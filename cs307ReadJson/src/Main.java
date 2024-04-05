import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {

        List<Cards> cards = readJsonArray(Path.of("resource/cards.json"), Cards.class);
        try {
            File file = new File("../Process_Data/Cards.sql");
            System.setOut(new PrintStream(new FileOutputStream(file)));
            System.out.println("""
                    CREATE TABLE Cards (
                        Card_number varchar(10) primary key not null,
                        Money float,
                        Create_time varchar(255)
                    );
                    """);
            for (Cards c : cards) {
                System.out.print("INSERT INTO Cards(Card_number, Money, Create_time) ");
                System.out.printf(
                        " VALUES('%s', %f, '%s');\n",
                        c.getCode(), c.getMoney(), c.getCreate_time());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Passengers> users = readJsonArray(Path.of("resource/passenger.json"), Passengers.class);
        try {
            File file = new File("../Process_Data/Passengers.sql");
            System.setOut(new PrintStream(new FileOutputStream(file)));
            System.out.println("""
                    CREATE TABLE Users (
                        User_id_number varchar(18) primary key not null,
                        Name varchar(10) not null,
                        Phone varchar(11),
                        Gender char(1),
                        District varchar(18)
                    );
                    """);
            for (Passengers p : users) {
                System.out.print("INSERT INTO Users(User_id_number, Name, Phone, Gender, District) ");
                System.out.printf(
                        " VALUES('%s', '%s', '%s', '%c', '%s');\n",
                        p.getId_number(), p.getName(), p.getPhone(), p.getGender(), p.getDistrict());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File file = new File("../Process_Data/Stations.sql");
            System.setOut(new PrintStream(new FileOutputStream(file)));
            String jsonStrings = Files.readString(Path.of("resource/stations.json"));
            JSONObject jsonObject = JSONObject.parseObject(jsonStrings, Feature.OrderedField);
            System.out.println("""
                    CREATE TABLE if not exists Stations (
                        Station_id    int         not null primary key,
                        English_name  varchar(900) not null,
                        Chinese_name  varchar(900) not null,
                        District      varchar(900) not null,
                        Introduction  text
                    );
                    """);
            int tmp = 0;
            for (String stationName : jsonObject.keySet()) {
                JSONObject station = jsonObject.getJSONObject(stationName);
                System.out.print("INSERT INTO stations(station_id, English_name, Chinese_name, District, Introduction) ");
                String StationName = stationName.replace("'", "''");
                String Introduction = station.getString("intro").replace("'", "''");
                System.out.printf(
                        "VALUES(%d, '%s', '%s', '%s', '%s');\n",
                        ++tmp, StationName, station.getString("chinese_name"), station.getString("district"), Introduction);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> List<T> readJsonArray(Path path, Class<T> clz) {
        try {
            String jsonStrings = Files.readString(path);
            return JSON.parseArray(jsonStrings, clz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
