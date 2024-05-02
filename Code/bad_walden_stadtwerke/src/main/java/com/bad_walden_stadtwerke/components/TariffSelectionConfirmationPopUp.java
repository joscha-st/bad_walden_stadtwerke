package com.bad_walden_stadtwerke.components;

import com.bad_walden_stadtwerke.components.errorHandling.ExceptionPopup;
import com.bad_walden_stadtwerke.controller.initialSignUp.PopupController;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import com.bad_walden_stadtwerke.model.initialSignUp.SignUpManager;
import com.bad_walden_stadtwerke.model.types.Tariff;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class TariffSelectionConfirmationPopUp {
	private static final String FXML_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/tariffConfirmationPopUp-dialog.fxml";
	private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;
	private CheckBox checkBox;
	private Stage dialogStage;
	private PopupController controller;
	private ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());

	public TariffSelectionConfirmationPopUp(Tariff tariff) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setResources(bundle);
			loader.setLocation(getClass().getResource(FXML_PATH));
			Pane page = loader.load();

			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.initModality(Modality.WINDOW_MODAL);
			DialogPane dialogPane = new DialogPane();
			dialogPane.setContent(page);
			dialog.setDialogPane(dialogPane);
			dialog.setTitle(bundle.getString("tariffSelectionConfirmationHeader"));
			dialog.setResizable(false);

			this.controller = loader.getController();
			this.checkBox = controller.getCheckBox();
			if (tariff != null) {
				this.controller.setGridPane(TariffTableDisplay.getGridPane(tariff));
			}

			this.dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();

		} catch (IOException e) {
			ExceptionPopup.showErrorPopup(bundle.getString("signUpErrorTitle"), bundle.getString("tariffSelectionConfirmationErrorLabel"));
		}
	}

	public void show() {
		dialogStage.showAndWait();
	}

	public boolean getCheckboxValue() {
		return checkBox.isSelected();
	}
}