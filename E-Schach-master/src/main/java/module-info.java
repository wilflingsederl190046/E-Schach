module com.example.project_echess {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project_echess to javafx.fxml;
    exports com.example.project_echess;
    exports com.example.project_echess.Chessman;
    opens com.example.project_echess.Chessman to javafx.fxml;
}