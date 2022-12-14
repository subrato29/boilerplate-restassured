package com.runner.testng;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.api.support.Constants;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class DynamicTestNg {
	public static final String TESTNG_DOT_XML = "testng.xml";
	public static void main(String[] args) {
		String testName = null;
		try {
			List < XmlClass > classList = new ArrayList < XmlClass > ();
			XmlSuite suite = new XmlSuite();
			XmlTest test = new XmlTest(suite);

			suite.setName("Suite");
			List < String > files = new ArrayList < String > ();
			files.add(Constants.FRAMEWORK_ROOT_DIRECTORY + Constants.FILE_SEPARATOR_KEY + TESTNG_DOT_XML);

			File file = new File(Constants.FRAMEWORK_ROOT_DIRECTORY + Constants.FILE_SEPARATOR_KEY +
					"src" + Constants.FILE_SEPARATOR_KEY + "test" + Constants.FILE_SEPARATOR_KEY + "java" + Constants.FILE_SEPARATOR_KEY + "scenarios");
			String[] fileList = file.list();
			for (String name: fileList) {
				testName = name.split(".java")[0];
				if (!testName.equals(".DS_Store")) {
					classList.add(new XmlClass("scenarios" + Constants.DOT_STRING + testName));
					test.setName("Test");
					test.setThreadCount(5);
					test.setXmlClasses(classList);
					FileWriter writer = new FileWriter(new File(TESTNG_DOT_XML));
					writer.write(suite.toXml());
					writer.flush();
					writer.close();
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}