package Loaders;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader4Transaction {

    private static Connection con = null;
    private static PreparedStatement stmt = null;

    private static void openDB(Properties prop) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.err.println("Cannot find the Postgres driver. Check CLASSPATH.");
            System.exit(1);
        }
        String url = "jdbc:postgresql://" + prop.getProperty("host") + "/" + prop.getProperty("database");
        try {
            con = DriverManager.getConnection(url, prop);
            if (con != null) {
                System.out.println("Successfully connected to the database "
                        + prop.getProperty("database") + " as " + prop.getProperty("user"));
                con.setAutoCommit(false); // turn off auto-commit: transaction mode
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void setPrepareStatement() {
        try {
            stmt = con.prepareStatement("INSERT INTO Buildings(Building_id, Entrance_id, Entrance) " +
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
                if (stmt != null) {
                    stmt.close();
                }
                con.close();
                con = null;
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

    static String regex = "INSERT INTO Buildings\\(Building_id, Entrance_id, Entrance\\) VALUES\\((\\d+), (\\d+), ('[a-zA-Z0-9 \\u4e00-\\u9fff\\u3000-\\u303F\\uff00-\\uffef/\\p{Punct}]+')\\);";
    static Pattern pattern = Pattern.compile(regex);

    private static void loadData(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches() && con != null) {
            try {
                stmt.setInt(1, Integer.parseInt(matcher.group(1)));
                stmt.setInt(2, Integer.parseInt(matcher.group(2)));
                stmt.setString(3, matcher.group(3));
                stmt.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void clearDataInTable() {
        Statement stmt0;
        if (con != null) {
            try {
                stmt0 = con.createStatement();
                stmt0.executeUpdate("drop table if exists Buildings;");
                con.commit();
                stmt0.executeUpdate("""
                         CREATE TABLE if not exists Buildings (
                             Building_id INT NOT NULL,
                             Entrance_id INT NOT NULL,
                             Entrance VARCHAR(255),
                             PRIMARY KEY (Building_id),
                             CONSTRAINT buildings_fk1 FOREIGN KEY (Entrance_id) REFERENCES Entrances (Entrance_id)
                         );
                        """);
                con.commit();
                stmt0.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void main(String[] args) {
        Properties prop = loadDBUser();
        List<String> lines = loadSQLFile();

        // Empty target table
        openDB(prop);
        clearDataInTable();
        closeDB();

        int cnt = 0;

        long start = System.currentTimeMillis();
        openDB(prop);
        setPrepareStatement();
        for (String line : lines) {
            loadData(line);//do insert command
            cnt++;
            if (cnt % 1000 == 0) {
                System.out.println("insert " + 1000 + " data successfully!");
            }
        }

        try {
            con.commit();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        closeDB();
        long end = System.currentTimeMillis();
        System.out.println(cnt + " records successfully loaded");
        System.out.println("Loading speed : " + (cnt * 1000L) / (end - start) + " records/s");
    }
}

