package com.api.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.utilities.CommonUtils;
import org.json.simple.JSONObject;
import com.api.reports.ReportUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiUtils extends HttpUtils {

	/**
	 *
	 * @param URI
	 * @param response
	 */
	public static void validateResponseHeader (String URI, Response response) {
		if (response.header("content-type").contains("application/json")) {
			ReportUtil.markPassed("Response header is correctly validated");
		} else {
			ReportUtil.markFailed("Reponse header is not validated");
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

	/**
	 *
	 * @param request_body
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject parse_json_to_create_tracks_to_playlist(String request_body) {
		JSONObject json = new JSONObject();
		String[] array_uri = getSpotifyURI_ByTrackId(request_body);
		json.put("uris", array_uri);
		return json;
	}

	/**
	 *
	 * @param trackId
	 * @return
	 */
	public static String[] getSpotifyURI_ByTrackId (String trackId) {
		List<String> list = new ArrayList<String>();
		String[] track = trackId.split(",");
		for (int i = 0; i < track.length; i++) {
			list.add("spotify:track:" + track[i].trim());
		}
		return list.toArray(new String[list.size()]);
	}
}