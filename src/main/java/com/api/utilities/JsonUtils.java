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
     * Based on jsonKey, json array object will be returned
     * @param nameOfJsonFile
     * @param jsonObjectKey
     * @return
     */
    public static List<String> getJsonBody (String nameOfJsonFile, String jsonObjectKey) {
        final String JSON_DATA_PATH = Constants.FRAMEWORK_ROOT_DIRECTORY + "/src/main/resources/jsonData/";
        List<String> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(JSON_DATA_PATH + nameOfJsonFile + ".json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get(jsonObjectKey);
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                list.add (String.valueOf(iterator.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
