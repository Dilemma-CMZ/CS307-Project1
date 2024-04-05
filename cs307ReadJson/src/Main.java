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
        // Cards
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

        // Users
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

        // Lines, stations and all related
        HashMap<String, Integer> stationsMap = new HashMap<>();
        try {
            File
                    fileStations = new File("../Process_Data/Stations.sql"),
                    fileEntrances = new File("../Process_Data/Entrance.sql"),
                    fileBuildings = new File("../Process_Data/Buildings.sql"),
                    fileBusName = new File("../Process_Data/Bus_Name.sql"),
                    fileBusLine = new File("../Process_Data/Bus_Line.sql"),
                    fileLines = new File("../Process_Data/Lines.sql"),
                    fileLinesDetail = new File("../Process_Data/Lines_Detail.sql");

            FileWriter writerStations = new FileWriter(fileStations), writerEntrances = new FileWriter(fileEntrances), writerBuildings = new FileWriter(fileBuildings), writerBusName = new FileWriter(fileBusName), writerBusLine = new FileWriter(fileBusLine), writerLines = new FileWriter(fileLines), writerLinesDetail = new FileWriter(fileLinesDetail);

            String LinesStrings = Files.readString(Path.of("resource/lines.json"));
            JSONObject LineObject = JSONObject.parseObject(LinesStrings, Feature.OrderedField);

            String jsonStrings = Files.readString(Path.of("resource/stations.json"));
            JSONObject jsonObject = JSONObject.parseObject(jsonStrings, Feature.OrderedField);

            writerStations.append("""
                    CREATE TABLE if not exists Stations (
                        Station_id    int         not null primary key,
                        English_name  varchar(900) not null,
                        Chinese_name  varchar(900) not null,
                        District      varchar(900) not null,
                        Introduction  text
                    );
                    """);
            writerBuildings.append("""
                    CREATE TABLE Buildings(
                        Building_id int,
                        Entrance_id int,
                        Entrance varchar(255),
                        primary key(Building_id)
                    );
                    """);
            writerBusLine.append("""
                    CREATE TABLE Bus_Line(
                        BusLine_id int primary key,
                        BusName_id int,
                        BusLine varchar(50)
                    );
                    """);
            writerBusName.append("""
                    CREATE TABLE Bus_Name(
                        BusName_id int primary key,
                        Entrance_id int,
                        BusName varchar(255)
                    );
                    """);
            writerEntrances.append("""
                    CREATE TABLE Entrance(
                        Entrance_id int primary key not null,
                        station_id int not null,
                        entrance varchar(255)
                    );
                    """);
            writerLinesDetail.append("""
                    CREATE TABLE Line_details (
                        Line_id int,
                        Station_id int,
                        primary key (Line_id, Station_id)
                    );
                    """);
            writerLines.append("""
                    CREATE TABLE Lines(
                        Line_id int,
                        start_time varchar(10),
                        end_time varchar(10),
                        intro text,
                        mileage varchar(10),
                        color varchar(10),
                        first_opening date,
                        url varchar(100),
                        primary key(Line_id)
                    );
                    """);

            int station_count = 0, entrance_count = 0, building_count = 0, bus_count = 0, bus_line_count = 0, bus_countt = 0;
            for (String stationName : jsonObject.keySet()) {
                JSONObject station = jsonObject.getJSONObject(stationName);
                writerStations.append("INSERT INTO stations(station_id, English_name, Chinese_name, District, Introduction) ");
                String StationName = stationName.replace("'", "''");
                String Introduction = station.getString("intro").replace("'", "''");
                writerStations.append(
                        "VALUES(" + (++station_count) + ", '" + StationName + "', '" + station.getString("chinese_name") + "', '" + station.getString("district") + "', '" + Introduction + "');\n");
                stationsMap.put(StationName, station_count);

                // Bus Info
                JSONArray busInfoArray = JSONArray.parseArray(station.getString("bus_info"));
                HashMap<String, Integer> Entrance_map = new HashMap<>();
                Entrance_map.clear();
                for (Object busInfoObject : busInfoArray) {
                    JSONObject busInfo = (JSONObject) busInfoObject;
                    JSONArray busOutInfoArray = busInfo.getJSONArray("busOutInfo");
                    String entrance = busInfo.getString("chukou").trim().replace(" ", "").trim();
                    if (entrance.startsWith("此")) continue;
                    if (!Entrance_map.containsKey(entrance))
                        Entrance_map.put(entrance, ++entrance_count);
                    for (Object busOutObject : busOutInfoArray) {
                        ++bus_count;
                        JSONObject busOutInfo = (JSONObject) busOutObject;
                        String[] buslines = busOutInfo.getString("busInfo").split("、|\\s|,|\\.|，|。|;|；");
                        for (String busline : buslines) {
                            String BusLine = busline.replace(" ", "");
                            if (BusLine.equals("")) continue;
                            writerBusLine.append("INSERT INTO Bus_Line(BusLine_id, BusName_id, BusLine)");
                            writerBusLine.append(" VALUES(" + (++bus_line_count) + ", " + bus_count + ", '" + BusLine + "');\n");
                        }
                    }
                }

                // Buildings
                JSONArray outInfoArray = JSONArray.parseArray(station.getString("out_info"));
                for (Object outInfoObject : outInfoArray) {
                    JSONObject outInfo = (JSONObject) outInfoObject;
                    String entrance = outInfo.getString("outt").trim().replace(" ", "").trim();
                    if (entrance.startsWith("此")) continue;
                    if (!Entrance_map.containsKey(entrance))
                        Entrance_map.put(entrance, ++entrance_count);
                    String[] textt = outInfo.getString("textt").split("、");
                    for (String text : textt) {
                        if (text.equals("")) continue;
                        writerBuildings.append("INSERT INTO Buildings(Building_id, Entrance_id, Entrance)");
                        writerBuildings.append(" VALUES(" + (++building_count) + ", " + Entrance_map.get(entrance) + ", '" + text + "');\n");
                    }
                }

                // Entrances
                for (String entrance : Entrance_map.keySet()) {
                    writerEntrances.append("INSERT INTO Entrance(Entrance_id, Station_id, Entrance)");
                    writerEntrances.append(" VALUES(" + Entrance_map.get(entrance) + ", " + station_count + ", '" + entrance + "');\n");
                }

                // Bus Name
                for (Object busInfoObject : busInfoArray) {
                    JSONObject busInfo = (JSONObject) busInfoObject;
                    JSONArray busOutInfoArray = busInfo.getJSONArray("busOutInfo");
                    String entrance = busInfo.getString("chukou").trim().replace(" ", "").trim();
                    if (entrance.startsWith("此")) continue;
                    if (!Entrance_map.containsKey(entrance)) Entrance_map.put(entrance, ++entrance_count);
                    for (Object busOutObject : busOutInfoArray) {
                        JSONObject busOutInfo = (JSONObject) busOutObject;
                        String[] buslines = busOutInfo.getString("busInfo").split("、|\\s|,|\\.|，|。|;|；");
                        writerBusName.append("INSERT INTO Bus_Name(BusName_id, Entrance_id, BusName)");
                        writerBusName.append(" VALUES(" + (++bus_countt) + ", " + Entrance_map.get(entrance) + ", '" + busOutInfo.getString("busName") + "');\n");
                    }
                }
            }

            // Lines
            int line_id = 0;
            for (String linename : LineObject.keySet()) {
                JSONObject Lines = LineObject.getJSONObject(linename);
                ++line_id;
                String start_time = Lines.getString("start_time");
                String end_time = Lines.getString("end_time");
                String intro = Lines.getString("intro");
                String mileage = Lines.getString("mileage");
                String color = Lines.getString("color");
                String first_opening = Lines.getString("first_opening");
                String url = Lines.getString("url");
                JSONArray station_array = JSONArray.parseArray(Lines.getString("stations"));
                writerLines.append("INSERT INTO Lines(Line_id, start_time, end_time, intro, mileage, color, first_opening, url) ");
                writerLines.append("VALUES(" + line_id + ", '" + start_time + "', '" + end_time + "', '" + intro + "', '" + mileage + "', '" + color + "', '" + first_opening + "', '" + url + "');\n");
                for (Object station_array_info : station_array) {
                    if (!stationsMap.containsKey(station_array_info.toString())) continue;
                    int stationid = stationsMap.get(station_array_info.toString());
                    writerLinesDetail.append("INSERT INTO Line_details(Line_id, Station_id) ");
                    writerLinesDetail.append("VALUES(" + line_id + ", " + stationid + ");\n");
                }
            }

            writerStations.close();
            writerEntrances.close();
            writerBuildings.close();
            writerBusName.close();
            writerBusLine.close();
            writerLines.close();
            writerLinesDetail.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Rides
        List<Ride> rides = readJsonArray(Path.of("resource/ride.json"), Ride.class);
        try {
            // User Rides
            File
                    fileUser = new File("../Process_Data/User_Rides.sql"),
                    fileCard = new File("../Process_Data/Card_Rides.sql");
            FileWriter writerUser = new FileWriter(fileUser), writerCard = new FileWriter(fileCard);
            writerUser.append("""
                    CREATE TABLE User_Rides (
                        Ride_id int primary key not null,
                        User_id varchar(18) not null,
                        From_station int not null,
                        To_station int not null,
                        Price float not null,
                        Start_time varchar(255),
                        End_time varchar(255)
                    );
                    """);
            writerCard.append("""
                    CREATE TABLE Card_Rides (
                        Ride_id int primary key not null,
                        Card_id varchar(9) not null,
                        From_station int not null,
                        To_station int not null,
                        Price float not null,
                        Start_time varchar(255),
                        End_time varchar(255)
                    );
                    """);
            int tmp = 0;
            for (Ride r : rides) {
                if (r.getUser() == null) continue;
                if (r.getUser().length() == 18) { // user rides
                    writerUser.append("INSERT INTO User_Rides(Ride_id, User_id, From_station, To_station, Price, Start_time, End_time) ");
                    writerUser.append(
                            " VALUES(" + (++tmp) + ", '" + r.getUser() + "', " + stationsMap.get(r.getStartStation()) + ", " + stationsMap.get(r.getEndStation()) + ", " + r.getPrice() + ", '" + r.getStartTime() + "', '" + r.getEndTime() + "');\n");
                } else if (r.getUser().length() == 9) { // card rides
                    writerCard.append("INSERT INTO Card_Rides(Ride_id, Card_id, From_station, To_station, Price, Start_time, End_time) ");
                    writerCard.append(
                            " VALUES(" + (++tmp) + ", '" + r.getUser() + "', " + stationsMap.get(r.getStartStation()) + ", " + stationsMap.get(r.getEndStation()) + ", " + r.getPrice() + ", '" + r.getStartTime() + "', '" + r.getEndTime() + "');\n");
                }
            }
            writerUser.close();
            writerCard.close();
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
