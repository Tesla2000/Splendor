package edu.ib.splendor;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameController {
    private AITwoPlayers aiTwoPlayers;
    private LocalDateTime block = LocalDateTime.now();
    private Player currentPlayer;
    private Board board;
    private ArrayList<HashMap<Gem, Text>> playersResource;
    private ArrayList<HashMap<Gem, StackPane>> playersPanes;
    private BoardController boardController;
    private Text goldResource;
    private Text redsResource;
    private Text greensResource;
    private Text bluesResource;
    private Text brownsResource;
    private Text whitesResource;
    private Text goldsResource;
    private Text redsResource1;
    private Text greensResource1;
    private Text bluesResource1;
    private Text brownsResource1;
    private Text whitesResource1;
    private Text goldsResource1;
    private Text redsResource11;
    private Text greensResource11;
    private Text bluesResource11;
    private Text brownsResource11;
    private Text whitesResource11;
    private Text goldsResource11;
    private Text redsResource111;
    private Text greensResource111;
    private Text bluesResource111;
    private Text brownsResource111;
    private Text whitesResource111;
    private Text goldsResource111;
    private ArrayList<Player> players;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView aristocrat0;

    @FXML
    private ImageView aristocrat1;

    @FXML
    private ImageView aristocrat2;

    @FXML
    private ImageView aristocrat3;

    @FXML
    private ImageView blue;

    @FXML
    private StackPane bluePane;

    @FXML
    private ImageView blues;

    @FXML
    private ImageView blues1;

    @FXML
    private ImageView blues11;

    @FXML
    private ImageView blues111;

    @FXML
    private StackPane bluesPane;

    @FXML
    private StackPane bluesPane1;

    @FXML
    private StackPane bluesPane11;

    @FXML
    private StackPane bluesPane111;

    @FXML
    private ImageView brown;

    @FXML
    private StackPane brownPane;

    @FXML
    private ImageView browns;

    @FXML
    private ImageView browns1;

    @FXML
    private ImageView browns11;

    @FXML
    private ImageView browns111;

    @FXML
    private StackPane brownsPane;

    @FXML
    private StackPane brownsPane1;

    @FXML
    private StackPane brownsPane11;

    @FXML
    private StackPane brownsPane111;

    @FXML
    private RadioButton build;

    @FXML
    private ImageView building10;

    @FXML
    private ImageView building11;

    @FXML
    private ImageView building12;

    @FXML
    private ImageView building13;

    @FXML
    private ImageView building14;

    @FXML
    private ImageView building20;

    @FXML
    private ImageView building21;

    @FXML
    private ImageView building22;

    @FXML
    private ImageView building23;

    @FXML
    private ImageView building24;

    @FXML
    private ImageView building30;

    @FXML
    private ImageView building31;

    @FXML
    private ImageView building32;

    @FXML
    private ImageView building33;

    @FXML
    private ImageView building34;

    @FXML
    private RadioButton getThree;

    @FXML
    private RadioButton getTwo;

    @FXML
    private ImageView gold;

    @FXML
    private StackPane goldPane;

    @FXML
    private ImageView golds;

    @FXML
    private ImageView golds1;

    @FXML
    private ImageView golds11;

    @FXML
    private ImageView golds111;

    @FXML
    private StackPane goldsPane;

    @FXML
    private StackPane goldsPane1;

    @FXML
    private StackPane goldsPane11;

    @FXML
    private StackPane goldsPane111;

    @FXML
    private ImageView green;

    @FXML
    private StackPane greenPane;

    @FXML
    private ImageView greens;

    @FXML
    private ImageView greens1;

    @FXML
    private ImageView greens11;

    @FXML
    private ImageView greens111;

    @FXML
    private StackPane greensPane;

    @FXML
    private StackPane greensPane1;

    @FXML
    private StackPane greensPane11;

    @FXML
    private StackPane greensPane111;

    @FXML
    private ToggleGroup option;

    @FXML
    private Text playerName;

    @FXML
    private Text playerName1;

    @FXML
    private Text playerName11;

    @FXML
    private Text playerName111;

    @FXML
    private Text poins;

    @FXML
    private Text poins1;

    @FXML
    private Text poins11;

    @FXML
    private Text poins111;

    @FXML
    private ImageView red;

    @FXML
    private StackPane redPane;

    @FXML
    private ImageView reds;

    @FXML
    private ImageView reds1;

    @FXML
    private ImageView reds11;

    @FXML
    private ImageView reds111;

    @FXML
    private VBox redsLeft;

    @FXML
    private ImageView endTurnButton;

    @FXML
    private StackPane redsPane;

    @FXML
    private StackPane redsPane1;

    @FXML
    private StackPane redsPane11;


    @FXML
    private StackPane redsPane111;

    @FXML
    private ImageView res1;

    @FXML
    private ImageView res2;

    @FXML
    private ImageView res3;

    @FXML
    private RadioButton reserve;

    @FXML
    private HBox resourceBox;

    @FXML
    private Text textRes;

    @FXML
    private Text textRes1;

    @FXML
    private Text textRes11;

    @FXML
    private ImageView white;

    @FXML
    private StackPane whitePane;

    @FXML
    private ImageView whites;

    @FXML
    private ImageView whites1;

    @FXML
    private ImageView whites11;

    @FXML
    private ImageView whites111;

    @FXML
    private StackPane whitesPane;

    @FXML
    private StackPane whitesPane1;

    @FXML
    private StackPane whitesPane11;

    @FXML
    private StackPane whitesPane111;

    private void buyEstate(Tier tier, int i) {
        if (LocalDateTime.now().isAfter(block)) {
            Card card;
            HashMap<Gem, Integer> cost = new HashMap<>();
            int goldNeeded = 0;
            if (build.isSelected() && i >= 0) {
                if (!tier.equals(Tier.RESERVE)) {
                    i--;
                    card = board.getTradeRow().getCard(tier, i);
                } else card = currentPlayer.getReserve().get(i);
                goldNeeded = getGoldNeeded(card, cost, goldNeeded, currentPlayer);
                if (goldNeeded <= currentPlayer.getPossession().get(Gem.GOLD)) {
                    pay(card, cost, goldNeeded, currentPlayer, board);
                    if (!tier.equals(Tier.RESERVE))
                        currentPlayer.addCard(board.getTradeRow().takeCard(tier, i));
                    else {
                        currentPlayer.addCard(card);
                        currentPlayer.removeReserve(card);
                    }
                    endTurn();
                }
            }
            if (reserve.isSelected()) {
                i--;
                if (i >= 0)
                    card = board.getTradeRow().getCard(tier, i);
                else card = board.getTradeRow().getCardsHidden().get(tier).remove(0);
                if (currentPlayer.getReserve().size() < 3) {
                    currentPlayer.addReserve(card);
                    if (board.getStored(Gem.GOLD) > 0 && currentPlayer.allGems() < 10) {
                        currentPlayer.changeGem(Gem.GOLD, -1);
                        board.changeStored(Gem.GOLD, -1);
                    }
                    board.getTradeRow().takeCard(tier, i);
                    block = LocalDateTime.now().plusSeconds(1);
                    endTurn();
                }
            }
        }
    }

    static void pay(Card card, HashMap<Gem, Integer> cost, int goldNeeded, Player currentPlayer, Board board) {
        currentPlayer.changeGem(Gem.GOLD, goldNeeded);
        cost.put(Gem.GOLD, goldNeeded);
        for (Gem gem : card.getCost().keySet()) {
            Integer paid = Math.max(0, card.getCost().getOrDefault(gem, 0) - currentPlayer.getProduction().getOrDefault(gem, 0));
            currentPlayer.changeGem(gem, paid);
            cost.put(gem, cost.getOrDefault(gem, 0) + paid);
        }
        for (Gem gem : cost.keySet())
            board.changeStored(gem, cost.get(gem));
    }

    static int getGoldNeeded(Card card, HashMap<Gem, Integer> cost, int goldNeeded, Player currentPlayer) {
        for (Gem gem : card.getCost().keySet()) {
            cost.put(gem, -Math.max(card.getCost().getOrDefault(gem, 0) - currentPlayer.getPossession().getOrDefault(gem, 0)
                    - currentPlayer.getProduction().getOrDefault(gem, 0), 0));
            goldNeeded += Math.max(card.getCost().getOrDefault(gem, 0) - currentPlayer.getPossession().getOrDefault(gem, 0)
                    - currentPlayer.getProduction().getOrDefault(gem, 0), 0);
        }
        return goldNeeded;
    }

    private void collectGem(Gem gem){
        if (currentPlayer.getCollectLimit() == 0)
            currentPlayer.setCollectLimit(3);
        if (getTwo.isSelected() && currentPlayer.getCollectLimit()==3 && board.getStored(gem)>3) {
            int amount = Math.min(2, 10 - currentPlayer.allGems());
            currentPlayer.changeGem(gem, -amount);
            board.changeStored(gem, -amount);
            block = LocalDateTime.now().plusSeconds(1);
            endTurn();
        }
        else if (board.getStored(gem)>0 && !currentPlayer.gemInTaken(gem)){
            if (currentPlayer.allGems() >= 10) {
                block = LocalDateTime.now().plusSeconds(1);
                endTurn();
            }
            getThree.setSelected(true);
            currentPlayer.changeGem(gem, -1);
            board.changeStored(gem, -1);
            currentPlayer.setCollectLimit(currentPlayer.getCollectLimit()-1);
            currentPlayer.addToTaken(gem);
            if (currentPlayer.getCollectLimit()==0 || currentPlayer.allGems() >= 10) {
                block = LocalDateTime.now().plusSeconds(1);
                endTurn();
            }
            else updateFields();
        }

    }

    private void endTurn() {
        try {
            currentPlayer.clearTaken();
            BoardController.getAristocrats(board, currentPlayer);
            players.add(players.remove(0));
            currentPlayer = players.get(0);
            updateFields();
        } catch (Exception e){
            e.printStackTrace();
        }
        if (currentPlayer instanceof PlayerWithNodes) {
            try {
                AIController.playTurn(players,((PlayerWithNodes) currentPlayer).getNodes(), board, AI.getPossibleMoves());
            } catch (GameLostException ignored) {}
            endTurn();
        } else {
            setPictures();
        }
    }


//    private void getAristocrats() {
//        ArrayList<Aristocrat> toRemove = new ArrayList<>();
//        for (Aristocrat aristocrat: board.getAristocrats())
//            if (
//                    aristocrat.getBlue()<=currentPlayer.getProduction().getOrDefault(Gem.BLUE,0) &&
//                    aristocrat.getRed()<=currentPlayer.getProduction().getOrDefault(Gem.RED,0) &&
//                    aristocrat.getGreen()<=currentPlayer.getProduction().getOrDefault(Gem.GREEN,0) &&
//                    aristocrat.getBrown()<=currentPlayer.getProduction().getOrDefault(Gem.BROWN,0) &&
//                    aristocrat.getWhite()<=currentPlayer.getProduction().getOrDefault(Gem.WHITE,0)
//            ) {
//                toRemove.add(aristocrat);
//                currentPlayer.addAristocrat(aristocrat);
//            }
//        for (Aristocrat aristocrat: toRemove){
//            board.removeAristocrat(aristocrat);
//        }
//    }

    private void setPictures(){
        Image image;
        try{
            image = new Image(currentPlayer.getReserve().get(0).getPicture());
            res1.setImage(image);
        } catch (Exception e){
            res1.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\reserve.png"));
        }
        try{
            image = new Image(currentPlayer.getReserve().get(1).getPicture());
            res2.setImage(image);
        } catch (Exception e){
            res2.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\reserve.png"));
        }
        try{
            image = new Image(currentPlayer.getReserve().get(2).getPicture());
            res3.setImage(image);
        } catch (Exception e){
            res3.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\reserve.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.FIRST, 0).getPicture());
            building11.setImage(image);
        } catch (Exception e){
            building11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_1.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 0).getPicture());
            building21.setImage(image);
        } catch (Exception e){
            building21.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_2.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 0).getPicture());
            building31.setImage(image);
        } catch (Exception e){
            building31.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_3.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.FIRST, 1).getPicture());
            building12.setImage(image);
        } catch (Exception e){
            building12.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_1.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 1).getPicture());
            building22.setImage(image);
        } catch (Exception e){
            building22.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_2.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 1).getPicture());
            building32.setImage(image);
        } catch (Exception e){
            building32.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_3.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.FIRST, 2).getPicture());
            building13.setImage(image);
        } catch (Exception e){
            building13.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_1.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 2).getPicture());
            building23.setImage(image);
        } catch (Exception e){
            building23.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_2.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 2).getPicture());
            building33.setImage(image);
        } catch (Exception e){
            building33.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_3.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.FIRST, 3).getPicture());
            building14.setImage(image);
        } catch (Exception e){
            building14.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_1.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 3).getPicture());
            building24.setImage(image);
        } catch (Exception e){
            building24.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_2.png"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 3).getPicture());
            building34.setImage(image);
        } catch (Exception e){
            building34.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_3.png"));
        }
        building30.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_3.png"));
        building20.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_2.png"));
        building10.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\tier_1.png"));
        try {
            aristocrat0.setImage(new Image(board.getAristocrats().get(0).getImage()));
        }catch (Exception e) {
            aristocrat0.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\reserve.png"));
        }
        try {
            aristocrat1.setImage(new Image(board.getAristocrats().get(1).getImage()));
        }catch (Exception e) {
            aristocrat1.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\reserve.png"));
        }
        try {
            aristocrat2.setImage(new Image(board.getAristocrats().get(2).getImage()));
        }catch (Exception e) {
            aristocrat2.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\reserve.png"));
        }
        try {
            aristocrat3.setImage(new Image(board.getAristocrats().get(3).getImage()));
        }catch (Exception e) {
            aristocrat3.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\reserve.png"));
        }

    }
    private void showResources(){
        String string;
        string =  board.getStored(Gem.RED).toString();
        red.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\red" + string + ".png"));
        string =  board.getStored(Gem.GREEN).toString();
        green.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\green" + string + ".png"));
        string =  board.getStored(Gem.BLUE).toString();
        blue.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\blue" + string + ".png"));
        string =  board.getStored(Gem.BROWN).toString();
        brown.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\brown" + string + ".png"));
        string =  board.getStored(Gem.WHITE).toString();
        white.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\white" + string + ".png"));
        string =  board.getStored(Gem.GOLD).toString();
        gold.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\gold" + string + ".png"));
    }

    private void updateFields() {
        showResources();
        Font font = new Font("Verdana", 20);
        String string;
        for (Gem gem: Gem.values()) {
            string = currentPlayer.getPossession().getOrDefault(gem, 0).toString();
            if (currentPlayer.getProduction().getOrDefault(gem, 0) != 0)
                string = currentPlayer.getPossession().getOrDefault(gem, 0) + "+" + currentPlayer.getProduction().getOrDefault(gem, 0);
            if (playersResource.get(0).get(gem) == null) {
                playersResource.get(0).put(gem, new Text(reds.getX(), reds.getY(), string));
                playersResource.get(0).get(gem).setFont(font);
                playersPanes.get(0).get(gem).getChildren().add(playersResource.get(0).get(gem));
            } else playersResource.get(0).get(gem).setText(string);
        }
        playerName.setText(currentPlayer.getName());
        poins.setText("Points: " + currentPlayer.getPoints());
        if (currentPlayer.getReserve().size()>0)
            res1.setImage(new Image(currentPlayer.getReserve().get(0).getPicture()));
        if (currentPlayer.getReserve().size()>1)
            res2.setImage(new Image(currentPlayer.getReserve().get(1).getPicture()));
        if (currentPlayer.getReserve().size()>2)
            res3.setImage(new Image(currentPlayer.getReserve().get(2).getPicture()));
        if (board.getPlayers().size()==2){
            for (Gem gem: Gem.values()) {
                string = board.getPlayers().get(1).getPossession().getOrDefault(gem, 0).toString();
                if (board.getPlayers().get(1).getProduction().getOrDefault(gem, 0) != 0)
                    string = board.getPlayers().get(1).getPossession().getOrDefault(gem, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(gem, 0);
                if (playersResource.get(2).get(gem) == null) {
                    playersResource.get(2).put(gem, new Text(reds.getX(), reds.getY(), string));
                    playersResource.get(2).get(gem).setFont(font);
                    playersPanes.get(2).get(gem).getChildren().add(playersResource.get(2).get(gem));
                } else playersResource.get(2).get(gem).setText(string);
            }
            playerName11.setText(board.getPlayers().get(1).getName());
            poins11.setText("Points: " + board.getPlayers().get(1).getPoints());
            textRes1.setText("Reserved: " + board.getPlayers().get(1).getReserve().size());
        } else {
            for (Gem gem: Gem.values()) {
                for (int i = 1; i<board.getPlayers().size(); i++) {
                    string = board.getPlayers().get(i).getPossession().getOrDefault(gem, 0).toString();
                    if (board.getPlayers().get(i).getProduction().getOrDefault(gem, 0) != 0)
                        string = board.getPlayers().get(i).getPossession().getOrDefault(gem, 0) + "+" + board.getPlayers().get(i).getProduction().getOrDefault(gem, 0);
                    if (playersResource.get(i).get(gem) == null) {
                        playersResource.get(i).put(gem, new Text(reds.getX(), reds.getY(), string));
                        playersResource.get(i).get(gem).setFont(font);
                        playersPanes.get(i).get(gem).getChildren().add(playersResource.get(i).get(gem));
                    } else playersResource.get(i).get(gem).setText(string);
                }
            }
            poins1.setText("Points: " + board.getPlayers().get(1).getPoints());
            poins11.setText("Points: " + board.getPlayers().get(2).getPoints());
            playerName1.setText(board.getPlayers().get(1).getName());
            textRes.setText("Reserved: " + board.getPlayers().get(1).getReserve().size());
            textRes1.setText("Reserved: " + board.getPlayers().get(2).getReserve().size());
            playerName11.setText(board.getPlayers().get(2).getName());
            if (board.getPlayers().size()==4) {
                poins111.setText("Points: " + board.getPlayers().get(3).getPoints());
                textRes11.setText("Reserved: " + board.getPlayers().get(3).getReserve().size());
                playerName111.setText(board.getPlayers().get(3).getName());
            }
        }
    }

    @FXML
    void actionAccess01(MouseEvent event) {
        buyEstate(Tier.RESERVE, 0);
    }


    @FXML
    void actionAccess02(MouseEvent event) {
        buyEstate(Tier.RESERVE, 1);
    }

    @FXML
    void actionAccess03(MouseEvent event) {
        buyEstate(Tier.RESERVE, 2);
    }

    @FXML
    void actionAccess10(MouseEvent event) {
        buyEstate(Tier.FIRST, 0);
    }

    @FXML
    void actionAccess11(MouseEvent event) {
        buyEstate(Tier.FIRST, 1);
    }

    @FXML
    void actionAccess12(MouseEvent event) {
        buyEstate(Tier.FIRST, 2);
    }

    @FXML
    void actionAccess13(MouseEvent event) {
        buyEstate(Tier.FIRST, 3);
    }

    @FXML
    void actionAccess14(MouseEvent event) {
        buyEstate(Tier.FIRST, 4);
    }

    @FXML
    void actionAccess20(MouseEvent event) {
        buyEstate(Tier.SECOND, 0);
    }

    @FXML
    void actionAccess21(MouseEvent event) {
        buyEstate(Tier.SECOND, 1);
    }

    @FXML
    void actionAccess22(MouseEvent event) {
        buyEstate(Tier.SECOND, 2);
    }

    @FXML
    void actionAccess23(MouseEvent event) {
        buyEstate(Tier.SECOND, 3);
    }

    @FXML
    void actionAccess24(MouseEvent event) {
        buyEstate(Tier.SECOND, 4);
    }

    @FXML
    void actionAccess30(MouseEvent event) {
        buyEstate(Tier.THIRD, 0);
    }

    @FXML
    void actionAccess31(MouseEvent event) {
        buyEstate(Tier.THIRD, 1);
    }

    @FXML
    void actionAccess32(MouseEvent event) {
        buyEstate(Tier.THIRD, 2);
    }

    @FXML
    void actionAccess33(MouseEvent event) {
        buyEstate(Tier.THIRD, 3);
    }

    @FXML
    void actionAccess34(MouseEvent event) {
        buyEstate(Tier.THIRD, 4);
    }

    @FXML
    void collectBlue(MouseEvent event) {
        collectGem(Gem.BLUE);
    }

    @FXML
    void collectBrown(MouseEvent event) {
        collectGem(Gem.BROWN);
    }

    @FXML
    void collectGreen(MouseEvent event) {
        collectGem(Gem.GREEN);
    }

    @FXML
    void collectRed(MouseEvent event) {
        collectGem(Gem.RED);
    }

    @FXML
    void collectWhite(MouseEvent event) {
        collectGem(Gem.WHITE);
    }

    @FXML
    void end(MouseEvent event) {
        endTurn();
    }

    @FXML
    void initialize() throws IOException {
        assert endTurnButton != null : "fx:id=\"endTurnButon\" was not injected: check your FXML file 'board.fxml'.";
        assert blue != null : "fx:id=\"blue\" was not injected: check your FXML file 'board.fxml'.";
        assert blues != null : "fx:id=\"blues\" was not injected: check your FXML file 'board.fxml'.";
        assert blues1 != null : "fx:id=\"blues1\" was not injected: check your FXML file 'board.fxml'.";
        assert blues11 != null : "fx:id=\"blues11\" was not injected: check your FXML file 'board.fxml'.";
        assert blues111 != null : "fx:id=\"blues111\" was not injected: check your FXML file 'board.fxml'.";
        assert brown != null : "fx:id=\"brown\" was not injected: check your FXML file 'board.fxml'.";
        assert browns != null : "fx:id=\"browns\" was not injected: check your FXML file 'board.fxml'.";
        assert browns1 != null : "fx:id=\"browns1\" was not injected: check your FXML file 'board.fxml'.";
        assert browns11 != null : "fx:id=\"browns11\" was not injected: check your FXML file 'board.fxml'.";
        assert browns111 != null : "fx:id=\"browns111\" was not injected: check your FXML file 'board.fxml'.";
        assert gold != null : "fx:id=\"gold\" was not injected: check your FXML file 'board.fxml'.";
        assert golds != null : "fx:id=\"golds\" was not injected: check your FXML file 'board.fxml'.";
        assert golds1 != null : "fx:id=\"golds1\" was not injected: check your FXML file 'board.fxml'.";
        assert golds11 != null : "fx:id=\"golds11\" was not injected: check your FXML file 'board.fxml'.";
        assert golds111 != null : "fx:id=\"golds111\" was not injected: check your FXML file 'board.fxml'.";
        assert green != null : "fx:id=\"green\" was not injected: check your FXML file 'board.fxml'.";
        assert greens != null : "fx:id=\"greens\" was not injected: check your FXML file 'board.fxml'.";
        assert greens1 != null : "fx:id=\"greens1\" was not injected: check your FXML file 'board.fxml'.";
        assert greens11 != null : "fx:id=\"greens11\" was not injected: check your FXML file 'board.fxml'.";
        assert greens111 != null : "fx:id=\"greens111\" was not injected: check your FXML file 'board.fxml'.";
        assert poins != null : "fx:id=\"poins\" was not injected: check your FXML file 'board.fxml'.";
        assert poins1 != null : "fx:id=\"poins1\" was not injected: check your FXML file 'board.fxml'.";
        assert poins11 != null : "fx:id=\"poins11\" was not injected: check your FXML file 'board.fxml'.";
        assert poins111 != null : "fx:id=\"poins111\" was not injected: check your FXML file 'board.fxml'.";
        assert red != null : "fx:id=\"red\" was not injected: check your FXML file 'board.fxml'.";
        assert reds != null : "fx:id=\"reds\" was not injected: check your FXML file 'board.fxml'.";
        assert reds1 != null : "fx:id=\"reds1\" was not injected: check your FXML file 'board.fxml'.";
        assert reds11 != null : "fx:id=\"reds11\" was not injected: check your FXML file 'board.fxml'.";
        assert reds111 != null : "fx:id=\"reds111\" was not injected: check your FXML file 'board.fxml'.";
        assert redsLeft != null : "fx:id=\"redsLeft\" was not injected: check your FXML file 'board.fxml'.";
        assert res1 != null : "fx:id=\"res1\" was not injected: check your FXML file 'board.fxml'.";
        assert res2 != null : "fx:id=\"res2\" was not injected: check your FXML file 'board.fxml'.";
        assert textRes != null : "fx:id=\"textRes\" was not injected: check your FXML file 'board.fxml'.";
        assert textRes1 != null : "fx:id=\"textRes1\" was not injected: check your FXML file 'board.fxml'.";
        assert textRes11 != null : "fx:id=\"textRes11\" was not injected: check your FXML file 'board.fxml'.";
        assert white != null : "fx:id=\"white\" was not injected: check your FXML file 'board.fxml'.";
        assert whites != null : "fx:id=\"whites\" was not injected: check your FXML file 'board.fxml'.";
        assert whites1 != null : "fx:id=\"whites1\" was not injected: check your FXML file 'board.fxml'.";
        assert whites11 != null : "fx:id=\"whites11\" was not injected: check your FXML file 'board.fxml'.";
        assert whites111 != null : "fx:id=\"whites111\" was not injected: check your FXML file 'board.fxml'.";
        ArrayList<ArrayList<Card>> cards = DeckGenerator.generateCards();
        playersResource = new ArrayList<>();
        playersPanes = new ArrayList<>();
        ImageView[][] imageViews = new ImageView[][]{
                new ImageView[]{reds, greens, blues, browns, whites, golds},
                new ImageView[]{reds1, greens1, blues1, browns1, whites1, golds1},
                new ImageView[]{reds11, greens11, blues11, browns11, whites11, golds11},
                new ImageView[]{reds111, greens111, blues111, browns111, whites111, golds111},
        };
        Image[] images = new Image[]{
                new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\red.png"),
                new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\green.png"),
                new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\blue.png"),
                new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\brown.png"),
                new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\white.png"),
                new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\gold.png"),
        };
        Text[][] texts = new Text[][]{
                new Text[]{redsResource, greensResource, bluesResource, brownsResource, whitesResource, goldsResource},
                new Text[]{redsResource1, greensResource1, bluesResource1, brownsResource1, whitesResource1, goldsResource1},
                new Text[]{redsResource11, greensResource11, bluesResource11, brownsResource11, whitesResource11, goldsResource11},
                new Text[]{redsResource111, greensResource111, bluesResource111, brownsResource111, whitesResource111, goldsResource111}
        };
        StackPane[][] panes = new StackPane[][]{
                new StackPane[]{redsPane, greensPane, bluesPane, brownsPane, whitesPane, goldsPane},
                new StackPane[]{redsPane1, greensPane1, bluesPane1, brownsPane1, whitesPane1, goldsPane1},
                new StackPane[]{redsPane11, greensPane11, bluesPane11, brownsPane11, whitesPane11, goldsPane11},
                new StackPane[]{redsPane111, greensPane111, bluesPane111, brownsPane111, whitesPane111, goldsPane111},
        };
        for (int player=0;player<4;player++){
            playersResource.add(new HashMap<>());
            playersPanes.add(new HashMap<>());
            for (int gem = 0; gem < 6; gem++) {
                playersResource.get(player).put(Gem.values()[gem], texts[player][gem]);
                playersPanes.get(player).put(Gem.values()[gem], panes[player][gem]);
            }
        }
        TradeRow tradeRow = new TradeRow(cards.get(0),cards.get(1),cards.get(2));
        players = new ArrayList<>();
        System.out.println("Enter number of players 2-4: ");
        Scanner scanner = new Scanner(System.in);
        int numberOfPlayers = scanner.nextInt();
        for (int i=0; i<numberOfPlayers; i++){
            System.out.println("Enter players name: ");
            String playersName = scanner.next();
            if (playersName.equals("Voldemort"))
                players.add(new Player(playersName,10000));
            else if (playersName.contains("AI"))
                players.add(new PlayerWithNodes(new Player(playersName), AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\28.txt")));
            else players.add(new Player(playersName));
        }
        scanner.close();
        board = new Board(tradeRow, players, 7,7,7,7,7,5);
        setPictures();
        currentPlayer = players.get(0);
        endTurnButton.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\end_turn.png"));
        showResources();
        for (int i=0;i<Gem.values().length;i++){
            imageViews[0][i].setImage(images[i]);
        }
        if (board.getPlayers().size()==2){
            for (int i=0;i<Gem.values().length;i++){
                imageViews[2][i].setImage(images[i]);
            }
        } else {
            for (int i=0;i<Gem.values().length;i++){
                imageViews[1][i].setImage(images[i]);
                imageViews[2][i].setImage(images[i]);
            }
        }
        if (board.getPlayers().size()==4) {
            for (int i=0;i<Gem.values().length;i++){
                imageViews[3][i].setImage(images[i]);
            }
        }
        updateFields();
        if (currentPlayer instanceof PlayerWithNodes) {
            try {
                AIController.playTurn(players, ((PlayerWithNodes) currentPlayer).getNodes(), board, AI.getPossibleMoves());
            } catch (GameLostException ignored) {}
            endTurn();
        }
    }
}