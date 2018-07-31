package com.itis.shaca.monitor.utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {

	public static JSONObject toJsonObject(String str) {
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(str);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return jsonObj;
	}

	public static JSONArray toJsonArray(String str) {
		JSONArray jsonArr = null;

		try {
			jsonArr = new JSONArray(str);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return jsonArr;
	}

	public static List toList(JSONArray jsonArray) {
		return IntStream.range(0, jsonArray.length()).mapToObj(i -> {
			try {
				return jsonArray.get(i);
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList());
	}

	public static String toJsonString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in String
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	public static <T> T toObject(String jsonString, Class<T> clazz) {
		// JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		T obj = null;
		try {
			obj = mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static boolean isValid(final String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
		boolean valid;
		try {
			objectMapper.readTree(json);
			valid = true;
		} catch (IOException e) {
			valid = false;
		}
		return valid;
	}
}
