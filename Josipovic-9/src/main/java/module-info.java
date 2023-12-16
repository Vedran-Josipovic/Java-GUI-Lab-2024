module hr.production.josipovic9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;


    opens hr.javafx.production to javafx.fxml;
    exports hr.javafx.production;
}
