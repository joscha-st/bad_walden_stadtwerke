package com.bad_walden_stadtwerke.utility;

import com.bad_walden_stadtwerke.controller.language.LanguageController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * The FXMLUtility class provides utility methods for loading FXML files and setting scenes on stages in JavaFX applications.
 * It encapsulates the loading and setting of FXML scenes, along with handling exceptions using a central logging utility.
 */
public class FXMLUtility {

	/**
	 * The path to the FXML file.
	 */
	private final String fxmlPath;

	/**
	 * The name of the resource bundle for internationalization.
	 */
	private final String bundleName;

	/**
	 * The stage where the FXML scene will be set.
	 */
	private final Stage stage;

	/**
	 * Constructs an FXMLUtility object with the specified FXML file path, resource bundle name, and event.
	 *
	 * @param fxmlPath   The path to the FXML file.
	 * @param bundleName The name of the resource bundle for internationalization.
	 * @param event      The event from which to obtain the stage.
	 */
	public FXMLUtility(String fxmlPath, String bundleName, Event event) {
		this.fxmlPath = fxmlPath;
		this.bundleName = bundleName;
		this.stage = getStageFromEvent(event);
	}

	/**
	 * Constructs an FXMLUtility object with the specified FXML file path, resource bundle name, and stage.
	 *
	 * @param fxmlPath   The path to the FXML file.
	 * @param bundleName The name of the resource bundle for internationalization.
	 * @param stage      The stage where the FXML scene will be set.
	 */
	public FXMLUtility(String fxmlPath, String bundleName, Stage stage) {
		this.fxmlPath = fxmlPath;
		this.bundleName = bundleName;
		this.stage = stage;
	}

	/**
	 * Retrieves the stage from the specified event.
	 *
	 * @param event The event from which to obtain the stage.
	 * @return The stage associated with the event.
	 */
	public static Stage getStageFromEvent(Event event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	/**
	 * Loads the FXML file, sets the scene on the stage, and displays the stage.
	 */
	public void loadAndSetScene() {
		try {
			Parent root = loadFXML();
			setSceneToStage(root);
			stage.show();
		} catch (Exception e) {
			CentralLoggingUtility.handleException("UI", e);
			e.printStackTrace();
		}
	}

	/**
	 * Loads the FXML file using FXMLLoader and sets the resource bundle for internationalization.
	 *
	 * @return The root node of the loaded FXML file.
	 * @throws IOException if an error occurs during loading.
	 */
	private Parent loadFXML() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
		fxmlLoader.setResources(ResourceBundle.getBundle(bundleName, LanguageController.getLanguage()));
		return fxmlLoader.load();
	}

	/**
	 * Sets the loaded scene to the stage.
	 *
	 * @param root The root node of the loaded FXML file.
	 */
	private void setSceneToStage(Parent root) {
		stage.setScene(new Scene(root));
	}
}