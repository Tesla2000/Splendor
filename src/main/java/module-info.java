module edu.ib.splendor {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.ib.splendor to javafx.fxml;
    exports edu.ib.splendor;
}