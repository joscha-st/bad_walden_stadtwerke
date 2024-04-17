module com.bad_walden_stadtwerke {
    requires javafx.controls;
    requires java.net.http;
    requires org.mockito;
    requires javafx.fxml;
    opens com.bad_walden_stadtwerke to javafx.fxml;

    exports com.bad_walden_stadtwerke;
}
