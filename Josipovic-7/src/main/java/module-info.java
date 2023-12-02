module hr.production.josipovic7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;


    opens hr.javafx.production to javafx.fxml;
    exports hr.javafx.production;
    exports hr.javafx.production.controller;
    opens hr.javafx.production.controller to javafx.fxml;
}
