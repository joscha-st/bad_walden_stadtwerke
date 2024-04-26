package com.bad_walden_stadtwerke.communication;

import com.bad_walden_stadtwerke.sales.types.BadWObjectBuildableFromJsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BadWJsonParser {

    public static <T extends BadWObjectBuildableFromJsonParser> List<T> parseJson(String json, Supplier<T> supplier) {
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

    public static String trimPart(String key) {
        if (key.startsWith("\"") && key.endsWith("\"")) {
            key = key.substring(1, key.length() - 1);
        }
        return key;
    }

    public static String[] extractKeyValuePairs(String json) {
        json = json.trim();
        json = json.substring(1, json.length() - 1); // remove outer { and }

        return json.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    }

    public static String createJsonPair(String key, String value) {
        return "\"" + key + "\": \"" + value + "\"";
    }


}
