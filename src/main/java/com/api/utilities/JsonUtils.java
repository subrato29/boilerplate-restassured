package com.api.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonUtils {

    static final String JSON_DATA_PATH = Constants.FRAMEWORK_ROOT_DIRECTORY + Constants.JSON_DATA_PATH;

    /**
     * Based on jsonKey, json array object will be returned
     * @param jsonFileName
     * @param jsonObjectKey
     * @return
     */
    public static JSONArray getJsonArrayByKey (String jsonFileName, String jsonObjectKey) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(JSON_DATA_PATH + jsonFileName + ".json"));
            JSONObject jsonObject = (JSONObject) obj;
            return (JSONArray) jsonObject.get(jsonObjectKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read the whole JSON body
     * @param jsonFileName
     * @return
     */
    public static JSONArray getJsonBody (String jsonFileName) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(JSON_DATA_PATH + jsonFileName + ".json")) {
            return (JSONArray)jsonParser.parse(reader);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }
}
