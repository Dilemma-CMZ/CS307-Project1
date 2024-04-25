package TestLoaders;

public class LoaderControl {

    // PostgreSQL
    public static String loader = "org.postgresql.Driver";
    public static String url_header = "jdbc:postgresql://";
    public static String dropper = "drop table if exists Buildings;";
    public static String creator = """
             CREATE TABLE if not exists Buildings (
                 Building_id INT NOT NULL,
                 Entrance_id INT NOT NULL,
                 Entrance VARCHAR(255),
                 PRIMARY KEY (Building_id),
                 CONSTRAINT buildings_fk1 FOREIGN KEY (Entrance_id) REFERENCES Entrances (Entrance_id)
             );
            """;
    public static String disableTrigger = "disable_triggers";
    public static String enableTrigger = "enable_triggers";

    // MySQL
//    public static String loader = "com.mysql.cj.jdbc.Driver";
//    public static String url_header = "jdbc:mysql://";
//    public static String dropper = "drop table if exists Buildings;";
//    public static String creator = """
//            create table if not exists buildings ( building_id int  null, entrance_id int  null, entrance    tinytext null);
//            """;
//
//    public static String disableTrigger = "disable_triggers_MySQL";
//    public static String enableTrigger = "enable_triggers_MySQL";
}
