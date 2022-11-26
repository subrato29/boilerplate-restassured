/**
 * This class  contains Utility methods specific to the framework architechture and
 * will be used to perform various actions across the framework
 *
 * @author Subrato
 */
package com.api.utilities;
import java.io.File;
import java.io.FileInputStream;

import com.api.base.BaseInit;
import com.api.reports.ExtentManager;
import com.api.support.Constants;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class CommonUtils {

	/**
	 *
	 * @param strVal
	 * @return
	 */
	public static String getProperty(String strVal) {
		String val;
		try {
			String path = Constants.FRAMEWORK_ROOT_DIRECTORY + "/environment/config.properties";
			Properties prop = new Properties();
			FileInputStream fs = new FileInputStream(path);
			prop.load(fs);
			val = prop.getProperty(strVal).trim();
		} catch (Throwable t) {
			val = null;
		}
		return val;
	}

	/**
	 *
	 * @return
	 */
	public static String htmlReportPathGenerated() {
		try {
			return ExtentManager.dynamicHtmlReportPath;
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * @param len
	 * @return
	 */
	public static String getRandomString(int len) {
		String CHARACTER_SET = "0123456789abcdefghijklmnopqrstuvwxyz";
		SecureRandom random = new SecureRandom();
		StringBuffer buff = new StringBuffer(len);
		for(int i = 0; i < len; i++) {
			int offset = random.nextInt(CHARACTER_SET.length());
			buff.append(CHARACTER_SET.substring(offset,offset+1));
		}
		return buff.toString();
	}

	/**
	 *
	 * @param dir
	 */
	public static void deleteDirectory (String dir) {
		try {
			FileUtils.deleteDirectory(new File(dir));
		} catch (Throwable t) {

		}
	}

	/**
	 *
	 * @param dirPath
	 * @return
	 */
	public static String generateDirectory (String dirPath) {
		File dir = new File(dirPath);
		dir.mkdir();
		return dirPath;
	}

	/**
	 *
	 * @param path
	 * @throws IOException
	 */
	public static String createFile (String path) throws IOException {
		File file = new File(path);
		file.createNewFile();
		return path;
	}

	/**
	 *
	 * @param jsonFileName
	 * @throws IOException
	 */
	public static String createJsonFileInsideTestDataDir (String jsonFileName) throws IOException {
		return createFile (BaseInit.TEST_CONTROLLER_DIR + jsonFileName + ".json");
	}

	/**
	 *
	 * @param jsonFileName
	 * @param jsonObject
	 */
	public static void writeInJsonFile (String jsonFileName, JSONObject jsonObject) throws IOException {
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