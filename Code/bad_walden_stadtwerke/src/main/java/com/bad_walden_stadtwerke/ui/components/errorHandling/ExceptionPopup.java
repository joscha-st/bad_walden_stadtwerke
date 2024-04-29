package com.bad_walden_stadtwerke.ui.components.errorHandling;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExceptionPopup {

    public static void showErrorPopup(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        if (message.contains(":")) { //make the message more user friendly and less technical.
            message = message.substring(message.indexOf(':') + 1).trim();
        }
        alert.setContentText(message);
        alert.showAndWait();
    }

}
