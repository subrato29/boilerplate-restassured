package com.runner.testng;
import java.util.List;

import com.api.utilities.Constants;
import org.testng.TestNG;

import com.beust.jcommander.internal.Lists;

public class Runner extends DynamicTestNg{
    /**
     * This class is responsible for running testng.xml
     * @param args
     */
    public static void main(String[] args) {
        DynamicTestNg.main(args);
        TestNG testng = new TestNG();
        List < String > suites = Lists.newArrayList();
        suites.add(Constants.FRAMEWORK_ROOT_DIRECTORY + Constants.FILE_SEPARATOR_KEY + TESTNG_DOT_XML);
        testng.setTestSuites(suites);
        testng.run();
    }
}