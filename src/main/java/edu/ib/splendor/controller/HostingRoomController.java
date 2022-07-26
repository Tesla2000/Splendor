package edu.ib.splendor.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HostingRoomController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private RepositoryAccessor<WaitDto> waitRepositoryAccessor;
    private RepositoryAccessor<GameDto> gameRepositoryAccessor;
    public static ArrayList<NameCheckedPair> players;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstPlayerNameTextField;

    @FXML
    private TextField fourthPlayerNameTextField;

    @FXML
    private CheckBox isFirstPlayerMeCheckBox;

    @FXML
    private CheckBox isFourthPlayerMeCheckBox;

    @FXML
    private CheckBox isSecondPlayerMeCheckBox;

    @FXML
    private CheckBox isThirdPlayerMeCheckBox;

    @FXML
    private TextField secondPlayerNameTextField;

    @FXML
    private TextField thirdPlayerNameTextField;

    @FXML
    private CheckBox waitingForGameCheckBox;

    @FXML
    void changeState(ActionEvent event) throws IOException, InterruptedException {
        GameDto gameDto = new GameDto();
        gameDto.setCreated(LocalDateTime.now());
        gameDto.setStarted(false);
        gameRepositoryAccessor.save(gameDto);
        ArrayList<WaitDto> waitDtos = new ArrayList<>();
        for (int i=0;i<4;i++){
            WaitDto waitDto = new WaitDto();
            waitRepositoryAccessor.save(waitDto);
            waitDtos.add(waitDto);
        }
        root = FXMLLoader.load(getClass().getClassLoader().getResource("waitForGame.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Splendor");
        stage.setScene(scene);
        String css = getClass().getClassLoader().getResource("board.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
        while (true) {
            if (waitingForGameCheckBox.isSelected()) {
                players = new ArrayList<>();
                players.add(new NameCheckedPair(firstPlayerNameTextField.getText(), isFirstPlayerMeCheckBox.isSelected()));
                players.add(new NameCheckedPair(secondPlayerNameTextField.getText(), isSecondPlayerMeCheckBox.isSelected()));
                players.add(new NameCheckedPair(thirdPlayerNameTextField.getText(), isThirdPlayerMeCheckBox.isSelected()));
                players.add(new NameCheckedPair(fourthPlayerNameTextField.getText(), isFourthPlayerMeCheckBox.isSelected()));
                for (int i=0; i<4;i++) {
//                    waitDtos.get(i).setGameDto(gameDto);
                    if (!players.get(i).getName().equals("") && players.get(i).isChecked()) {
                        waitDtos.get(i).setPlayerName(players.get(i).getName());
                        waitDtos.get(i).setReady(true);
                    } else if (players.get(i).getName().equals("") && !players.get(i).isChecked()) {
                        waitDtos.get(i).setReady(true);
                        waitDtos.get(i).setPlayerName("");
                        waitDtos.get(i).setGameKey("");
                    }else if (!players.get(i).getName().equals("") && !players.get(i).isChecked()) {
                        waitDtos.get(i).setReady(false);
                        waitDtos.get(i).setPlayerName("");
                        waitDtos.get(i).setGameKey(players.get(i).getName());
                    } else {
                        waitDtos.get(i).setReady(false);
                        waitDtos.get(i).setPlayerName("");
                        waitDtos.get(i).setGameKey("");
                    }
                }
            }
            waitRepositoryAccessor.saveAll(waitDtos);
            Thread.sleep(1000);
            if (areAllPlayersReady(waitDtos)){
                ArrayList<NameCheckedPair> list = new ArrayList<>();
                for (WaitDto waitDto: waitRepositoryAccessor.findAll()) {
//                    if (waitDto.getGameDto().getId().equals(gameDto.getId()) && !waitDto.getPlayerName().equals("")) {
//                        list.add(new NameCheckedPair(waitDto.getPlayerName(), false));
//                    }
                }
                players = list;
                if (players.size()>=2) {
                    gameDto.setStarted(true);
                    gameRepositoryAccessor.save(gameDto);
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("board.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Splendor");
                    stage.setScene(scene);
                    scene.getStylesheets().add(css);
                    stage.show();
                }
            }
        }
    }

    private boolean areAllPlayersReady(ArrayList<WaitDto> waitDtos){
        for (WaitDto waitDto: waitDtos){
            if (!waitDto.getReady()) return false;
        }
        return true;
    }

    @FXML
    void initialize() {
        assert firstPlayerNameTextField != null : "fx:id=\"firstPlayerNameTextField\" was not injected: check your FXML file 'hostingRoom.fxml'.";
        assert fourthPlayerNameTextField != null : "fx:id=\"fourthPlayerNameTextField\" was not injected: check your FXML file 'hostingRoom.fxml'.";
        assert isFirstPlayerMeCheckBox != null : "fx:id=\"isFirstPlayerMeCheckBox\" was not injected: check your FXML file 'hostingRoom.fxml'.";
        assert isFourthPlayerMeCheckBox != null : "fx:id=\"isFourthPlayerMeCheckBox\" was not injected: check your FXML file 'hostingRoom.fxml'.";
        assert isSecondPlayerMeCheckBox != null : "fx:id=\"isSecondPlayerMeCheckBox\" was not injected: check your FXML file 'hostingRoom.fxml'.";
        assert isThirdPlayerMeCheckBox != null : "fx:id=\"isThirdPlayerMeCheckBox\" was not injected: check your FXML file 'hostingRoom.fxml'.";
        assert secondPlayerNameTextField != null : "fx:id=\"secondPlayerNameTextField\" was not injected: check your FXML file 'hostingRoom.fxml'.";
        assert thirdPlayerNameTextField != null : "fx:id=\"thirdPlayerNameTextField\" was not injected: check your FXML file 'hostingRoom.fxml'.";
        assert waitingForGameCheckBox != null : "fx:id=\"waitingForGameCheckBox\" was not injected: check your FXML file 'hostingRoom.fxml'.";
        waitRepositoryAccessor = new RepositoryAccessor<>("/wait", WaitDto.class, WaitDto[].class);
        gameRepositoryAccessor = new RepositoryAccessor<>("/game", GameDto.class, GameDto[].class);
    }

}
