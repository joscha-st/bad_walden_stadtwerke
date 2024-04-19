package com.bad_walden_stadtwerke.ui.components.errorHandling;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExceptionPopup {

    public static void showErrorPopup(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
