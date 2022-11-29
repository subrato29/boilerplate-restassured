package com.api.reports;
import java.io.File;

import com.api.base.BaseInit;
import com.api.utilities.CommonUtils;
import com.api.support.Constants;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager extends BaseInit {
	private static ExtentReports extent;
	public static String dynamicHtmlReportPath;
	public static String reportFolderPath = null;
	static String env = CommonUtils.getProperty("ENV");

	/**
	 *
	 * @return
	 */
	public static ExtentReports getInstance() {
		if (extent == null) {
			dynamicHtmlReportPath = htmlReportPath();
			extent.loadConfig(new File(Constants.FRAMEWORK_ROOT_DIRECTORY + "/ReportsConfig.xml"));
			try {
				extent
						.addSystemInfo("Environment", env);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return extent;
	}

	/**
	 *
	 * @return
	 */
	public static String htmlReportPath() {
		String fileName = Constants.EXECUTION_REPORT_FILE_NAME + ".html";
		reportFolderPath = Constants.FRAMEWORK_ROOT_DIRECTORY + Constants.FILE_SEPARATOR_KEY + Constants.EXECUTION_REPORT_GENERATING_DIR;
		CommonUtils.generateDirectory(reportFolderPath);
		String htmlReportPath = reportFolderPath + Constants.FILE_SEPARATOR_KEY + fileName;
		extent = new ExtentReports(htmlReportPath);
		return htmlReportPath;
	}
}