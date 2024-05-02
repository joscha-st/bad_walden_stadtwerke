package com.bad_walden_stadtwerke.controller.initialSignUp;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PopupController {
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
		Stage stage = (Stage) checkBox.getScene().getWindow();
		stage.close();
	}
}