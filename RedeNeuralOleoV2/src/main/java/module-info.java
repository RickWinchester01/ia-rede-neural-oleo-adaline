module com.example.redeneuraloleov2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.redeneuraloleov2 to javafx.fxml;
    exports com.example.redeneuraloleov2;
}