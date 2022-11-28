package com.api.reports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportUtil extends ExtentManager {
	static ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	static int countOfCallingEndTest = 0;
	static int countOfCallingStartTest = 0;

	/**
	 *
	 * @param comment
	 * @return
	 */
	public static String reportStepFailed(String comment) {
		String reportStep = null;
		try {
			reportStep = "<font color='red' face='Georgia'>" + comment + "</font>";
		} catch (Throwable t) {
			reportStep = null;
		}
		return reportStep;
	}

	/**
	 *
	 * @param comment
	 * @return
	 */
	public static String reportStepPassed(String comment) {
		String reportStep = null;
		try {
			reportStep = "<font color='white' face='Georgia'>" + comment + "</font>";
		} catch (Throwable t) {
			reportStep = null;
		}
		return reportStep;
	}

	/**
	 *
	 * @param comment
	 * @return
	 */
	public static String reportStepInfo(String comment) {
		try {
			String reportStep = "<font color='white' face='Georgia'><i>" + comment + "</i></font>";
			return reportStep;
		} catch (Throwable t) {
			return null;
		}
	}

	/**
	 *
	 * @param comment
	 * @return
	 */
	public static String reportStepWarning(String comment) {
		String reportStep = null;
		try {
			reportStep = "<font color='orange' face='Georgia'>" + comment + "</font>";
		} catch (Throwable t) {
			reportStep = null;
		}
		return reportStep;
	}

	/**
	 *
	 * @param comment
	 * @return
	 */
	public static String reportStepSkip(String comment) {
		try {
			String reportStep = "<font color='sky blue' face='Georgia'>" + comment + "</font>";
			return reportStep;
		} catch (Throwable t) {
			return null;
		}
	}

	/**
	 *
	 * @param comment
	 */
	public static void markPassed(String comment) {
		if (continueRun) {
			if (test == null) {
				test = report.startTest(displayReportPane ());
			}
			try {
				test.log(LogStatus.PASS, reportStepPassed(comment));
			} finally {
				if (report != null) {
					report.endTest(test);
					report.flush();
				}
			}
		}
	}

	/**
	 *
	 * @param comment
	 */
	public static void markFailed(String comment) {
		if (continueRun) {
			if (test == null) {
				test = report.startTest(displayReportPane ());
			}
			try {
				test.log(LogStatus.FAIL, reportStepFailed(comment));
			} finally {
				if (report != null) {
					report.endTest(test);
					report.flush();
				}
			}
		}
	}

	/**
	 *
	 * @param comment
	 */
	public static void markInfo(String comment) {
		if (test == null) {
			test = report.startTest(displayReportPane ());
		}
		try {
			if (comment.toUpperCase().equals("START")) {
				comment = "Starting the test";
				test.log(LogStatus.INFO, reportStepInfo(comment));
				countOfCallingStartTest++;
			} else if (comment.toUpperCase().equals("END")) {
				comment = "Ending the test";
				test.log(LogStatus.INFO, reportStepInfo(comment));
				test = null;
				countOfCallingEndTest++;
			} else {
				test.log(LogStatus.INFO, reportStepInfo(comment));
			}
		} finally {
			if (report != null) {
				report.endTest(test);
				report.flush();
			}
		}
	}

	/**
	 *
	 * @param comment
	 */
	public static void markWarning(String comment) {
		if (continueRun) {
			if (test == null) {
				test = report.startTest(displayReportPane ());
			}
			try {
				test.log(LogStatus.WARNING, reportStepWarning(comment));
			} finally {
				if (report != null) {
					report.endTest(test);
					report.flush();
				}
			}
		}
	}

	/**
	 *
	 * @param comment
	 */
	public static void markSkip(String comment) {
		if (test == null) {
			test = report.startTest(displayReportPane ());
		}
		try {
			test.log(LogStatus.SKIP, reportStepSkip(comment));
		} finally {
			if (report != null) {
				report.endTest(test);
				report.flush();
			}
		}
	}

	/**
	 *
	 */
	public static void markStart() {
		if (test == null) {
			test = report.startTest(displayReportPane ());
		}
		try {
			test = null;
		} finally {
			if (report != null) {
				report.endTest(test);
				report.flush();
			}
		}
	}

	/**
	 *
	 * @return
	 */
	public static String displayReportPane () {
		return testCaseId + ": " + testCaseName;
	}
}