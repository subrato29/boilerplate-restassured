package com.api.utilities;

import com.api.base.BaseInit;
import com.api.support.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonUtils extends BaseInit {

    /**
     * Based on jsonKey, json array object will be returned
     * @param jsonFileName
     * @param jsonObjectKey
     * @return
     */
    public static JSONArray getJsonArrayByKeyFromTestDataDir (String jsonFileName, String jsonObjectKey) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(TEST_DATA_DIR + jsonFileName + ".json"));
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
    public static JSONArray getJsonBodyFromTestDataDir (String jsonFileName) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(TEST_DATA_DIR + jsonFileName + ".json")) {
            return (JSONArray)jsonParser.parse(reader);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param jsonFilePath
     * @return
     */
    public static JSONArray getJsonBody (String jsonFilePath) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(jsonFilePath)) {
            return (JSONArray)jsonParser.parse(reader);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param tcId
     * @param key
     * @return String value of the keys belong to controller.json
     */
    public static String getControllerValueBasedOnKey (String tcId, String key) {
        String jsonFilePath = TEST_CONTROLLER_DIR + Constants.CONTROLLER_DOT_JSON;
        JSONArray controller = JsonUtils.getJsonBody(jsonFilePath);
        for (int i = 0; i < controller.size(); i++) {
            JSONObject jsonObject = (JSONObject) controller.get(i);
            if (jsonObject.containsKey(tcId)) {
                return ((JSONObject) jsonObject.get(tcId)).get(key).toString();
            }
        }
        return null;
    }

    /**
     *
     * @param tcId
     * @param key
     * @return Boolean value of the keys belong to controller.json
     */
    public static boolean getControllerBooleanValueBasedOnKey (String tcId, String key) {
        String jsonFilePath = TEST_CONTROLLER_DIR + Constants.CONTROLLER_DOT_JSON;
        JSONArray controller = JsonUtils.getJsonBody(jsonFilePath);
        for (int i = 0; i < controller.size(); i++) {
            JSONObject jsonObject = (JSONObject) controller.get(i);
            if (jsonObject.containsKey(tcId)) {
                if (((JSONObject) jsonObject.get(tcId)).get(key).equals(true)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This function will check whether input TestCaseID belongs to controller.json
     * @param tcId
     * @return
     */
    public static boolean isValidTestCaseId (String tcId) {
        String jsonFilePath = TEST_CONTROLLER_DIR + Constants.CONTROLLER_DOT_JSON;
        JSONArray controller = JsonUtils.getJsonBody(jsonFilePath);
        for (int i = 0; i < controller.size(); i++) {
            JSONObject jsonObject = (JSONObject) controller.get(i);
            if (jsonObject.containsKey(tcId)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    public static boolean getRunMode () {
        return getControllerBooleanValueBasedOnKey (testCaseId, Constants.TEST_CASE_RUN_MODE_KEY);
    }

    /**
     *
     * @return
     */
    public static String getScenarioName () {
        return getControllerValueBasedOnKey (testCaseId, Constants.SCENARIO_NAME_KEY);
    }

    /**
     *
     * @return
     */
    public static String getEndpoint () {
        return getControllerValueBasedOnKey (testCaseId, Constants.ENDPOINT_KEY);
    }

    /**
     *
     * @return
     */
    public static String getRequestBodyFileName () {
        return getControllerValueBasedOnKey (testCaseId, Constants.REQUEST_BODY_KEY);
    }

    /**
     *
     * @return
     */
    public static boolean getSuiteRunMode () {
        String jsonFilePath = TEST_CONTROLLER_DIR + Constants.SUITE_RUN_CONFIG_DOT_JSON;
        JSONArray runConfig = JsonUtils.getJsonBody(jsonFilePath);
        for (int i = 0; i < runConfig.size(); i++) {
            JSONObject jsonObject = (JSONObject) runConfig.get(i);
            if (jsonObject.get(Constants.SUITE_RUN_MODE_KEY).equals(true)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param jsonFileName
     * @throws IOException
     */
    public static String createJsonFileInsideTestDataDir(String jsonFileName) throws IOException {
        return CommonUtils.createFile(TEST_CONTROLLER_DIR + jsonFileName + ".json");
    }

    /**
     *
     * @param jsonFileName
     * @param jsonObject
     */
    public static void writeToJsonFileInsideTestDataDir(String jsonFileName, JSONObject jsonObject) throws IOException {
        FileWriter file = new FileWriter(createJsonFileInsideTestDataDir(jsonFileName));
        try {
            file.write("[" + jsonObject.toJSONString() + "]");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            file.close();
        }
    }
}
