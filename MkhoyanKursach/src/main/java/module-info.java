module com.example.mkhoyankursach {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.persistence;


    opens org.example to javafx.fxml;
    exports org.example;
}