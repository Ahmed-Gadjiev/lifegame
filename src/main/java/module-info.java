module com.example.lifegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.lifegame to javafx.fxml;
    exports com.example.lifegame;
}