package edu.ib.splendor.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

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
    private CheckBox isFirstPlayerAICheckBox;

    @FXML
    private CheckBox isFirstPlayerMeCheckBox;

    @FXML
    private CheckBox isFourthPlayerAICheckBox;

    @FXML
    private CheckBox isFourthPlayerMeCheckBox;

    @FXML
    private CheckBox isSecondPlayerAICheckBox;

    @FXML
    private CheckBox isSecondPlayerMeCheckBox;

    @FXML
    private CheckBox isThirdPlayerAICheckBox;

    @FXML
    private CheckBox isThirdPlayerMeCheckBox;

    @FXML
    private TextField secondPlayerNameTextField;

    @FXML
    private TextField thirdPlayerNameTextField;

    @FXML
    private Button waitingForGameCheckBox;

    @FXML
    void changeState(ActionEvent event) throws IOException, InterruptedException {
        Random random = new Random(1);
        boolean[] checks = new boolean[4];
        boolean[] ais = new boolean[4];
        checks[0] = isFirstPlayerMeCheckBox.isSelected();
        checks[1] = isSecondPlayerMeCheckBox.isSelected();
        checks[2] = isThirdPlayerMeCheckBox.isSelected();
        checks[3] = isFourthPlayerMeCheckBox.isSelected();
        ais[0] = isFirstPlayerAICheckBox.isSelected();
        ais[1] = isSecondPlayerAICheckBox.isSelected();
        ais[2] = isThirdPlayerAICheckBox.isSelected();
        ais[3] = isFourthPlayerAICheckBox.isSelected();
        GameDto gameDto = new GameDto();
        gameDto.setStarted(false);
        gameDto.setId(random.nextLong());
        gameRepositoryAccessor.save(gameDto);
        ArrayList<WaitDto> waitDtos = new ArrayList<>();
        for (int i=0;i<4;i++){
            WaitDto waitDto = new WaitDto();
            waitDto.setId(random.nextLong());
            waitRepositoryAccessor.save(waitDto);
            waitDtos.add(waitDto);
        }
        if (checks[0] && !ais[0])
            Configuration.playerNames.add(firstPlayerNameTextField.getText());
        if (checks[1] && !ais[1])
            Configuration.playerNames.add(secondPlayerNameTextField.getText());
        if (checks[2] && !ais[2])
            Configuration.playerNames.add(thirdPlayerNameTextField.getText());
        if (checks[3] && !ais[3])
            Configuration.playerNames.add(fourthPlayerNameTextField.getText());
        if (ais[0])
            Configuration.AINames.add(firstPlayerNameTextField.getText());
        if (ais[1])
            Configuration.AINames.add(secondPlayerNameTextField.getText());
        if (ais[2])
            Configuration.AINames.add(thirdPlayerNameTextField.getText());
        if (ais[3])
            Configuration.AINames.add(fourthPlayerNameTextField.getText());
        while (true) {
            players = new ArrayList<>();
            players.add(new NameCheckedPair(firstPlayerNameTextField.getText(), isFirstPlayerMeCheckBox.isSelected()));
            players.add(new NameCheckedPair(secondPlayerNameTextField.getText(), isSecondPlayerMeCheckBox.isSelected()));
            players.add(new NameCheckedPair(thirdPlayerNameTextField.getText(), isThirdPlayerMeCheckBox.isSelected()));
            players.add(new NameCheckedPair(fourthPlayerNameTextField.getText(), isFourthPlayerMeCheckBox.isSelected()));
            for (int i=0; i<4;i++) {
                waitDtos.get(i).setGameDto(gameDto);
                if (!players.get(i).getName().equals("") && (checks[i] || ais[i])) {
                    waitDtos.get(i).setPlayerName(players.get(i).getName());
                    waitDtos.get(i).setReady(true);
                } else if (players.get(i).getName().equals("") && !(checks[i] || ais[i])) {
                    waitDtos.get(i).setReady(true);
                    waitDtos.get(i).setPlayerName("");
                    waitDtos.get(i).setGameKey("");
                }else if (!players.get(i).getName().equals("") && !(checks[i] || ais[i])) {
                    waitDtos.get(i).setReady(false);
                    waitDtos.get(i).setPlayerName("");
                    waitDtos.get(i).setGameKey(players.get(i).getName());
                } else {
                    waitDtos.get(i).setReady(false);
                    waitDtos.get(i).setPlayerName("");
                    waitDtos.get(i).setGameKey("");
                }
            }
            waitRepositoryAccessor.saveAll(waitDtos);
            Thread.sleep(1000);
            if (areAllPlayersReady(waitRepositoryAccessor.findAll())){
                System.out.println("All ready");
                ArrayList<NameCheckedPair> list = new ArrayList<>();
                for (WaitDto waitDto: waitRepositoryAccessor.findAll()) {
                    if (waitDto.getGameDto().getId().equals(gameDto.getId()) && !waitDto.getPlayerName().equals("")) {
                        list.add(new NameCheckedPair(waitDto.getPlayerName(), false));
                    }
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
                    String css = getClass().getClassLoader().getResource("board.css").toExternalForm();
                    scene.getStylesheets().add(css);
                    stage.show();
                    break;
                }
            }
        }
    }

    private boolean areAllPlayersReady(WaitDto[] waitDtos){
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
