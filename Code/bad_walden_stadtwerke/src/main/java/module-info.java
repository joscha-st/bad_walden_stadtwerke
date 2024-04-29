module com.bad_walden_stadtwerke {
	requires javafx.controls;
	requires java.net.http;
	requires org.mockito;
	requires javafx.fxml;

	exports com.bad_walden_stadtwerke;
	exports com.bad_walden_stadtwerke.ui.controller;
	exports com.bad_walden_stadtwerke.ui.controller.mainApplication;
	exports com.bad_walden_stadtwerke.ui.components.errorHandling;

	opens com.bad_walden_stadtwerke to javafx.fxml;
	opens com.bad_walden_stadtwerke.ui.controller to javafx.fxml;
	opens com.bad_walden_stadtwerke.ui.controller.mainApplication to javafx.fxml;
	opens com.bad_walden_stadtwerke.ui.components.errorHandling to javafx.fxml;
	opens com.bad_walden_stadtwerke.ui.controller.initialSignUp to javafx.fxml;
}
