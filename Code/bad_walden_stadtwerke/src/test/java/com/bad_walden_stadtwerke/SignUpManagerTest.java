package com.bad_walden_stadtwerke;

import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import com.bad_walden_stadtwerke.ui.controller.SignUpManager;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SignUpManagerTest {

	private static final String BUNDLE_NAME = "Bundle";
	private ResourceBundle bundle;

	@Test
	void testInitializeDialogStage() {
		Stage mockStage = mock(Stage.class);
		FXMLLoader mockLoader = mock(FXMLLoader.class);
		LanguageController mockController = mock(LanguageController.class);
		SignUpManager manager = new SignUpManager(mockStage, mockLoader, mockController);
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());

		verify(mockStage).initModality(Modality.APPLICATION_MODAL);
		verify(mockStage).initStyle(StageStyle.UNDECORATED);
		verify(mockStage).setTitle(bundle.getString("signUpPopUpTitle"));
	}
}