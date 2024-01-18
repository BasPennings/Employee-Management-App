module bas.pennings.app {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
    requires com.fasterxml.jackson.databind;
    requires lombok;
    requires javafx.graphics;

    opens bas.pennings.app to javafx.fxml;
    exports bas.pennings.app;
    exports bas.pennings.app.application;
    opens bas.pennings.app.application to javafx.fxml;
}