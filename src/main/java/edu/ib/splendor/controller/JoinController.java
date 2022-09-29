package edu.ib.splendor.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.ib.splendor.Configuration;
import edu.ib.splendor.database.entities.Board;
import edu.ib.splendor.database.entities.NameCheckedPair;
import edu.ib.splendor.database.entities.Player;
import edu.ib.splendor.database.repositories.access.RepositoryAccessor;
import edu.ib.splendor.database.repositories.dtos.BoardDto;
import edu.ib.splendor.database.repositories.dtos.GameDto;
import edu.ib.splendor.database.repositories.dtos.WaitDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class JoinController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private RepositoryAccessor<WaitDto> waitRepositoryAccessor;
    private RepositoryAccessor<GameDto> gameRepositoryAccessor;
    private RepositoryAccessor<BoardDto> boardRepositoryAccessor;
    public static ArrayList<NameCheckedPair> players = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField aliasFieldName;

    @FXML
    private CheckBox readyButton;

    @FXML
    private TextField secretNameField;

    @FXML
    void wait(ActionEvent event) throws IOException, InterruptedException {
        Long gameId = null;
//        root = FXMLLoader.load(getClass().getClassLoader().getResource("waitForGame.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setTitle("Splendor");
//        stage.setScene(scene);
//        String css = getClass().getClassLoader().getResource("board.css").toExternalForm();
//        scene.getStylesheets().add(css);
//        stage.show();
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add(aliasFieldName.getText());
        Configuration.playerNames = playerNames;
        for (WaitDto waitDto: waitRepositoryAccessor.findAll()) {
            if (waitDto.getGameKey() != null && waitDto.getGameKey().equals(secretNameField.getText())) {
                gameId = waitDto.getGameDto().getId();
            }
        }
        while (true) {
            if (readyButton.isSelected()) {
                ArrayList<NameCheckedPair> list = new ArrayList<>();
                for (WaitDto waitDto: waitRepositoryAccessor.findAll()){
                    GameDto gameDto = gameRepositoryAccessor.findById(gameId);
                    if (waitDto.getGameDto().getId().equals(gameDto.getId()) && !waitDto.getPlayerName().equals("")) {
                        list.add(new NameCheckedPair(waitDto.getPlayerName(), false));
                    }
                    if (waitDto.getGameKey() != null && waitDto.getGameKey().equals(secretNameField.getText())){
                        gameId = waitDto.getGameDto().getId();
                        waitDto.setReady(true);
                        waitDto.setPlayerName(aliasFieldName.getText());
                        waitRepositoryAccessor.save(waitDto);
                        if (gameDto.getStarted()){
                            players = list;
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("board.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setTitle("Splendor");
                            stage.setScene(scene);
                            String css = getClass().getClassLoader().getResource("board.css").toExternalForm();
                            scene.getStylesheets().add(css);
                            stage.show();
                        }
                        break;
                    }
                }
            }
            Thread.sleep(250);
        }
    }

    @FXML
    void initialize() {
        assert aliasFieldName != null : "fx:id=\"AliasFieldName\" was not injected: check your FXML file 'join.fxml'.";
        assert readyButton != null : "fx:id=\"readyButton\" was not injected: check your FXML file 'join.fxml'.";
        assert secretNameField != null : "fx:id=\"secretNameField\" was not injected: check your FXML file 'join.fxml'.";
        waitRepositoryAccessor = new RepositoryAccessor<>("/wait", WaitDto.class, WaitDto[].class);
        gameRepositoryAccessor = new RepositoryAccessor<>("/game", GameDto.class, GameDto[].class);
        boardRepositoryAccessor = new RepositoryAccessor<>("/board", BoardDto.class, BoardDto[].class);
    }

}
