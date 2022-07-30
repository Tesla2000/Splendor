package edu.ib.splendor.controller;

import edu.ib.splendor.Configuration;
import edu.ib.splendor.database.entities.NameCheckedPair;
import edu.ib.splendor.database.repositories.access.RepositoryAccessor;
import edu.ib.splendor.database.repositories.dtos.GameDto;
import edu.ib.splendor.database.repositories.dtos.WaitDto;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class JoinController {
    private RepositoryAccessor<WaitDto> waitRepositoryAccessor;
    private RepositoryAccessor<GameDto> gameRepositoryAccessor;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField aliasFieldName;

    @FXML
    private Button readyButton;

    @FXML
    private TextField secretNameField;

    @FXML
    void wait(ActionEvent event) throws IOException, InterruptedException {
        Long gameId;
        boolean stop = false;
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add(aliasFieldName.getText());
        Configuration.playerNames = playerNames;
        while (true) {
            for (WaitDto waitDto: waitRepositoryAccessor.findAll()){
                if (waitDto.getGameKey()!=null && waitDto.getGameKey().equals(secretNameField.getText())){
                    gameId = waitDto.getGameDto().getId();
                    waitDto.setReady(true);
                    waitDto.setPlayerName(aliasFieldName.getText());
                    waitRepositoryAccessor.save(waitDto);
                    if (gameRepositoryAccessor.findById(gameId).getStarted()){
                        ArrayList<NameCheckedPair> list = new ArrayList<>();
                        for (WaitDto waitDto1: waitRepositoryAccessor.findAll()) {
                            if (waitDto1.getGameDto().getId().equals(gameId) && !waitDto1.getPlayerName().equals("")) {
                                list.add(new NameCheckedPair(waitDto1.getPlayerName(), false));
                            }
                            Configuration.gameId = gameId;
                            HostingRoomController.players = list;
                            stop = true;
                        }
                    }
                    break;
                }
            }
            if (stop) break;
            Thread.sleep(250);
        }
        Configuration.joining = true;
        Configuration.playerNames.add(aliasFieldName.getText());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("board.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Splendor");
        stage.setScene(scene);
        String css = getClass().getClassLoader().getResource("board.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
    }

    @FXML
    void initialize() {
        assert aliasFieldName != null : "fx:id=\"AliasFieldName\" was not injected: check your FXML file 'join.fxml'.";
        assert readyButton != null : "fx:id=\"readyButton\" was not injected: check your FXML file 'join.fxml'.";
        assert secretNameField != null : "fx:id=\"secretNameField\" was not injected: check your FXML file 'join.fxml'.";
        waitRepositoryAccessor = new RepositoryAccessor<>("/wait", WaitDto.class, WaitDto[].class);
        gameRepositoryAccessor = new RepositoryAccessor<>("/game", GameDto.class, GameDto[].class);
    }

}
