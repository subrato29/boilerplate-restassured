package com.api.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonUtils {

    /**
     *
     * @param nameOfJsonFile
     * @return
     */
    public static List<String> getJsonBody (String nameOfJsonFile) {
        final String JSON_DATA_PATH = Constants.FRAMEWORK_ROOT_DIRECTORY + "/src/main/resources/jsonData/";
        List<String> enterprise = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(JSON_DATA_PATH + nameOfJsonFile + ".json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray enterpriseList = (JSONArray) jsonObject.get("enterprise");
            Iterator<JSONObject> iterator = enterpriseList.iterator();
            while (iterator.hasNext()) {
                enterprise.add (String.valueOf(iterator.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enterprise;
    }
}
