/**
 * The ExceptionPopup class provides utility methods for displaying error popups using JavaFX Alert.
 * It includes a method to show an error popup and a method to process error messages.
 * <p>
 * Example usage:
 * <pre>
 *    ExceptionPopup.showErrorPopup("Error Title", "Error message to be displayed");
 * </pre>
 * </p>
 * @author com.bad_walden_stadtwerke.components.errorHandling
 * @version 1.0
 */

package com.bad_walden_stadtwerke.components.errorHandling;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExceptionPopup {

	/**
     * Shows an error popup with the specified title and message.
     *
     * @param title   the title of the error popup
     * @param message the message to be displayed in the error popup
     */

	public static void showErrorPopup(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(title);
		alert.setContentText(processMessage(message));
		alert.showAndWait();
	}

    /**
     * Shows an information popup with the call to refresh..
     *
     * @param title   the title of the popup
     * @param message the message to be displayed in the popup
     */
    public static void showRefreshPopup(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(title);
		alert.setContentText(processMessage(message));
		alert.showAndWait();
	}

	 /**
     * Processes the error message to remove any leading identifier or metadata.
     *
     * @param message the error message to be processed
     * @return the processed error message
     */
	public static String processMessage(String message) {
		if (message.contains(":")) {
			return message.substring(message.indexOf(':') + 1).trim();
		}
		return message;
	}
}
