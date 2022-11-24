package com.api.base;

import java.io.IOException;

import com.api.console.Logging;
import com.api.utilities.JsonUtils;
import org.testng.annotations.*;
import com.api.reports.ReportUtil;
import com.api.support.Constants;
import com.api.utilities.CommonUtils;
import com.api.utilities.Zip;

public class BaseInit {

	public static String testCaseName;
	public static String testCaseId;
	public static boolean continueRun = false;
	public static String endpoint = null;
	public static final String BASE_URL = CommonUtils.getProperty("BASE_URL");

	/**
	 *
	 * @param tcId
	 * @return
	 */
	public static boolean isRunnable(String tcId) {
		boolean isRunnable = false;
		continueRun = false;
		testCaseId = tcId;
		testCaseName = JsonUtils.getScenarioName(testCaseId);
		String runMode = JsonUtils.getRunMode(testCaseId);
		if (JsonUtils.isValidTestCaseId(testCaseId) && runMode.equalsIgnoreCase(Constants.TEST_CASE_RUNMODE_YES)) {
			continueRun = true;
			endpoint = BASE_URL + JsonUtils.getEndpoint(tcId);
			isRunnable = true;
			Logging.info("Test scenario started:==== " + testCaseId + ": " + testCaseName);
		} else {
			Logging.info("Either tcId is invalid or runmode of tcId is not Yes'" + testCaseId + "'");
			isRunnable = false;
		}
		return isRunnable;
	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@AfterMethod
	public void tearDownTest() {
		ReportUtil.test = null;
	}

	@BeforeSuite
	public void init () {
		CommonUtils.deleteDirectory(Constants.FRAMEWORK_ROOT_DIRECTORY + Constants.FRONT_SLASH + Constants.EXECUTION_REPORT_GENERATING_DIR);
	}

	@AfterSuite
	public void tearDownSuite() throws IOException {;
		Zip.zipFile();
	}


}