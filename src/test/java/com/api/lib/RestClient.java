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
	 * @param json
	 * @return
	 */
	public static Response post(String endpoint, JSONObject json) {
		return request()
			.body(json)
			.when()
			.post(endpoint);
	}

	/**
	 *
	 * @param endpoint
	 * @param json
	 * @return
	 */
	public static Response put(String endpoint, JSONObject json) {
		return request()
			.body(json)
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
	 * @param json
	 * @return
	 */
	public static Response patch(String endpoint, JSONObject json) {
		return request()
			.body(json)
			.when()
			.patch(endpoint);
	}
}