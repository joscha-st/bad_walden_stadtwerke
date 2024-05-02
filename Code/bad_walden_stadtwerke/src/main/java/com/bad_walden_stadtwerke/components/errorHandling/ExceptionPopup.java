package com.bad_walden_stadtwerke.components.errorHandling;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExceptionPopup {

	public static void showErrorPopup(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(title);
		alert.setContentText(processMessage(message));
		alert.showAndWait();
	}

	public static void showRefreshPopup(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(title);
		alert.setContentText(processMessage(message));
		alert.showAndWait();
	}

	public static String processMessage(String message) {
		if (message.contains(":")) {
			return message.substring(message.indexOf(':') + 1).trim();
		}
		return message;
	}
}
