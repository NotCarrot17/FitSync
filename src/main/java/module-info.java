module com.example.fitsync {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.fitsync to javafx.fxml;
    exports com.example.fitsync;
    exports com.example.fitsync.controller;
    opens com.example.fitsync.controller to javafx.fxml;
    exports com.example.fitsync.dao;
    opens com.example.fitsync.dao to javafx.fxml;
}