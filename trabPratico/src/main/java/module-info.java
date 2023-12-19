module com.example.trabpratico {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.trabpratico to javafx.fxml;
    exports com.example.trabpratico;
}