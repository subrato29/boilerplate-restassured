package com.api.lib;

import com.api.reports.ReportUtil;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient extends AuthFactory {

	private static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER_TOKEN = "Bearer " + TOKEN;

	/**
	 *
	 * @param endpoint
	 * @return
	 */
	public static Response get(String endpoint) {
		try {
			return requestHeader()
					.get(endpoint);
		} catch (Throwable t) {
			ReportUtil.markFailed("GET call is not successful");
			t.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @return
	 */
	public static RequestSpecification requestHeader() {
		return RestAssured.given()
			.header(AUTHORIZATION, BEARER_TOKEN);
	}

	/**
	 *
	 * @return
	 */
	public static RequestSpecification request() {
		return requestHeader()
			.accept(APPLICATION_JSON_CONTENT_TYPE)
			.contentType(APPLICATION_JSON_CONTENT_TYPE);
	}

	/**
	 *
	 * @param endpoint
	 * @param requestBody
	 * @return
	 */
	public static Response post(String endpoint, JSONObject requestBody) {
		try {
			return request()
					.body(requestBody)
					.when()
					.post(endpoint);
		} catch (Throwable t) {
			ReportUtil.markFailed("POST call is not successful");
			t.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param endpoint
	 * @param requestBody
	 * @return
	 */
	public static Response put(String endpoint, JSONObject requestBody) {
		try {
			return request()
					.body(requestBody)
					.when()
					.put(endpoint);
		} catch (Throwable t) {
			ReportUtil.markFailed("PUT call is not successful");
			t.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param endpoint
	 * @return
	 */
	public static Response delete(String endpoint) {
		try {
			return request()
					.when()
					.delete(endpoint);
		} catch (Throwable t) {
			ReportUtil.markFailed("DELETE call is not successful");
			t.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param endpoint
	 * @param requestBody
	 * @return
	 */
	public static Response patch(String endpoint, JSONObject requestBody) {
		try {
			return request()
					.body(requestBody)
					.when()
					.patch(endpoint);
		} catch (Throwable t) {
			ReportUtil.markFailed("PATCH call is not successful");
			t.printStackTrace();
		}
		return null;
	}
}