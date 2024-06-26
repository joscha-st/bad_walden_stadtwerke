/**
 * The TariffTableDisplay class provides methods to display tariffs in a table format with JavaFX.
 * It includes methods to create a table with tariff details, select tariffs, and show more details about each tariff.
 * <p>
 * Example usage:
 * <pre>
 *     ArrayList<Tariff> tariffs = new ArrayList<>();
 *     VBox header = new VBox();
 *     ScrollPane scrollPane = new ScrollPane();
 *     TariffTableDisplay tariffTableDisplay = new TariffTableDisplay(scrollPane, tariffs, header);
 * </pre>
 * </p>
 * <p>
 * This class depends on the {@link com.bad_walden_stadtwerke.model.types.Tariff} class
 * to represent tariff objects and the {@link com.bad_walden_stadtwerke.controller.language.LanguageController}
 * class to retrieve localized messages.
 * </p>
 * <p>
 * It provides methods to create a table of tariffs, handle tariff selection, and display more details about each tariff.
 * The table is displayed within a scroll pane, and each tariff is represented as a row in the table.
 * </p>
 * <p>
 * The class also provides options to customize the appearance of the table, such as hiding the select button.
 * </p>
 * <p>
 * This class supports internationalization by using resource bundles to retrieve localized messages.
 * </p>
 * @author com.bad_walden_stadtwerke.components
 * @version 1.0
 */
package com.bad_walden_stadtwerke.components;

import com.bad_walden_stadtwerke.model.types.Tariff;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class TariffTableDisplay {
	private static final String FONT_SIZE = "16px";
	private static final String FONT_FAMILY = "Arial";
	private static final String BUTTON_BACKGROUND_COLOR = "-fx-background-color: #ffffff";
	private static final String BUTTON_SELECTED_BACKGROUND_COLOR = "-fx-background-color: #ffcc00";
	private static final double COLUMN_WIDTH_SMALL = 80;
	private static final double COLUMN_WIDTH_MEDIUM = 125;
	private static final double COLUMN_WIDTH_LARGE = 175;
	private static final int SPACING = 10;
	private static final int PADDING = 10;
	private static final int MAX_HEIGHT = 100;

	private final ScrollPane scrollPane;
	private final ArrayList<Tariff> tariffs;
	private final VBox header;
	private final ResourceBundle bundle;
	private Tariff selectedTariff;
	private boolean hideSelectButton = false;

/**
     * Constructs a TariffTableDisplay object with the specified parameters.
     *
     * @param scrollPane the scroll pane to contain the tariff table
     * @param tariffs    the list of tariffs to display in the table
     * @param header     the header containing column labels for the table
     */
	public TariffTableDisplay(ScrollPane scrollPane, ArrayList<Tariff> tariffs, VBox header) {
		this.scrollPane = scrollPane;
		this.tariffs = tariffs;
		this.header = header;
		this.selectedTariff = null;
		this.bundle = ResourceBundle.getBundle("bundle", LanguageController.getLanguage());

		setupHeader();
		displayTariffs();
	}

	/**
     * Constructs a TariffTableDisplay object with the specified parameters and hides the select button if specified.
     *
     * @param scrollPane       the scroll pane to contain the tariff table
     * @param tariffs          the list of tariffs to display in the table
     * @param header           the header containing column labels for the table
     * @param hideSelectButton true to hide the select button, false otherwise
     */
	public TariffTableDisplay(ScrollPane scrollPane, ArrayList<Tariff> tariffs, VBox header, boolean hideSelectButton) {
		this.scrollPane = scrollPane;
		this.tariffs = tariffs;
		this.header = header;
		this.selectedTariff = null;
		this.bundle = ResourceBundle.getBundle("bundle", LanguageController.getLanguage());
		this.hideSelectButton = hideSelectButton;

		setupHeader();
		displayTariffs();
	}

	/**
     * Retrieves the currently selected tariff.
     *
     * @return the currently selected tariff
     */
	public Tariff getSelectedTariff() {
		return selectedTariff;
	}

	/**
     * Sets whether to hide the select button for tariffs in the table.
     *
     * @param hideSelectButton true to hide the select button, false otherwise
     */
	public void setHideSelectButton(boolean hideSelectButton) {
		this.selectedTariff = null;
		this.hideSelectButton = hideSelectButton;

		this.displayTariffs();
	}

	private void displayTariffs() {
		VBox content = new VBox(SPACING);
		scrollPane.setContent(content);

		tariffs.forEach(tariff -> content.getChildren().add(createTariffRow(tariff, content)));
	}

	private HBox createTariffRow(Tariff tariff, VBox content) {
		HBox row = new HBox(SPACING);
		row.setPadding(new Insets(0, PADDING, 0, PADDING));
		row.getChildren().addAll(createLabel(tariff.getName(), COLUMN_WIDTH_MEDIUM), createLabel(tariff.getDescription(), COLUMN_WIDTH_LARGE, MAX_HEIGHT), createLabel(tariff.getPrice() + " ct / " + tariff.getUnit(), COLUMN_WIDTH_SMALL), createLabel(tariff.getMinDuration() + " " + bundle.getString("months"), COLUMN_WIDTH_MEDIUM), createLabel(tariff.getCancellationPeriod() + " " + bundle.getString("months"), COLUMN_WIDTH_MEDIUM), createSelectButton(tariff, content), createMoreButton(tariff));

		return row;
	}

	private Label createLabel(String text, double width) {
		return createLabel(text, width, Double.MAX_VALUE);
	}

	private Label createLabel(String text, double width, double maxHeight) {
		Label label = new Label(text);
		label.setPrefWidth(width);
		label.setMaxWidth(width);
		label.setMaxHeight(maxHeight);
		label.setEllipsisString("...");
		label.setWrapText(true);
		label.setStyle("-fx-font-family: " + FONT_FAMILY + "; -fx-font-size: " + FONT_SIZE + ";");
		return label;
	}

	private StackPane createSelectButton(Tariff tariff, VBox content) {
		if (hideSelectButton) {
			return new StackPane();
		}
		Button button = new Button(bundle.getString("tariffSelectButton"));
		button.setStyle(BUTTON_BACKGROUND_COLOR + "; -fx-font-family: " + FONT_FAMILY + "; -fx-font-size: " + FONT_SIZE + ";");
		button.setOnAction(event -> {
			this.selectedTariff = tariff;
			content.getChildren().stream().map(node -> (HBox) node).flatMap(hBox -> hBox.getChildren().stream()).forEach(this::resetNodeStyle);
			button.setStyle(BUTTON_SELECTED_BACKGROUND_COLOR + "; -fx-font-family: " + FONT_FAMILY + "; -fx-font-size: " + FONT_SIZE + ";");
		});

		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(button);
		StackPane.setAlignment(button, Pos.CENTER);

		return stackPane;
	}

	private void resetNodeStyle(Node node) {
		if (node instanceof StackPane) {
			StackPane stackPane = (StackPane) node;
			Button button = (Button) stackPane.getChildren().get(0);
			button.setStyle(BUTTON_BACKGROUND_COLOR + "; -fx-font-family: " + FONT_FAMILY + "; -fx-font-size: " + FONT_SIZE + ";");
		}
	}

	private StackPane createMoreButton(Tariff tariff) {
		Button moreButton = new Button(bundle.getString("tariffShowMoreButton"));
		moreButton.setStyle(BUTTON_BACKGROUND_COLOR + "; -fx-font-family: " + FONT_FAMILY + "; -fx-font-size: " + FONT_SIZE + ";");
		moreButton.setOnAction(event -> showAlert(tariff));

		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(moreButton);
		StackPane.setAlignment(moreButton, Pos.CENTER);

		return stackPane;
	}

	private void showAlert(Tariff tariff) {
		Dialog<Object> dialog = new Dialog<>();
		dialog.setTitle(bundle.getString("tariffDetailsTitle"));
		dialog.setDialogPane(getDialogPane(tariff));
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.showAndWait();
	}

	private void setupHeader() {
		header.getChildren().clear();

		HBox headerRow = new HBox(SPACING);
		headerRow.setPadding(new Insets(0, PADDING, 0, PADDING));

		headerRow.getChildren().addAll(createLabel(bundle.getString("tariffNameHeader"), COLUMN_WIDTH_MEDIUM), createLabel(bundle.getString("tariffDescriptionHeader"), COLUMN_WIDTH_LARGE), createLabel(bundle.getString("tariffPriceHeader"), COLUMN_WIDTH_SMALL), createLabel(bundle.getString("tariffMinDurationHeader"), COLUMN_WIDTH_MEDIUM), createLabel(bundle.getString("tariffCancellationPeriodHeader"), COLUMN_WIDTH_MEDIUM));

		header.getChildren().add(headerRow);
	}


	public DialogPane getDialogPane(Tariff tariff) {
		DialogPane dialogPane = new DialogPane();

		GridPane grid = getGridPane(tariff);
		dialogPane.setContent(grid);

		return dialogPane;
	}

	public static GridPane getGridPane(Tariff tariff) {
		GridPane grid = createTariffGridPane();
		ResourceBundle bundle = ResourceBundle.getBundle("bundle", LanguageController.getLanguage());

		grid.add(createTariffLabel(bundle.getString("tariffName"), tariff.getName()), 0, 0);
		grid.add(createTariffLabel(bundle.getString("tariffDescription"), tariff.getDescription()), 0, 1);
		grid.add(createTariffLabel(bundle.getString("tariffPrice"), Integer.toString(tariff.getPrice())), 0, 2);
		grid.add(createTariffLabel(bundle.getString("tariffUnit"), "ct / " + tariff.getUnit()), 0, 3);
		grid.add(createTariffLabel(bundle.getString("tariffMinDuration"), Integer.toString(tariff.getMinDuration())), 0, 4);
		grid.add(createTariffLabel(bundle.getString("tariffCancellationPeriod"), Integer.toString(tariff.getMinDuration())), 0, 5);

		return grid;
	}

	private static GridPane createTariffGridPane() {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 10, 10, 10));
		return grid;
	}

	private static HBox createTariffLabel(String labelText, String valueText) {
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