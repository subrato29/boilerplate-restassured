package com.api.utilities;

import com.api.base.BaseInit;
import com.api.support.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonUtils extends BaseInit {

    static final String TEST_DATA_DIR = Constants.FRAMEWORK_ROOT_DIRECTORY + Constants.TEST_DATA_DIR;
    static final String TEST_CONTROLLER_DIR = Constants.FRAMEWORK_ROOT_DIRECTORY + Constants.TEST_CONTROLLER_DIR;

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
     * @return
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
        return getControllerBooleanValueBasedOnKey (testCaseId, "runmode");
    }

    /**
     *
     * @return
     */
    public static String getScenarioName () {
        return getControllerValueBasedOnKey (testCaseId, "scenario");
    }

    /**
     *
     * @return
     */
    public static String getEndpoint () {
        return getControllerValueBasedOnKey (testCaseId, "endpoint");
    }

    /**
     *
     * @return
     */
    public static String getRequestBodyFileName () {
        return getControllerValueBasedOnKey (testCaseId, "requestBody");
    }

    /**
     *
     * @return
     */
    public static boolean getSuiteRunMode () {
        String jsonFilePath = TEST_CONTROLLER_DIR + Constants.SUITE_RUN_CONFIG;
        JSONArray runConfig = JsonUtils.getJsonBody(jsonFilePath);
        for (int i = 0; i < runConfig.size(); i++) {
            JSONObject jsonObject = (JSONObject) runConfig.get(i);
            if (jsonObject.get("suiteRunMode").equals(true)) {
                return true;
            }
        }
        return false;
    }
}
