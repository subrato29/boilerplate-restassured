package com.api.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonUtils {

    /**
     * Based on jsonKey, json array object will be returned
     * @param nameOfJsonFile
     * @param jsonObjectKey
     * @return
     */
    public static JSONArray getJsonArray (String nameOfJsonFile, String jsonObjectKey) {
        final String JSON_DATA_PATH = Constants.FRAMEWORK_ROOT_DIRECTORY + Constants.JSON_DATA_PATH;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(JSON_DATA_PATH + nameOfJsonFile + ".json"));
            JSONObject jsonObject = (JSONObject) obj;
            return (JSONArray) jsonObject.get(jsonObjectKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
