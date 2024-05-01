/**
 * Provides utilities for error handling in JavaFX applications.
 * The {@code ExceptionPopup} class contains methods to display error messages in a popup window.
 */
package com.bad_walden_stadtwerke.components.errorHandling;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * A utility class for displaying error messages as popups in JavaFX applications.
 * This class uses JavaFX's {@link Alert} class to show errors in a standardized format.
 */
public class ExceptionPopup {

    /**
     * Displays an error popup with a specified title and message.
     * The message is processed to potentially modify its content before display.
     *
     * @param title   the title of the error popup, displayed as the header text.
     * @param message the original error message which may contain additional formatting
     *                to be processed by {@link #processMessage(String)} method.
     */
    public static void showErrorPopup(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(processMessage(message));
        alert.showAndWait();
    }

    /**
     * Processes the error message to improve readability in the popup.
     * If the message contains a colon (':'), the method returns the substring following
     * the first colon, trimmed of leading and trailing whitespace. Otherwise, it returns
     * the original message.
     *
     * @param message the error message that may contain special formatting
     * @return a possibly modified version of the original error message.
     */
    public static String processMessage(String message) {
        if (message.contains(":")) {
            return message.substring(message.indexOf(':') + 1).trim();
        }
        return message;
    }
}
