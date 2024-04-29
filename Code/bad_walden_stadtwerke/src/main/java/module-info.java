module com.bad_walden_stadtwerke {
    requires javafx.controls;
    requires java.net.http;
    requires org.mockito;
    requires javafx.fxml;

    exports com.bad_walden_stadtwerke;
    exports com.bad_walden_stadtwerke.components;
    exports com.bad_walden_stadtwerke.components.errorHandling;
    exports com.bad_walden_stadtwerke.controller.language;
    exports com.bad_walden_stadtwerke.controller.mainApplication;
    exports com.bad_walden_stadtwerke.model.initialSignUp;
    exports com.bad_walden_stadtwerke.model.types;
    exports com.bad_walden_stadtwerke.model.types.billingAddress;
    exports com.bad_walden_stadtwerke.model.types.language;
    exports com.bad_walden_stadtwerke.utility;

    opens com.bad_walden_stadtwerke to javafx.fxml;
    opens com.bad_walden_stadtwerke.components to javafx.fxml;
    opens com.bad_walden_stadtwerke.components.errorHandling to javafx.fxml;
    opens com.bad_walden_stadtwerke.controller.initialSignUp to javafx.fxml;
    opens com.bad_walden_stadtwerke.controller.language to javafx.fxml;
    opens com.bad_walden_stadtwerke.controller.mainApplication to javafx.fxml;
    opens com.bad_walden_stadtwerke.model.initialSignUp to javafx.fxml;
    opens com.bad_walden_stadtwerke.model.types to javafx.fxml;
    opens com.bad_walden_stadtwerke.model.types.billingAddress to javafx.fxml;
    opens com.bad_walden_stadtwerke.model.types.language to javafx.fxml;
    opens com.bad_walden_stadtwerke.utility to javafx.fxml;
}