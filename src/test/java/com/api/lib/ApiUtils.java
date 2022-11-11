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
	 * Keyword: validateReponsePayload
	 * Author: Subrato Sarkar
	 * Date: 11/10/2020
	 * @return responseCode
	 */
	public static void validateResponsePayload (String requestPayLoad, String URI) {
		JsonPath jsonPath = get(URI).jsonPath();
		String[] reqPayLoad = requestPayLoad.split(",");
		String key = null, value = null;
		int count = 0;
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < reqPayLoad.length; i++) {
			key = reqPayLoad[i].split(":")[0].trim();
			value = reqPayLoad[i].split(":")[1].trim();
			if (String.valueOf(jsonPath.get(key.toLowerCase())).equals(value)) {
				count ++;
			} else {
				map.put(key, value);
			}
		}
		if (count == reqPayLoad.length) {
			ReportUtil.markPassed("Response payload values is verified successfully with request payload");
		} else {
			ReportUtil.markFailed("Unverified: " + map.toString());
		}
	}
	
	/**
	 * Keyword: validateReponseHeader
	 * Author: Subrato Sarkar
	 * Date: 11/10/2020
	 * @return responseCode
	 */
	public static void validateResponseHeader (String URI, Response response) {
		if (response.header("content-type").contains("application/json")) {
			ReportUtil.markPassed("Response header is correctly validated");
		} else {
			ReportUtil.markFailed("Reponse header is not validated");
		}
	}
	
	public static long getResponseTime (Response response) {
		return response.getTime();
	}
	/**
	 *
	 * @param request_body
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject generateJsonRequestBody () {
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
	 * @param request_body
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject parse_json_to_delete_tracks_from_playlist (String request_body) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		String[] array = request_body.split(",");
		for (int i = 0; i < array.length; i++) {
			json.put("uri", "spotify:track:" + array[i].trim());
			list.add(json);
			json = new JSONObject();
		}
		json = new JSONObject();
		json.put("tracks", list);
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