package com.api.support;

import java.io.File;

public class Constants {
	public static final String FILE_SEPARATOR_KEY = File.separator;
	public static final String REQUEST_BODY_KEY = "requestBody";
	public static final String COLON_STRING_WITH_ONE_SPACE = ": ";
	public static final String DOT_STRING = ".";
	public static final String FRONT_SLASH = "/";
	public static final String ID_KEY = "id";
	public static final String ENDPOINT_KEY = "endpoint";
	public static final String EXECUTION_REPORT_GENERATING_DIR = "Results";
	public static final String EXECUTION_REPORT_FILE_NAME = "Report";
	public static final String FRAMEWORK_ROOT_DIRECTORY = System.getProperty("user.dir");
	public static final String TEST_CONTROLLER_DIR = "/src/main/resources/testController/";
	public static final String TEST_DATA_DIR = "/src/main/resources/testData/";
	public static final String CONTROLLER_DOT_JSON = "controller.json";
	public static final String SUITE_RUN_CONFIG_DOT_JSON = "suiteRunConfig.json";
	public static final String SUITE_RUN_MODE_KEY = "suiteRunMode";
	public static final String TEST_CASE_RUN_MODE_KEY = "runmode";
	public static final String SCENARIO_NAME_KEY = "scenario";
}