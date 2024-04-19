module com.bad_walden_stadtwerke {
    requires javafx.controls;
    requires java.net.http;
    requires org.mockito;
    requires javafx.fxml;
    opens com.bad_walden_stadtwerke to javafx.fxml;

    exports com.bad_walden_stadtwerke;
    exports com.bad_walden_stadtwerke.ui.controller;
    opens com.bad_walden_stadtwerke.ui.controller to javafx.fxml;
    exports com.bad_walden_stadtwerke.ui.controller.mainApplication;
    opens com.bad_walden_stadtwerke.ui.controller.mainApplication to javafx.fxml;
    exports com.bad_walden_stadtwerke.ui.components.errorHandling;
    opens com.bad_walden_stadtwerke.ui.components.errorHandling to javafx.fxml;
}
