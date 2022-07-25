package edu.ib.splendor.controller;

import edu.ib.splendor.Configuration;
import edu.ib.splendor.database.repositories.dtos.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static String login;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    void login(ActionEvent event) throws IOException {
        login = loginField.getText();
        for (UserDto userDto : Configuration.userRepository.findAll()) {
            if (userDto.getLogin().equals(login) && userDto.getPassword().equals(passwordField.getText())) {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("multiplayer.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Splendor");
                stage.setScene(scene);
                String css = getClass().getClassLoader().getResource("board.css").toExternalForm();
                scene.getStylesheets().add(css);
                stage.show();

            }
        }
    }

    @FXML
    void initialize() {
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login.fxml'.";
        assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'login.fxml'.";
    }
}
