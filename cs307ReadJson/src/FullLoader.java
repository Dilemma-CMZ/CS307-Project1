import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class FullLoader {
    private static Connection con = null;
    private static Statement stmt = null;

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
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed");
            System.err.println(e.getMessage());
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

    private static List<String> loadSQLFile(String name) {
        try {
            return Files.readAllLines(Path.of("Process_Data/" + name + ".sql"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadData(String line) {
        try {
            if (con != null) {
                stmt = con.createStatement();
                stmt.execute(line);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearDataInTable() {
        List<String> lines = loadSQLFile("Definition");
        Statement stmt0;
        if (con != null) {
            try {
                stmt0 = con.createStatement();
                for (String line : lines) {
                    stmt0.execute(line);
                }
                stmt0.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void main(String[] args) {
        Properties prop = loadDBUser();

        String[] sqls = new String[]{
                "disable_triggers",
                "Lines", "Stations", "Lines_Detail", "Entrance", "Buildings", "Bus_Name", "Bus_Line", "Users", "Cards", "Card_Rides", "User_Rides",
                "enable_triggers"
        };

        // Empty target table
        long start = System.currentTimeMillis();
        openDB(prop);
        clearDataInTable();

        int allCnt = 0;
        for (String sql : sqls) {
            List<String> lines = loadSQLFile(sql);
            int cnt = 0;
            for (String line : lines) {
                loadData(line);//do insert command
                cnt++;
            }
            allCnt += cnt;
            System.out.println(sql + ".sql: insert " + cnt + " data successfully!");
        }

        closeDB();
        long end = System.currentTimeMillis();
        System.out.println(allCnt + " records successfully loaded");
        System.out.println("Loading speed : " + (allCnt * 1000L) / (end - start) + " records/s");
        System.out.println("Loading time : " + ((float) (end - start)) / 1000f + " s");
    }
}


