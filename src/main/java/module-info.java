module com.example.bowlingscoreboard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bowlingscoreboard to javafx.fxml;
    exports com.example.bowlingscoreboard;
}