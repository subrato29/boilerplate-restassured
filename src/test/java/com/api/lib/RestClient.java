package com.api.lib;

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
		return getHeader()
			.get(endpoint);
	}

	/**
	 *
	 * @return
	 */
	public static RequestSpecification getHeader() {
		return RestAssured.given()
			.header(AUTHORIZATION, BEARER_TOKEN);
	}

	/**
	 *
	 * @return
	 */
	public static RequestSpecification request() {
		return getHeader()
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
		return request()
			.body(requestBody)
			.when()
			.post(endpoint);
	}

	/**
	 *
	 * @param endpoint
	 * @param requestBody
	 * @return
	 */
	public static Response put(String endpoint, JSONObject requestBody) {
		return request()
			.body(requestBody)
			.when()
			.put(endpoint);
	}

	/**
	 *
	 * @param endpoint
	 * @return
	 */
	public static Response delete(String endpoint) {
		return request()
			.when()
			.delete(endpoint);
	}

	/**
	 *
	 * @param endpoint
	 * @param requestBody
	 * @return
	 */
	public static Response patch(String endpoint, JSONObject requestBody) {
		return request()
			.body(requestBody)
			.when()
			.patch(endpoint);
	}
}