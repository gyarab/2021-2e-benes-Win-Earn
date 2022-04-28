module com.example.demo4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.example.demo4 to javafx.fxml;
    exports com.example.demo4;
}