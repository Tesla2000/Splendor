package edu.ib.splendor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class GameController {
    private Player currentPlayer;
    private Board board;
    private Text redResource;
    private Text greenResource;
    private Text blueResource;
    private Text brownResource;
    private Text whiteResource;
    private Text goldResource;

    @FXML
    private ImageView aristocrat0;

    @FXML
    private ImageView aristocrat1;

    @FXML
    private ImageView aristocrat2;

    @FXML
    private ImageView aristocrat3;

    @FXML
    private Circle blue;

    @FXML
    private StackPane bluePane;

    @FXML
    private Text blues;

    @FXML
    private Text blues1;

    @FXML
    private Text blues11;

    @FXML
    private Text blues111;

    @FXML
    private Text bluesProduction;

    @FXML
    private Text bluesProduction1;

    @FXML
    private Text bluesProduction11;

    @FXML
    private Text bluesProduction111;

    @FXML
    private Circle brown;

    @FXML
    private StackPane brownPane;

    @FXML
    private Text browns;

    @FXML
    private Text browns1;

    @FXML
    private Text browns11;

    @FXML
    private Text browns111;

    @FXML
    private Text brownsProduction;

    @FXML
    private Text brownsProduction1;

    @FXML
    private Text brownsProduction11;

    @FXML
    private Text brownsProduction111;

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
    private Circle gold;

    @FXML
    private StackPane goldPane;

    @FXML
    private Text golds;

    @FXML
    private Text golds1;

    @FXML
    private Text golds11;

    @FXML
    private Text golds111;

    @FXML
    private Circle green;

    @FXML
    private StackPane greenPane;

    @FXML
    private Text greens;

    @FXML
    private Text greens1;

    @FXML
    private Text greens11;

    @FXML
    private Text greens111;

    @FXML
    private Text greensProduction;

    @FXML
    private Text greensProduction1;

    @FXML
    private Text greensProduction11;

    @FXML
    private Text greensProduction111;

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
    private Circle red;

    @FXML
    private StackPane redPane;

    @FXML
    private Text reds;

    @FXML
    private Text reds1;

    @FXML
    private Text reds11;

    @FXML
    private Text reds111;

    @FXML
    private VBox redsLeft;

    @FXML
    private Text redsProduction;

    @FXML
    private Text redsProduction1;

    @FXML
    private Text redsProduction11;

    @FXML
    private Text redsProduction111;

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
    private Circle white;

    @FXML
    private StackPane whitePane;

    @FXML
    private Text whites;

    @FXML
    private Text whites1;

    @FXML
    private Text whites11;

    @FXML
    private Text whites111;

    @FXML
    private Text whitesProduction;

    @FXML
    private Text whitesProduction1;

    @FXML
    private Text whitesProduction11;

    @FXML
    private Text whitesProduction111;


    private void buyEstate(Tier tier, int i) {
        Card card;
        HashMap<Gem, Integer> cost = new HashMap<>();
        int goldNeeded = 0;
        if (build.isSelected() && i>=0){
            if (!tier.equals(Tier.RESERVE)) {
                i--;
                card = board.getTradeRow().getCard(tier, i);
            }
            else card = currentPlayer.getReserve()[i];
            for (Gem gem: card.getCost().keySet()) {
                cost.put(gem, -Math.max(card.getCost().getOrDefault(gem,0) - currentPlayer.getPossession().getOrDefault(gem,0)
                        - currentPlayer.getProduction().getOrDefault(gem,0), 0));
                goldNeeded += Math.max(card.getCost().getOrDefault(gem,0) - currentPlayer.getPossession().getOrDefault(gem,0)
                        - currentPlayer.getProduction().getOrDefault(gem,0), 0);
            }
            if (goldNeeded <= currentPlayer.getPossession().get(Gem.GOLD)) {
                currentPlayer.changeGem(Gem.GOLD, goldNeeded);
                cost.put(Gem.GOLD, goldNeeded);
                for (Gem gem: card.getCost().keySet()) {
                    Integer paid = Math.max(0, card.getCost().getOrDefault(gem,0) - currentPlayer.getProduction().getOrDefault(gem,0));
                    currentPlayer.changeGem(gem, paid);
                    cost.put(gem, cost.getOrDefault(gem,0)+paid);
                }
                for (Gem gem: cost.keySet())
                    board.changeStored(gem, cost.get(gem));
                if (!tier.equals(Tier.RESERVE))
                    board.getTradeRow().takeCard(tier, i, currentPlayer);
                else {
                    currentPlayer.addCard(card);
                    currentPlayer.removeReserve(card);
                }
                endTurn();
            }
        }
        if (reserve.isSelected()){
            i--;
            if (i>=0)
                card = board.getTradeRow().getCard(tier, i);
            else card = board.getTradeRow().getHiddenCard(tier);
            if (Arrays.stream(currentPlayer.getReserve()).toList().contains(null)) {
                currentPlayer.addReserve(card);
                if (board.getStored(Gem.GOLD) > 0 && currentPlayer.allGems()<10) {
                    currentPlayer.changeGem(Gem.GOLD, -1);
                    board.changeStored(Gem.GOLD, -1);
                }
                board.getTradeRow().takeCardToReserve(tier, i);
                endTurn();
            }
        }
    }

    private void collectGem(Gem gem){
        if (currentPlayer.getCollectLimit() == 0)
            currentPlayer.setCollectLimit(3);
        if (getTwo.isSelected() && currentPlayer.getCollectLimit()==3 && board.getStored(gem)>=3) {
            int amount = Math.min(2, 10 - currentPlayer.allGems());
            currentPlayer.changeGem(gem, -amount);
            board.changeStored(gem, -amount);
            endTurn();
        }
        else if (board.getStored(gem)>0 && !currentPlayer.gemInTaken(gem)){
            if (currentPlayer.allGems() >= 10) endTurn();
            currentPlayer.changeGem(gem, -1);
            board.changeStored(gem, -1);
            currentPlayer.setCollectLimit(currentPlayer.getCollectLimit()-1);
            currentPlayer.addToTaken(gem);
            if (currentPlayer.getCollectLimit()==0) endTurn();
            else updateFields();
        }

    }

    private void endTurn() {
        board.turn();
        currentPlayer.clearTaken();
        getAristocrats();
        currentPlayer = board.getPlayers().get(0);
        updateFields();
        setPictures();
    }


    private void getAristocrats() {
        for (Aristocrat aristocrat: board.getAristocrats())
            if (
                    aristocrat.getBlue()<=currentPlayer.getProduction().getOrDefault(Gem.BLUE,0) &&
                            aristocrat.getRed()<=currentPlayer.getProduction().getOrDefault(Gem.RED,0) &&
                            aristocrat.getGreen()<=currentPlayer.getProduction().getOrDefault(Gem.GREEN,0) &&
                            aristocrat.getBrown()<=currentPlayer.getProduction().getOrDefault(Gem.BROWN,0) &&
                            aristocrat.getWhite()<=currentPlayer.getProduction().getOrDefault(Gem.WHITE,0)
            ) {
                currentPlayer.setPoints(currentPlayer.getPoints() + aristocrat.getPoints());
                board.removeAristocrat(aristocrat);
            }
    }

    private void setPictures(){
        Image image;
        try{
            image = new Image(currentPlayer.getReserve()[0].getPicture());
            res1.setImage(image);
        } catch (Exception e){
            res1.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(currentPlayer.getReserve()[1].getPicture());
            res2.setImage(image);
        } catch (Exception e){
            res2.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(currentPlayer.getReserve()[2].getPicture());
            res3.setImage(image);
        } catch (Exception e){
            res3.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.FIRST, 0).getPicture());
            building11.setImage(image);
        } catch (Exception e){
            building11.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 0).getPicture());
            building21.setImage(image);
        } catch (Exception e){
            building21.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 0).getPicture());
            building31.setImage(image);
        } catch (Exception e){
            building31.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.FIRST, 1).getPicture());
            building12.setImage(image);
        } catch (Exception e){
            building12.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 1).getPicture());
            building22.setImage(image);
        } catch (Exception e){
            building22.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 1).getPicture());
            building32.setImage(image);
        } catch (Exception e){
            building32.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.FIRST, 2).getPicture());
            building13.setImage(image);
        } catch (Exception e){
            building13.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 2).getPicture());
            building23.setImage(image);
        } catch (Exception e){
            building23.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 2).getPicture());
            building33.setImage(image);
        } catch (Exception e){
            building33.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.FIRST, 3).getPicture());
            building14.setImage(image);
        } catch (Exception e){
            building14.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 3).getPicture());
            building24.setImage(image);
        } catch (Exception e){
            building24.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try{
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 3).getPicture());
            building34.setImage(image);
        } catch (Exception e){
            building34.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        building30.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        building20.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        building10.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        try {
            aristocrat0.setImage(new Image(board.getAristocrats().get(0).getImage()));
        }catch (Exception e) {
            aristocrat0.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try {
            aristocrat1.setImage(new Image(board.getAristocrats().get(1).getImage()));
        }catch (Exception e) {
            aristocrat1.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try {
            aristocrat2.setImage(new Image(board.getAristocrats().get(2).getImage()));
        }catch (Exception e) {
            aristocrat2.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }
        try {
            aristocrat3.setImage(new Image(board.getAristocrats().get(3).getImage()));
        }catch (Exception e) {
            aristocrat3.setImage(new Image("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\splendor.jpg"));
        }

    }
    private void showResources(){
        String string;
        string =  board.getStored(Gem.RED).toString();
        if (redResource == null) {
            redResource = new Text(red.getCenterX(), red.getCenterY(), string);
            redPane.getChildren().add(redResource);
        }
        else redResource.setText(string);
        string =  board.getStored(Gem.GREEN).toString();
        if (greenResource == null) {
            greenResource = new Text(green.getCenterX(), green.getCenterY(), string);
            greenPane.getChildren().add(greenResource);
        }
        else greenResource.setText(string);
        string =  board.getStored(Gem.BLUE).toString();
        if (blueResource == null) {
            blueResource = new Text(blue.getCenterX(), blue.getCenterY(), string);
            bluePane.getChildren().add(blueResource);
        }
        else blueResource.setText(string);
        string =  board.getStored(Gem.BROWN).toString();
        if (brownResource == null) {
            brownResource = new Text(brown.getCenterX(), brown.getCenterY(), string);
            brownPane.getChildren().add(brownResource);
        }
        else brownResource.setText(string);
        string =  board.getStored(Gem.WHITE).toString();
        if (whiteResource == null) {
            whiteResource = new Text(white.getCenterX(), white.getCenterY(), string);
            whitePane.getChildren().add(whiteResource);
        }
        else whiteResource.setText(string);
        string =  board.getStored(Gem.GOLD).toString();
        if (goldResource == null) {
            goldResource = new Text(gold.getCenterX(), gold.getCenterY(), string);
            goldPane.getChildren().add(goldResource);
        }
        else goldResource.setText(string);
    }

    private void updateFields() {
        showResources();
        reds.setText("Reds: " + currentPlayer.getPossession().get(Gem.RED));
        greens.setText("Greens: " + currentPlayer.getPossession().get(Gem.GREEN));
        browns.setText("Browns: " + currentPlayer.getPossession().get(Gem.BROWN));
        blues.setText("Blues: " + currentPlayer.getPossession().get(Gem.BLUE));
        whites.setText("Whites: " + currentPlayer.getPossession().get(Gem.WHITE));
        redsProduction.setText("Reds production: " + currentPlayer.getProduction().get(Gem.RED));
        greensProduction.setText("Greens production: " + currentPlayer.getProduction().get(Gem.GREEN));
        brownsProduction.setText("Browns production: " + currentPlayer.getProduction().get(Gem.BROWN));
        bluesProduction.setText("Blues production: " + currentPlayer.getProduction().get(Gem.BLUE));
        whitesProduction.setText("Whites production: " + currentPlayer.getProduction().get(Gem.WHITE));
        playerName.setText(currentPlayer.getName());
        golds.setText("Golds: " + currentPlayer.getPossession().get(Gem.GOLD));
        poins.setText("Points: " + currentPlayer.getPoints());
        if (currentPlayer.getReserve()[0]!=null)
            res1.setImage(new Image(currentPlayer.getReserve()[0].getPicture()));
        if (currentPlayer.getReserve()[1]!=null)
            res2.setImage(new Image(currentPlayer.getReserve()[1].getPicture()));
        if (currentPlayer.getReserve()[2]!=null)
            res3.setImage(new Image(currentPlayer.getReserve()[2].getPicture()));
        if (board.getPlayers().size()==2){
            reds11.setText("Reds: " + board.getPlayers().get(1).getPossession().get(Gem.RED));
            greens11.setText("Greens: " + board.getPlayers().get(1).getPossession().get(Gem.GREEN));
            browns11.setText("Browns: " + board.getPlayers().get(1).getPossession().get(Gem.BROWN));
            blues11.setText("Blues: " + board.getPlayers().get(1).getPossession().get(Gem.BLUE));
            whites11.setText("Whites: " + board.getPlayers().get(1).getPossession().get(Gem.WHITE));
            golds11.setText("Golds: " + board.getPlayers().get(1).getPossession().get(Gem.GOLD));
            redsProduction11.setText("Reds production: " + board.getPlayers().get(1).getProduction().get(Gem.RED));
            greensProduction11.setText("Greens production: " + board.getPlayers().get(1).getProduction().get(Gem.GREEN));
            brownsProduction11.setText("Browns production: " + board.getPlayers().get(1).getProduction().get(Gem.BROWN));
            bluesProduction11.setText("Blues production: " + board.getPlayers().get(1).getProduction().get(Gem.BLUE));
            whitesProduction11.setText("Whites production: " + board.getPlayers().get(1).getProduction().get(Gem.WHITE));
            playerName11.setText(board.getPlayers().get(1).getName());
            poins11.setText("Points: " + board.getPlayers().get(1).getPoints());
            textRes1.setText("Reserved: " + board.getPlayers().get(1).getReserveNumber());
        } else {
            reds1.setText("Reds: " + board.getPlayers().get(1).getPossession().get(Gem.RED));
            greens1.setText("Greens: " + board.getPlayers().get(1).getPossession().get(Gem.GREEN));
            browns1.setText("Browns: " + board.getPlayers().get(1).getPossession().get(Gem.BROWN));
            blues1.setText("Blues: " + board.getPlayers().get(1).getPossession().get(Gem.BLUE));
            whites1.setText("Whites: " + board.getPlayers().get(1).getPossession().get(Gem.WHITE));
            golds1.setText("Golds: " + board.getPlayers().get(1).getPossession().get(Gem.GOLD));
            poins1.setText("Points: " + board.getPlayers().get(1).getPoints());
            redsProduction1.setText("Reds production: " + board.getPlayers().get(1).getProduction().get(Gem.RED));
            greensProduction1.setText("Greens production: " + board.getPlayers().get(1).getProduction().get(Gem.GREEN));
            brownsProduction1.setText("Browns production: " + board.getPlayers().get(1).getProduction().get(Gem.BROWN));
            bluesProduction1.setText("Blues production: " + board.getPlayers().get(1).getProduction().get(Gem.BLUE));
            whitesProduction1.setText("Whites production: " + board.getPlayers().get(1).getProduction().get(Gem.WHITE));
            playerName1.setText(board.getPlayers().get(1).getName());
            textRes.setText("Reserved: " + board.getPlayers().get(1).getReserveNumber());
            reds11.setText("Reds: " + board.getPlayers().get(2).getPossession().get(Gem.RED));
            greens11.setText("Greens: " + board.getPlayers().get(2).getPossession().get(Gem.GREEN));
            browns11.setText("Browns: " + board.getPlayers().get(2).getPossession().get(Gem.BROWN));
            blues11.setText("Blues: " + board.getPlayers().get(2).getPossession().get(Gem.BLUE));
            whites11.setText("Whites: " + board.getPlayers().get(2).getPossession().get(Gem.WHITE));
            golds11.setText("Golds: " + board.getPlayers().get(2).getPossession().get(Gem.GOLD));
            poins11.setText("Points: " + board.getPlayers().get(2).getPoints());
            textRes1.setText("Reserved: " + board.getPlayers().get(2).getReserveNumber());
            redsProduction11.setText("Reds production: " + board.getPlayers().get(2).getProduction().get(Gem.RED));
            greensProduction11.setText("Greens production: " + board.getPlayers().get(2).getProduction().get(Gem.GREEN));
            brownsProduction11.setText("Browns production: " + board.getPlayers().get(2).getProduction().get(Gem.BROWN));
            bluesProduction11.setText("Blues production: " + board.getPlayers().get(2).getProduction().get(Gem.BLUE));
            whitesProduction11.setText("Whites production: " + board.getPlayers().get(2).getProduction().get(Gem.WHITE));
            playerName11.setText(board.getPlayers().get(2).getName());
        }
        if (board.getPlayers().size()==4) {
            reds111.setText("Reds: " + board.getPlayers().get(3).getPossession().get(Gem.RED));
            greens111.setText("Greens: " + board.getPlayers().get(3).getPossession().get(Gem.GREEN));
            browns111.setText("Browns: " + board.getPlayers().get(3).getPossession().get(Gem.BROWN));
            blues111.setText("Blues: " + board.getPlayers().get(3).getPossession().get(Gem.BLUE));
            whites111.setText("Whites: " + board.getPlayers().get(3).getPossession().get(Gem.WHITE));
            golds111.setText("Golds: " + board.getPlayers().get(3).getPossession().get(Gem.GOLD));
            poins111.setText("Points: " + board.getPlayers().get(3).getPoints());
            textRes11.setText("Reserved: " + board.getPlayers().get(3).getReserveNumber());
            redsProduction111.setText("Reds production: " + board.getPlayers().get(3).getProduction().get(Gem.RED));
            greensProduction111.setText("Greens production: " + board.getPlayers().get(3).getProduction().get(Gem.GREEN));
            brownsProduction111.setText("Browns production: " + board.getPlayers().get(3).getProduction().get(Gem.BROWN));
            bluesProduction111.setText("Blues production: " + board.getPlayers().get(3).getProduction().get(Gem.BLUE));
            whitesProduction111.setText("Whites production: " + board.getPlayers().get(3).getProduction().get(Gem.WHITE));
            playerName111.setText(board.getPlayers().get(3).getName());
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
    void initialize() {
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
        ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
        TradeRow tradeRow = new TradeRow(cards.get(0),cards.get(1),cards.get(2));
        ArrayList<Player> players = new ArrayList<>();
        for (int i=0; i<2; i++){
            players.add(new Player());
        }
        board = new Board(tradeRow, players, 7,7,7,7,7,5);
        setPictures();
        currentPlayer = players.get(0);
        updateFields();
    }

}