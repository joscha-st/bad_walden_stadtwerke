package com.bad_walden_stadtwerke.sales.types;

import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import javafx.geometry.Insets;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.ResourceBundle;

import static com.bad_walden_stadtwerke.communication.BadWJsonParser.extractKeyValuePairs;
import static com.bad_walden_stadtwerke.communication.BadWJsonParser.trimPart;

public class Tariff implements BadWObjectBuildableFromJsonParser {
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

    public Tariff() {};

    public void fillFromJson(String json) {
        String[] keyValuePairs = extractKeyValuePairs(json);

        for (String pair : keyValuePairs) {
            String[] parts = pair.split(":(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();

                key = trimPart(key);
                value = trimPart(value);

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