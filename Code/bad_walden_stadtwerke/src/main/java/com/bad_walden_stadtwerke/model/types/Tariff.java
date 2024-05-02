/**
 * The Tariff class represents a tariff object with various properties such as ID, name, description, price, unit, minDuration,
 * cancellation period, and category.
 * <p>
 * This class implements the {@link ObjectBuildableFromJsonParser} interface, allowing it to be filled from JSON data.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.model.types;

import com.bad_walden_stadtwerke.utility.JsonParserUtility;

/**
     * Constructs a Tariff object with the specified properties.
     *
     * @param id                The ID of the tariff.
     * @param name              The name of the tariff.
     * @param description       The description of the tariff.
     * @param price             The price of the tariff.
     * @param unit              The unit of measurement for the tariff.
     * @param minDuration       The minimum duration of the tariff.
     * @param cancellationPeriod The cancellation period of the tariff.
     * @param category          The category of the tariff.
     */
public class Tariff implements ObjectBuildableFromJsonParser {
	private int id;
	private String name;
	private String description;
	private int price;
	private String unit;
	private int minDuration;
	private int cancellationPeriod;
	private String category;

	/**
     * Constructs an empty Tariff object.
     */
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

	/**
     * Fills the Tariff object's fields from JSON data.
     *
     * @param json The JSON string containing data to populate the tariff object.
     */
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

	/**
     * Fills the corresponding object variable with the JSON value of the given key.
     *
     * @param key   The key from JSON data.
     * @param value The value from JSON data.
     */
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

	/**
     * Converts the tariff ID to JSON format.
     *
     * @return The tariff ID in JSON format.
     */
	public String idToJson() {
		return "{" + JsonParserUtility.createJsonPair("id", String.valueOf(this.id)) + "}";
	}

	// Getters for the Tariff properties
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