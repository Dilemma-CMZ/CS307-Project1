package TestLoaders;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader6Threads {
    private static final int BATCH_SIZE = 1000, THREAD_NUM = 5;
    private static Connection[] con = new Connection[THREAD_NUM];
    private static final PreparedStatement[] stmt = new PreparedStatement[THREAD_NUM];

    private static void openDB(Properties prop) {
        try {
            Class.forName(LoaderControl.loader);
        } catch (Exception e) {
            System.err.println("Cannot find the Postgres driver. Check CLASSPATH.");
            System.exit(1);
        }
        String url = LoaderControl.url_header + prop.getProperty("host") + "/" + prop.getProperty("database");
        try {
            for (int i = 0; i < THREAD_NUM; i++) {
                if (con != null) {
                    con[i] = DriverManager.getConnection(url, prop);
                    System.out.println("Connection " + i + ", successfully connected to the database "
                            + prop.getProperty("database") + " as " + prop.getProperty("user"));
                    con[i].setAutoCommit(false);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void setPrepareStatement() {
        try {
            for (int i = 0; i < THREAD_NUM; i++)
                stmt[i] = con[i].prepareStatement("INSERT INTO Buildings(Building_id, Entrance_id, Entrance) " +
                        "VALUES(?, ?, ?);");
        } catch (SQLException e) {
            System.err.println("Insert statement failed");
            System.err.println(e.getMessage());
            closeDB();
            System.exit(1);
        }
    }

    private static void closeDB() {
        if (con != null) {
            try {
                for (int i = 0; i < THREAD_NUM; i++)
                    if (stmt[i] != null) {
                        stmt[i].close();
                        con[i].close();
                        con[i] = null;
                    }
            } catch (Exception ignored) {
            }
        }
    }

    private static Properties loadDBUser() {
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream("dbUser.properties")));
            return properties;
        } catch (IOException e) {
            System.err.println("can not find db user file");
            throw new RuntimeException(e);
        }
    }

    private static List<String> loadSQLFile() {
        try {
            return Files.readAllLines(Path.of("Process_Data/Buildings.sql"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String regex = "INSERT INTO Buildings\\(Building_id, Entrance_id, Entrance\\) VALUES\\((\\d+), (\\d+), '([a-zA-Z0-9 \\u4e00-\\u9fff\\u3000-\\u303F\\uff00-\\uffef/\\p{Punct}]+)'\\);";

    public static void clearDataInTable() {
        Statement stmt0;
        if (con != null) {
            try {
                stmt0 = con[0].createStatement();
                stmt0.executeUpdate(LoaderControl.dropper);
                con[0].commit();
                stmt0.executeUpdate(LoaderControl.creator);
                con[0].commit();
                stmt0.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private static void operateTriggers(String mode) {
        try {
            Statement stmt0;
            List<String> list;
            if (mode.equals("disable")) {
                list = Files.readAllLines(Path.of("Process_Data/" + LoaderControl.disableTrigger + ".sql"));
            } else {
                list = Files.readAllLines(Path.of("Process_Data/" + LoaderControl.enableTrigger + ".sql"));
            }
            for (String line : list) {
                stmt0 = con[0].createStatement();
                stmt0.executeUpdate(line);
                con[0].commit();
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Properties prop = loadDBUser();
        List<String> lines = loadSQLFile();

        // Empty target table
        openDB(prop);
        clearDataInTable();
        operateTriggers("disable");
        setPrepareStatement();

        final CountDownLatch cdl = new CountDownLatch(THREAD_NUM);
        long starttime = System.currentTimeMillis();
        for (int k = 0; k < THREAD_NUM; k++) {
            int finalK = k;
            int maxi = lines.size() / THREAD_NUM;
            new Thread(() -> {
                Pattern pattern = Pattern.compile(regex);
                try {
                    for (int i = 1; i <= maxi; i++) {
                        Matcher matcher = pattern.matcher(lines.get(finalK * maxi + i - 1));
                        if (matcher.matches() && con != null) {
                            // System.out.println("inserting " + matcher.group(0) + ", k=" + finalK + ", i=" + i);
                            stmt[finalK].setInt(1, Integer.parseInt(matcher.group(1)));
                            stmt[finalK].setInt(2, Integer.parseInt(matcher.group(2)));
                            stmt[finalK].setString(3, matcher.group(3));
                            stmt[finalK].executeUpdate();
                        }
                        if (i % BATCH_SIZE == 0) {
                            stmt[finalK].executeBatch();
                            System.out.println("No. " + finalK + " thread, insert " + BATCH_SIZE + " data successfully!");
                            stmt[finalK].clearBatch();
                        }
                    }
                    if (lines.size() % BATCH_SIZE != 0) {
                        stmt[finalK].executeBatch();
                        System.out.println("No. " + finalK + " thread, insert " + lines.size() % BATCH_SIZE + " data successfully!");
                    }
                    if (con != null && con[finalK] != null) {
                        con[finalK].commit();
                    }
                    cdl.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            cdl.await();
            long spendtime = System.currentTimeMillis() - starttime;
            System.out.println(THREAD_NUM + " threads, use all together " + spendtime + "ms");
            System.out.println("Loading speed : " + Math.round(lines.size() / (spendtime / 1000.0)) + " records/s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        operateTriggers("enable");

        for (int i = 0; i < THREAD_NUM; i++) {
            try {
                if (con != null && con[i] != null) {
                    con[i].close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        closeDB();

    }
}

