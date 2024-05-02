package com.bad_walden_stadtwerke.model.types;

import com.bad_walden_stadtwerke.utility.JsonParserUtility;

public class Tariff implements ObjectBuildableFromJsonParser {
	private int id;
	private String name;
	private String description;
	private int price;
	private String unit;
	private int minDuration;
	private int cancellationPeriod;
	private String category;

	public Tariff(int id, String name, String description, int price, String unit, int minDuration, int cancellationPeriod, String category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.unit = unit;
		this.minDuration = minDuration;
		this.cancellationPeriod = cancellationPeriod;
		this.category = category;
	}

	public Tariff() {
	}

	public void fillFromJson(String json) {
		String[] keyValuePairs = JsonParserUtility.extractKeyValuePairs(json);

		for (String pair : keyValuePairs) {
			String[] parts = pair.split(":(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

			if (parts.length == 2) {
				String key = parts[0].trim();
				String value = parts[1].trim();

				key = JsonParserUtility.trimPart(key);
				value = JsonParserUtility.trimPart(value);

				fillObjectVarWithJsonValueOfCorrespondingKey(key, value);
			}
		}
	}

	private void fillObjectVarWithJsonValueOfCorrespondingKey(String key, String value) {
		switch (key) {
			case "id":
				this.id = Integer.parseInt(value);
				break;
			case "name":
				this.name = value;
				break;
			case "description":
				this.description = value;
				break;
			case "price":
				this.price = Integer.parseInt(value);
				break;
			case "unit":
				this.unit = value;
				break;
			case "minDuration":
				this.minDuration = Integer.parseInt(value);
				break;
			case "cancellationPeriod":
				this.cancellationPeriod = Integer.parseInt(value);
				break;
			case "category":
				this.category = value;
				break;
			default:
				break;
		}
	}

	public String idToJson() {
		return "{" + JsonParserUtility.createJsonPair("id", String.valueOf(this.id)) + "}";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}

	public String getUnit() {
		return unit;
	}

	public String getCategory() {
		return category;
	}

	public int getMinDuration() {
		return minDuration;
	}

	public int getCancellationPeriod() {
		return cancellationPeriod;
	}
}