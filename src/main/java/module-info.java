module com.example.calendar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires lombok;
    requires org.commonmark;

    opens com.example.calendar to javafx.fxml;
    exports com.example.calendar;
    exports com.example.calendar.controller;
    opens com.example.calendar.controller to javafx.fxml;
}