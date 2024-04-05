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

//        List<Cards> cards = readJsonArray(Path.of("resource/cards.json"), Cards.class);
//        for (Cards c : cards) {
//            System.out.println(c.toString());
//        }
        try {
            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            System.setOut(new PrintStream(new FileOutputStream(file)));
            String jsonStrings = Files.readString(Path.of("resource/stations.json"));
            JSONObject jsonObject = JSONObject.parseObject(jsonStrings, Feature.OrderedField);
            int tmp = 0, tmp2 = 0, tmp3 = 0;
            for (String stationName : jsonObject.keySet()) {
                JSONObject station = jsonObject.getJSONObject(stationName);
//                System.out.printf("INSERT INTO stations(station_id, English_name, Chinese_name, District, Introduction)");
//                String StationName = stationName.replace("'", "''");
//                String Introduction = station.getString("intro").replace("'", "''");
//                System.out.printf(
//                        " VALUES(%d, '%s', '%s', '%s', '%s');\n",
//                        ++tmp, StationName, station.getString("chinese_name"), station.getString("district"), Introduction);

                //System.out.println("--BusInfo");
//                JSONArray busInfoArray = JSONArray.parseArray(station.getString("bus_info"));
//                for (Object busInfoObject : busInfoArray) {
//                    JSONObject busInfo = (JSONObject) busInfoObject;
//                    System.out.println("\tchukou: " + busInfo.getString("chukou"));
//                    ++tmp2;
//                    JSONArray busOutInfoArray = busInfo.getJSONArray("busOutInfo");
//                    for (Object busOutObject : busOutInfoArray) {
//                        JSONObject busOutInfo = (JSONObject) busOutObject;
//                        ++tmp3;
////                        System.out.printf("INSERT INTO Bus_Name(BusName_id, Entrance_id, BusName)");
////                        System.out.printf(" VALUES(%d, %d, '%s');\n", tmp3, tmp2, busOutInfo.getString("busName"));
//                        String[] buslines = busOutInfo.getString("busInfo").split("、|\\s|,|\\.|，|。|;|；");
//                        for (String busline : buslines) {
////                            System.out.printf("INSERT INTO Bus_Line(BusLine_id, BusName_id, BusLine)");
////                            String BusLine = busline.replace(" ", "");
////                            System.out.printf(" VALUES(%d, %d, '%s');\n", ++tmp, tmp3, BusLine);
//                        }
//                        System.out.println("\tbusInfo: " + Arrays.toString(buslines));
//                        System.out.println("\tbusName: " + busOutInfo.getString("busName"));
//                    }
//                }


                System.out.println("--TexttInfo");
                JSONArray outInfoArray = JSONArray.parseArray(station.getString("out_info"));

                for (Object outInfoObject : outInfoArray) {
                    JSONObject outInfo = (JSONObject) outInfoObject;
                    System.out.println("\toutt: " + outInfo.getString("outt").trim());
                    String[] textt = outInfo.getString("textt").split("、");
                    System.out.println("\ttextt: " + Arrays.toString(textt));

                }
                writer.close();
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
