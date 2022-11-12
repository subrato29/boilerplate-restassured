package com.api.lib;

import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import com.api.reports.ReportUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpUtils extends AuthFactory {

	/**
	 *
	 * @param URI
	 * @return
	 */
	public static Response get(String URI) {
		RequestSpecification request = RestAssured.given();
		Response response = request
				.header("Authorization", "Bearer " + BEARER_TOKEN)
				.get(URI);
		return response;
	}

	/**
	 *
	 * @return
	 */
	public static RequestSpecification request() {
		RequestSpecification request = RestAssured.given();
		return request.header("Authorization", "Bearer " + BEARER_TOKEN);
	}

	/**
	 *
	 * @param endpoint
	 * @param json
	 * @return
	 */
	public static Response post(String endpoint, JSONObject json) {
		try {
			Response response = given()
					.header("Authorization", "Bearer " + BEARER_TOKEN)
					.accept("application/json")
					.contentType("application/json")
					.body(json)
					.when()
					.post(endpoint);
			return response;
		} catch (Throwable t) {
			ReportUtil.markFailed("POST method is failed");
			t.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * @param endpoint
	 * @param json
	 * @return
	 */
	public static Response put(String endpoint, JSONObject json) {
		try {
			Response response = given()
					.header("Authorization", "Bearer " + BEARER_TOKEN)
					.accept("application/json")
					.contentType("application/json")
					.body(json)
					.when()
					.put(endpoint);
			return response;
		} catch (Throwable t) {
			ReportUtil.markFailed("PUT command is failed");
			t.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * @param endpoint
	 * @param json
	 * @return
	 */
	public static Response delete(String endpoint) {
		RequestSpecification request = RestAssured.given();
		Response response = request.get(endpoint);
		response = given()
				.header("Authorization", "Bearer " + BEARER_TOKEN)
				.accept("application/json")
				.contentType("application/json")
				.when()
				.delete(endpoint);
		return response;
	}

	/**
	 *
	 * @param endpoint
	 * @param json
	 * @return
	 */
	public static Response patch(String endpoint, JSONObject json) {
		Response response = given()
				.header("Authorization", "Bearer " + BEARER_TOKEN)
				.accept("application/json")
				.contentType("application/json")
				.body(json)
				.when()
				.patch(endpoint);
		return response;
	}
}