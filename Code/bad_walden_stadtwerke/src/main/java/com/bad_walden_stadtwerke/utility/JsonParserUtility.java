package com.bad_walden_stadtwerke.utility;

import com.bad_walden_stadtwerke.model.types.ObjectBuildableFromJsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * The JsonParserUtility class provides utility methods for parsing JSON strings and manipulating JSON data.
 * It includes methods for parsing JSON into objects, trimming JSON strings, extracting key-value pairs, and creating JSON pairs.
 */
public class JsonParserUtility {

	/**
	 * Parses a JSON string into a list of objects of type T using a supplier to create instances of T.
	 *
	 * @param json     The JSON string to parse.
	 * @param supplier A supplier function used to create instances of type T.
	 * @param <T>      The type of objects to parse the JSON into.
	 * @return A list of objects parsed from the JSON string.
	 */
	public static <T extends ObjectBuildableFromJsonParser> List<T> parseJson(String json, Supplier<T> supplier) {
		List<T> items = new ArrayList<>();

		json = trimJson(json);

		int bracketCount = 0;
		int start = 0;

		for (int i = 0; i < json.length(); i++) {
			if (json.charAt(i) == '{') {
				bracketCount++;
				if (bracketCount == 1)
					start = i;
			} else if (json.charAt(i) == '}') {
				bracketCount--;
				if (bracketCount == 0) {
					T item = supplier.get();
					item.fillFromJson(json.substring(start, i + 1));
					items.add(item);
				}
			}
		}

		return items;
	}

	/**
	 * Trims the outer brackets from a JSON string (if present).
	 *
	 * @param json The JSON string to trim.
	 * @return The trimmed JSON string.
	 */
	private static String trimJson(String json) {
		json = json.trim();
		if (json.startsWith("[")) {
			json = json.substring(1);
		}
		if (json.endsWith("]")) {
			json = json.substring(0, json.length() - 1);
		}
		return json;
	}

	/**
	 * Trims quotation marks from the beginning and end of a string (if present).
	 *
	 * @param key The string to trim.
	 * @return The trimmed string.
	 */
	public static String trimPart(String key) {
		if (key.startsWith("\"") && key.endsWith("\"")) {
			key = key.substring(1, key.length() - 1);
		}
		return key;
	}

	/**
	 * Extracts key-value pairs from a JSON object string.
	 *
	 * @param json The JSON object string from which to extract key-value pairs.
	 * @return An array of key-value pairs.
	 */
	public static String[] extractKeyValuePairs(String json) {
		json = json.trim();
		json = json.substring(1, json.length() - 1); // remove outer { and }

		return json.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	}

	/**
	 * Creates a JSON key-value pair string.
	 *
	 * @param key   The key of the pair.
	 * @param value The value of the pair.
	 * @return The JSON key-value pair string.
	 */
	public static String createJsonPair(String key, String value) {
		return "\"" + key + "\": \"" + value + "\"";
	}


}
