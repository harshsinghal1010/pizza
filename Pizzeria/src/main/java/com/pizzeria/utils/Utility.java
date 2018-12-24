package com.pizzeria.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
	
	public static Map<String,String> getFailureMap() {
		Map<String,String> failureMap = new HashMap<String,String>();
		failureMap.put("status", "failure");
		return failureMap;
	}
	
	public static Map<String,String> getSuccessMap() {
		Map<String,String> successMap = new HashMap<String,String>();
		successMap.put("status", "success");
		return successMap;
	}
	
	public static JSONObject getFailureJson() {
		JSONObject failureJson = new JSONObject();
		try {
			failureJson.put("status", "failure");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return failureJson;
	}
	
	public static JSONObject getSuccessJson() {
		JSONObject successJson = new JSONObject();
		try {
			successJson.put("status", "success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return successJson;
	}
				
}
