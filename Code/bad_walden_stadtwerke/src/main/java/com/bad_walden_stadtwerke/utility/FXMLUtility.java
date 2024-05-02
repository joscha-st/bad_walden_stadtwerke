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

public class FXMLUtility {

	private final String fxmlPath;
	private final String bundleName;
	private final Stage stage;

	public FXMLUtility(String fxmlPath, String bundleName, Event event) {
		this.fxmlPath = fxmlPath;
		this.bundleName = bundleName;
		this.stage = getStageFromEvent(event);
	}

	public static Stage getStageFromEvent(Event event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

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

	private Parent loadFXML() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
		fxmlLoader.setResources(ResourceBundle.getBundle(bundleName, LanguageController.getLanguage()));
		return fxmlLoader.load();
	}

	private void setSceneToStage(Parent root) {
		stage.setScene(new Scene(root));
	}
}