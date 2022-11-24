package com.api.base;

import java.io.IOException;

import com.api.console.Logging;
import org.testng.annotations.*;
import com.api.reports.ReportUtil;
import com.api.support.Xls_Reader;
import com.api.utilities.Constants;
import com.api.utilities.CommonUtils;
import com.api.utilities.Zip;

public class DriverScript {

	public static String TEST_CONTROLLER_PATH = Constants.FRAMEWORK_ROOT_DIRECTORY + Constants.TEST_CONTROLLER_PATH;
	public static Xls_Reader controller = new Xls_Reader(TEST_CONTROLLER_PATH + Constants.CONTROLLER_FILE);
	public static int rowNum = 2;
	public static int rowNumExecutableTC = 2;
	public static int count = 0;
	public static String testCaseName;
	public static String testCaseId;
	public static boolean continueRun = false;
	public static String endpoint = null;
	public static final String BASE_URL = CommonUtils.getProperty("BASE_URL");

	/**
	 *
	 * @return
	 */
	public static int getRowNumForExecutableTestCases() {
		while (rowNumExecutableTC <= controller.getRowCount(Constants.TEST_DATA)) {
			if (controller.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_RUNMODE, rowNumExecutableTC).toUpperCase().equals(Constants.TEST_CASE_RUNMODE_YES)) {
				count++;
			}
			rowNumExecutableTC++;
		}
		rowNumExecutableTC = 2;
		return count;
	}

	/**
	 *
	 * @param tcId
	 * @return
	 */
	public static boolean isRunnable(String tcId) {
		boolean isRunnable = false;
		continueRun = false;
		rowNum = controller.getCellRowNum(Constants.TEST_DATA, Constants.TEST_CASE_ID, tcId);
		testCaseId = tcId;
		testCaseName = controller.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_NAME, rowNum);
		if (controller.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_RUNMODE, rowNum).equalsIgnoreCase(Constants.TEST_CASE_RUNMODE_YES)) {
			continueRun = true;
			endpoint = BASE_URL + controller.getCellData(Constants.TEST_DATA, Constants.ENDPOINT, rowNum);
			isRunnable = true;
			Logging.info("Test scenario started:==== " + testCaseId + ": " + testCaseName);
		} else {
			Logging.info("Please check the runmode of TestCaseID '" + testCaseId + "'");
			isRunnable = false;
		}
		return isRunnable;
	}

	public static final int countOfExecutableTestCases = getRowNumForExecutableTestCases();

	public static String getTestDataSheetName() {
		String testDataSheet = controller.getCellData(Constants.TEST_DATA, Constants.TEST_DATA_FILE_NAME, rowNum);
		return testDataSheet;
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