package edu.ib.splendor.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WaitingRoomController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void admin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("multiplayer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Splendor");
        stage.setScene(scene);
        String css = getClass().getClassLoader().getResource("board.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();

    }

    @FXML
    void user(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("join.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Splendor");
        stage.setScene(scene);
        String css = getClass().getClassLoader().getResource("board.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();


    }

    @FXML
    void initialize() {

    }

}
