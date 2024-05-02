package com.bad_walden_stadtwerke.controller.initialSignUp;

import com.bad_walden_stadtwerke.components.errorHandling.ExceptionPopup;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import com.bad_walden_stadtwerke.model.initialSignUp.SignUpManager;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ResourceBundle;


public class PopupController {
	private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;

	@FXML
	private CheckBox checkBox;
	@FXML
	private GridPane gridPane;


	public CheckBox getCheckBox() {
		return checkBox;
	}


	public void setGridPane(GridPane gridPane) {
		this.gridPane.getChildren().clear();
		this.gridPane.getChildren().addAll(gridPane.getChildren());
	}


	@FXML
	public void cancel() {
		checkBox.setSelected(false);
		Stage stage = (Stage) checkBox.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void confirm() {
		if (!checkBox.isSelected()) {
			ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
			ExceptionPopup.showErrorPopup(bundle.getString("tariffSelectionConfirmationErrorLabel"), bundle.getString("tariffSelectionConfirmationNoCheckoxErrorLabel"));
		}
		Stage stage = (Stage) checkBox.getScene().getWindow();
		stage.close();
	}
}