package com.api.reports;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.api.base.DriverScript;
import com.api.utilities.CommonUtils;
import com.api.utilities.Constants;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	public static String dynamicHtmlReportPath;
	public static String reportFolderPath = null;

	public static ExtentReports getInstance() {
		if (extent == null) {
			dynamicHtmlReportPath = htmlReportPath();
			extent.loadConfig(new File(System.getProperty("user.dir") + "/ReportsConfig.xml"));
			try {
				extent
						.addSystemInfo("Environment", CommonUtils.getProperty("test_environment"))
						.addSystemInfo("Automation tools used", CommonUtils.getProperty("automation_tool_used"))
						.addSystemInfo("Nature of AUT", CommonUtils.getProperty("nature_of_aut"))
						.addSystemInfo("Name of the AUT", CommonUtils.getProperty("name_of_aut"));
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return extent;
	}


	public static String htmlReportPath() {
		String fileName = "Report.html";
		reportFolderPath = System.getProperty("user.dir") + Constants.FILE_SEPARATOR_KEY + "Results";
		File dir = new File(reportFolderPath);
		dir.mkdir();
		extent = new ExtentReports(reportFolderPath + Constants.FILE_SEPARATOR_KEY + fileName);
		return (reportFolderPath + File.separator + fileName);
	}

}