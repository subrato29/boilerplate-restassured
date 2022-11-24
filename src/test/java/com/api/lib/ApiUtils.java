package com.api.lib;

import com.api.utilities.CommonUtils;
import org.json.simple.JSONObject;
import com.api.reports.ReportUtil;

import io.restassured.response.Response;

public class ApiUtils extends RestClient {

	/**
	 *
	 * @param URI
	 * @param response
	 */
	public static void validateResponseHeader (String URI, Response response) {
		if (response.header("content-type").contains("application/json")) {
			ReportUtil.markPassed("Response header is correctly validated");
		} else {
			ReportUtil.markFailed("Response header is not validated");
		}
	}

	/**
	 *
	 * @param response
	 * @return
	 */
	public static long getResponseTime (Response response) {
		return response.getTime();
	}

	/**
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject generateRequestBody () {
		JSONObject json = new JSONObject();
		String random = CommonUtils.getRandomString (6);
		json.put("name", random);
		json.put("gender", "male");
		json.put("email", random + "@email.com");
		json.put("status", "active");
		return json;
	}

	/**
	 *
	 * @return
	 */
	public static JSONObject updateRequestBody (JSONObject requestBody) {
		JSONObject json = new JSONObject();
		requestBody.forEach((key, value) -> {
			if (key.equals("name")) {
				json.put(key, value + "_UPDATED");
			}
		});
		return json;
	}
}