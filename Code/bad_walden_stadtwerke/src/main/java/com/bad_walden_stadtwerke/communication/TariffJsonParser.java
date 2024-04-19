package com.bad_walden_stadtwerke.communication;

import com.bad_walden_stadtwerke.sales.types.Tariff;

import java.util.ArrayList;
import java.util.List;


public class TariffJsonParser {

    public static List<Tariff> parseJson(String json) {
        List<Tariff> tariffs = new ArrayList<>();

        json = json.trim();
        if (json.startsWith("[")) {
            json = json.substring(1);
        }
        if (json.endsWith("]")) {
            json = json.substring(0, json.length() - 1);
        }

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
                    tariffs.add(parseTariff(json.substring(start, i + 1)));
                }
            }
        }

        return tariffs;
    }


    private static Tariff parseTariff(String json) {
        int id = -1;
        String name = null;
        String description = null;
        int price = -1;
        String unit = null;
        String category = null;

        json = json.trim();
        json = json.substring(1, json.length() - 1); // remove outer { and }

        // Split on comma not enclosed in quotes
        String[] keyValuePairs = json.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        for (String pair : keyValuePairs) {
            // Split on colon not enclosed in quotes
            String[] parts = pair.split(":(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();

                if (key.startsWith("\"") && key.endsWith("\"")) {
                    key = key.substring(1, key.length() - 1);
                }
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1);
                }

                switch (key) {
                    case "id":
                        id = Integer.parseInt(value);
                        break;
                    case "name":
                        name = value;
                        break;
                    case "description":
                        description = value;
                        break;
                    case "price":
                        price = Integer.parseInt(value);
                        break;
                    case "unit":
                        unit = value;
                        break;
                    case "category":
                        category = value;
                        break;
                    default:
                        break;
                }
            }
        }

        return new Tariff(id, name, description, price, unit, category);
    }

}
