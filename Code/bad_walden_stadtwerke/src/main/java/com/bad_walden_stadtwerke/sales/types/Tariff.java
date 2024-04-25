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

    public Tariff() {
    }

    ;

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

    public DialogPane getDialogPane() {
        DialogPane dialogPane = new DialogPane();
        ResourceBundle bundle = ResourceBundle.getBundle("bundle", LanguageController.getLanguage());

        GridPane grid = createGridPane();

        grid.add(createLabel(bundle.getString("tariffName"), name), 0, 0);
        grid.add(createLabel(bundle.getString("tariffDescription"), description), 0, 1);
        grid.add(createLabel(bundle.getString("tariffPrice"), Integer.toString(price)), 0, 2);
        grid.add(createLabel(bundle.getString("tariffUnit"), unit), 0, 3);
        grid.add(createLabel(bundle.getString("tariffMinDuration"), Integer.toString(minDuration)), 0, 4);
        grid.add(createLabel(bundle.getString("tariffCancellationPeriod"), Integer.toString(cancellationPeriod)), 0, 5);

        dialogPane.setContent(grid);

        return dialogPane;
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));
        return grid;
    }

    private HBox createLabel(String labelText, String valueText) {
        Label label = new Label(labelText);
        label.setWrapText(true);
        label.setPrefWidth(120);
        label.setMaxWidth(120);
        label.setAlignment(Pos.TOP_LEFT);

        Label valueLabel = new Label(valueText);
        valueLabel.setWrapText(true);
        valueLabel.setMaxWidth(400);
        valueLabel.setAlignment(Pos.TOP_LEFT);

        label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-font-family: Arial;");
        valueLabel.setStyle("-fx-font-size: 14px; -fx-font-family: Arial;");

        return new HBox(label, valueLabel);
    }
}