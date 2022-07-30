module Splendor {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.io;
    exports edu.ib.splendor.database.repositories.dtos to com.fasterxml.jackson.databind;


    opens edu.ib.splendor to javafx.fxml;
    exports edu.ib.splendor;
    exports edu.ib.splendor.database.entities;
    opens edu.ib.splendor.database.entities to javafx.fxml;
    exports edu.ib.splendor.controller;
    opens edu.ib.splendor.controller to javafx.fxml;
    exports edu.ib.splendor.service;
    opens edu.ib.splendor.service to javafx.fxml;
    exports edu.ib.splendor.service.AI;
    opens edu.ib.splendor.service.AI to javafx.fxml;
}