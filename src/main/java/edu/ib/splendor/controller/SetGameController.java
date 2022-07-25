package edu.ib.splendor.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import edu.ib.splendor.Configuration;
import edu.ib.splendor.database.entities.NameCheckedPair;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetGameController {
    public static List<NameCheckedPair> players;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstPlayerNameTextField;

    @FXML
    private TextField fourthPlayerNameTextField;

    @FXML
    private CheckBox isFirstPlayerAICheckBox;

    @FXML
    private CheckBox isFourthPlayerAICheckBox;

    @FXML
    private CheckBox isSecondPlayerAICheckBox;

    @FXML
    private CheckBox isThirdPlayerAICheckBox;

    @FXML
    private TextField secondPlayerNameTextField;

    @FXML
    private TextField thirdPlayerNameTextField;

    @FXML
    void start(ActionEvent event) throws IOException {
        players = new ArrayList<>();
        players.add(new NameCheckedPair(firstPlayerNameTextField.getText(), isFirstPlayerAICheckBox.isSelected()));
        players.add(new NameCheckedPair(secondPlayerNameTextField.getText(), isSecondPlayerAICheckBox.isSelected()));
        players.add(new NameCheckedPair(thirdPlayerNameTextField.getText(), isThirdPlayerAICheckBox.isSelected()));
        players.add(new NameCheckedPair(fourthPlayerNameTextField.getText(), isFourthPlayerAICheckBox.isSelected()));
        List<NameCheckedPair> nameCheckedPairs = new ArrayList<>();
        for (NameCheckedPair player : players) {
            if (!player.getName().equals("")) {
                nameCheckedPairs.add(player);
            }
        }
        players = nameCheckedPairs;
        if (nameCheckedPairs.size() >= 2){
            Configuration.playerNames = players.stream().map(NameCheckedPair::getName).collect(Collectors.toList());
            root = FXMLLoader.load(getClass().getClassLoader().getResource("board.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Splendor");
            stage.setScene(scene);
            String css = getClass().getClassLoader().getResource("board.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.show();
        }
    }

    @FXML
    void initialize() {
        assert firstPlayerNameTextField != null : "fx:id=\"firstPlayerNameTextField\" was not injected: check your FXML file 'setGame.fxml'.";
        assert fourthPlayerNameTextField != null : "fx:id=\"fourthPlayerNameTextField\" was not injected: check your FXML file 'setGame.fxml'.";
        assert isFirstPlayerAICheckBox != null : "fx:id=\"isFirstPlayerAICheckBox\" was not injected: check your FXML file 'setGame.fxml'.";
        assert isFourthPlayerAICheckBox != null : "fx:id=\"isFourthPlayerAICheckBox\" was not injected: check your FXML file 'setGame.fxml'.";
        assert isSecondPlayerAICheckBox != null : "fx:id=\"isSecondPlayerAICheckBox\" was not injected: check your FXML file 'setGame.fxml'.";
        assert isThirdPlayerAICheckBox != null : "fx:id=\"isThirdPlayerAICheckBox\" was not injected: check your FXML file 'setGame.fxml'.";
        assert secondPlayerNameTextField != null : "fx:id=\"secondPlayerNameTextField\" was not injected: check your FXML file 'setGame.fxml'.";
        assert thirdPlayerNameTextField != null : "fx:id=\"thirdPlayerNameTextField\" was not injected: check your FXML file 'setGame.fxml'.";

    }

}
