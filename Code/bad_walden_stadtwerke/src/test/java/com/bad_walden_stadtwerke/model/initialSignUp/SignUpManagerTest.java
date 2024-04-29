package com.bad_walden_stadtwerke.model.initialSignUp;

import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SignUpManagerTest {


	@Test
	void whenInitializeDialogStage_thenStageIsConfiguredCorrectly() {
		Stage mockStage = mock(Stage.class);
		new SignUpManager(mockStage, new FXMLLoader());

		verify(mockStage).initModality(Modality.APPLICATION_MODAL);
	}
}