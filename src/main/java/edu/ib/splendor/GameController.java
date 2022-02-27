package edu.ib.splendor;

import java.net.URL;
import java.util.*;

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
import javafx.scene.text.Font;
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
            if (currentPlayer.getCollectLimit()==0 || currentPlayer.allGems() >= 10) endTurn();
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
        ArrayList<Aristocrat> toRemove = new ArrayList<>();
        for (Aristocrat aristocrat: board.getAristocrats())
            if (
                    aristocrat.getBlue()<=currentPlayer.getProduction().getOrDefault(Gem.BLUE,0) &&
                            aristocrat.getRed()<=currentPlayer.getProduction().getOrDefault(Gem.RED,0) &&
                            aristocrat.getGreen()<=currentPlayer.getProduction().getOrDefault(Gem.GREEN,0) &&
                            aristocrat.getBrown()<=currentPlayer.getProduction().getOrDefault(Gem.BROWN,0) &&
                            aristocrat.getWhite()<=currentPlayer.getProduction().getOrDefault(Gem.WHITE,0)
            ) {
                toRemove.add(aristocrat);
                currentPlayer.addAristocrat(aristocrat);
            }
        for (Aristocrat aristocrat: toRemove){
            board.removeAristocrat(aristocrat);
        }
    }

    private void setPictures(){
        Image image;
        try{
            image = new Image(currentPlayer.getReserve()[0].getPicture());
            res1.setImage(image);
        } catch (Exception e){
            res1.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\reserve.png"));
        }
        try{
            image = new Image(currentPlayer.getReserve()[1].getPicture());
            res2.setImage(image);
        } catch (Exception e){
            res2.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\reserve.png"));
        }
        try{
            image = new Image(currentPlayer.getReserve()[2].getPicture());
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
        Font font = new Font("Verdana", 20);
        if (redResource == null) {
            Text text = new Text(red.getX(), red.getY(), string);
            text.setFont(font);
            redResource = text;
            redPane.getChildren().add(redResource);
        }
        else redResource.setText(string);
        string =  board.getStored(Gem.GREEN).toString();
        if (greenResource == null) {
            Text text = new Text(green.getX(), green.getY(), string);
            text.setFont(font);
            greenResource = text;
            greenPane.getChildren().add(greenResource);
        }
        else greenResource.setText(string);
        string =  board.getStored(Gem.BLUE).toString();
        if (blueResource == null) {
            Text text = new Text(blue.getX(), blue.getY(), string);
            text.setFont(font);
            blueResource = text;
            bluePane.getChildren().add(blueResource);
        }
        else blueResource.setText(string);
        string =  board.getStored(Gem.BROWN).toString();
        if (brownResource == null) {
            Text text = new Text(brown.getX(), brown.getY(), string);
            text.setFont(font);
            brownResource = text;
            brownPane.getChildren().add(brownResource);
        }
        else brownResource.setText(string);
        string =  board.getStored(Gem.WHITE).toString();
        if (whiteResource == null) {
            Text text = new Text(white.getX(), white.getY(), string);
            text.setFont(font);
            whiteResource = text;
            whitePane.getChildren().add(whiteResource);
        }
        else whiteResource.setText(string);
        string =  board.getStored(Gem.GOLD).toString();
        if (goldResource == null) {
            Text text = new Text(gold.getX(), gold.getY(), string);
            text.setFont(font);
            goldResource = text;
            goldPane.getChildren().add(goldResource);
        }
        else goldResource.setText(string);
    }

    private void updateFields() {
        showResources();
        Font font = new Font("Verdana", 20);
        String string = currentPlayer.getPossession().getOrDefault(Gem.RED, 0) + "+" + currentPlayer.getProduction().getOrDefault(Gem.RED, 0);
        if (redsResource == null) {
            redsResource = new Text(reds.getX(), reds.getY(), string);
            redsResource.setFont(font);
            redsPane.getChildren().add(redsResource);
        } else redsResource.setText(string);
        string = currentPlayer.getPossession().getOrDefault(Gem.GREEN, 0) + "+" + currentPlayer.getProduction().getOrDefault(Gem.GREEN,0);
        if (greensResource==null) {
            greensResource = new Text(greens.getX(), greens.getY(), string);
            greensResource.setFont(font);
            greensPane.getChildren().add(greensResource);
        } else greensResource.setText(string);
        string = currentPlayer.getPossession().getOrDefault(Gem.BLUE, 0) + "+" + currentPlayer.getProduction().getOrDefault(Gem.BLUE,0);
        if (bluesResource == null) {
            bluesResource = new Text(blues.getX(), blues.getY(), currentPlayer.getPossession().getOrDefault(Gem.BLUE, 0) + "+" + currentPlayer.getProduction().getOrDefault(Gem.BLUE, 0));
            bluesResource.setFont(font);
            bluesPane.getChildren().add(bluesResource);
        } else bluesResource.setText(string);
        string = currentPlayer.getPossession().getOrDefault(Gem.BROWN, 0) + "+" + currentPlayer.getProduction().getOrDefault(Gem.BROWN,0);
        if (brownsResource == null) {
            brownsResource = new Text(browns.getX(), browns.getY(), currentPlayer.getPossession().getOrDefault(Gem.BROWN, 0) + "+" + currentPlayer.getProduction().getOrDefault(Gem.BROWN, 0));
            brownsResource.setFont(font);
            brownsPane.getChildren().add(brownsResource);
        } else brownsResource.setText(string);
        string = currentPlayer.getPossession().getOrDefault(Gem.WHITE, 0) + "+" + currentPlayer.getProduction().getOrDefault(Gem.WHITE,0);
        if (whitesResource==null) {
            whitesResource = new Text(whites.getX(), whites.getY(), currentPlayer.getPossession().getOrDefault(Gem.WHITE, 0) + "+" + currentPlayer.getProduction().getOrDefault(Gem.WHITE, 0));
            whitesResource.setFont(font);
            whitesPane.getChildren().add(whitesResource);
        } else whitesResource.setText(string);
        string = currentPlayer.getPossession().getOrDefault(Gem.GOLD, 0).toString();
        if (goldsResource==null) {
            goldsResource = new Text(golds.getX(), golds.getY(), currentPlayer.getPossession().getOrDefault(Gem.GOLD, 0).toString());
            goldsResource.setFont(font);
            goldsPane.getChildren().add(goldsResource);
        } else goldsResource.setText(string);
        playerName.setText(currentPlayer.getName());
        poins.setText("Points: " + currentPlayer.getPoints());
        if (currentPlayer.getReserve()[0]!=null)
            res1.setImage(new Image(currentPlayer.getReserve()[0].getPicture()));
        if (currentPlayer.getReserve()[1]!=null)
            res2.setImage(new Image(currentPlayer.getReserve()[1].getPicture()));
        if (currentPlayer.getReserve()[2]!=null)
            res3.setImage(new Image(currentPlayer.getReserve()[2].getPicture()));
        if (board.getPlayers().size()==2){
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.RED, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.RED, 0);
            if (redsResource11 == null) {
                redsResource11 = new Text(reds.getX(), reds.getY(), string);
                redsResource11.setFont(font);
                redsPane11.getChildren().add(redsResource11);
            } else redsResource11.setText(string);
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.GREEN, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.GREEN,0);
            if (greensResource11==null) {
                greensResource11 = new Text(greens.getX(), greens.getY(), string);
                greensResource11.setFont(font);
                greensPane11.getChildren().add(greensResource11);
            } else greensResource11.setText(string);
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.BLUE, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.BLUE,0);
            if (bluesResource11 == null) {
                bluesResource11 = new Text(blues.getX(), blues.getY(), board.getPlayers().get(1).getPossession().getOrDefault(Gem.BLUE, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.BLUE, 0));
                bluesResource11.setFont(font);
                bluesPane11.getChildren().add(bluesResource11);
            } else bluesResource11.setText(string);
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.BROWN, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.BROWN,0);
            if (brownsResource11 == null) {
                brownsResource11 = new Text(browns.getX(), browns.getY(), board.getPlayers().get(1).getPossession().getOrDefault(Gem.BROWN, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.BROWN, 0));
                brownsResource11.setFont(font);
                brownsPane11.getChildren().add(brownsResource11);
            } else brownsResource11.setText(string);
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.WHITE, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.WHITE,0);
            if (whitesResource11==null) {
                whitesResource11 = new Text(whites.getX(), whites.getY(), board.getPlayers().get(1).getPossession().getOrDefault(Gem.WHITE, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.WHITE, 0));
                whitesResource11.setFont(font);
                whitesPane11.getChildren().add(whitesResource11);
            } else whitesResource11.setText(string);
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.GOLD, 0).toString();
            if (goldsResource11==null) {
                goldsResource11 = new Text(golds.getX(), golds.getY(), board.getPlayers().get(1).getPossession().getOrDefault(Gem.GOLD, 0).toString());
                goldsResource11.setFont(font);
                goldsPane11.getChildren().add(goldsResource11);
            } else goldsResource11.setText(string);
            playerName11.setText(board.getPlayers().get(1).getName());
            poins11.setText("Points: " + board.getPlayers().get(1).getPoints());
            textRes1.setText("Reserved: " + board.getPlayers().get(1).getReserveNumber());
        } else {
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.RED, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.RED, 0);
            if (redsResource1 == null) {
                redsResource1 = new Text(reds.getX(), reds.getY(), string);
                redsResource1.setFont(font);
                redsPane1.getChildren().add(redsResource1);
            } else redsResource1.setText(string);
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.GREEN, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.GREEN,0);
            if (greensResource1==null) {
                greensResource1 = new Text(greens.getX(), greens.getY(), string);
                greensResource1.setFont(font);
                greensPane1.getChildren().add(greensResource1);
            } else greensResource1.setText(string);
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.BLUE, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.BLUE,0);
            if (bluesResource1 == null) {
                bluesResource1 = new Text(blues.getX(), blues.getY(), board.getPlayers().get(1).getPossession().getOrDefault(Gem.BLUE, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.BLUE, 0));
                bluesResource1.setFont(font);
                bluesPane1.getChildren().add(bluesResource1);
            } else bluesResource1.setText(string);
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.BROWN, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.BROWN,0);
            if (brownsResource1 == null) {
                brownsResource1 = new Text(browns.getX(), browns.getY(), board.getPlayers().get(1).getPossession().getOrDefault(Gem.BROWN, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.BROWN, 0));
                brownsResource1.setFont(font);
                brownsPane1.getChildren().add(brownsResource1);
            } else bluesResource1.setText(string);
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.WHITE, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.WHITE,0);
            if (whitesResource1==null) {
                whitesResource1 = new Text(whites.getX(), whites.getY(), board.getPlayers().get(1).getPossession().getOrDefault(Gem.WHITE, 0) + "+" + board.getPlayers().get(1).getProduction().getOrDefault(Gem.WHITE, 0));
                whitesResource1.setFont(font);
                whitesPane1.getChildren().add(whitesResource1);
            } else whitesResource1.setText(string);
            string = board.getPlayers().get(1).getPossession().getOrDefault(Gem.GOLD, 0).toString();
            if (goldsResource1==null) {
                goldsResource1 = new Text(golds.getX(), golds.getY(), board.getPlayers().get(1).getPossession().getOrDefault(Gem.GOLD, 0).toString());
                goldsResource1.setFont(font);
                goldsPane1.getChildren().add(goldsResource1);
            } else goldsResource1.setText(string);
            string = board.getPlayers().get(2).getPossession().getOrDefault(Gem.RED, 0) + "+" + board.getPlayers().get(2).getProduction().getOrDefault(Gem.RED, 0);
            if (redsResource11 == null) {
                redsResource11 = new Text(reds.getX(), reds.getY(), string);
                redsResource11.setFont(font);
                redsPane11.getChildren().add(redsResource11);
            } else redsResource11.setText(string);
            string = board.getPlayers().get(2).getPossession().getOrDefault(Gem.GREEN, 0) + "+" + board.getPlayers().get(2).getProduction().getOrDefault(Gem.GREEN,0);
            if (greensResource11==null) {
                greensResource11 = new Text(greens.getX(), greens.getY(), string);
                greensResource11.setFont(font);
                greensPane11.getChildren().add(greensResource11);
            } else greensResource11.setText(string);
            string = board.getPlayers().get(2).getPossession().getOrDefault(Gem.BLUE, 0) + "+" + board.getPlayers().get(2).getProduction().getOrDefault(Gem.BLUE,0);
            if (bluesResource11 == null) {
                bluesResource11 = new Text(blues.getX(), blues.getY(), board.getPlayers().get(2).getPossession().getOrDefault(Gem.BLUE, 0) + "+" + board.getPlayers().get(2).getProduction().getOrDefault(Gem.BLUE, 0));
                bluesResource11.setFont(font);
                bluesPane11.getChildren().add(bluesResource11);
            } else bluesResource11.setText(string);
            string = board.getPlayers().get(2).getPossession().getOrDefault(Gem.BROWN, 0) + "+" + board.getPlayers().get(2).getProduction().getOrDefault(Gem.BROWN,0);
            if (brownsResource11 == null) {
                brownsResource11 = new Text(browns.getX(), browns.getY(), board.getPlayers().get(2).getPossession().getOrDefault(Gem.BROWN, 0) + "+" + board.getPlayers().get(2).getProduction().getOrDefault(Gem.BROWN, 0));
                brownsResource11.setFont(font);
                brownsPane11.getChildren().add(brownsResource11);
            } else brownsResource11.setText(string);
            string = board.getPlayers().get(2).getPossession().getOrDefault(Gem.WHITE, 0) + "+" + board.getPlayers().get(2).getProduction().getOrDefault(Gem.WHITE,0);
            if (whitesResource11==null) {
                whitesResource11 = new Text(whites.getX(), whites.getY(), board.getPlayers().get(2).getPossession().getOrDefault(Gem.WHITE, 0) + "+" + board.getPlayers().get(2).getProduction().getOrDefault(Gem.WHITE, 0));
                whitesResource11.setFont(font);
                whitesPane11.getChildren().add(whitesResource11);
            } else whitesResource11.setText(string);
            string = board.getPlayers().get(2).getPossession().getOrDefault(Gem.GOLD, 0).toString();
            if (goldsResource11==null) {
                goldsResource11 = new Text(golds.getX(), golds.getY(), board.getPlayers().get(2).getPossession().getOrDefault(Gem.GOLD, 0).toString());
                goldsResource11.setFont(font);
                goldsPane11.getChildren().add(goldsResource11);
            } else goldsResource11.setText(string);
            poins1.setText("Points: " + board.getPlayers().get(1).getPoints());
            playerName1.setText(board.getPlayers().get(1).getName());
            textRes.setText("Reserved: " + board.getPlayers().get(1).getReserveNumber());
            textRes1.setText("Reserved: " + board.getPlayers().get(2).getReserveNumber());
            playerName11.setText(board.getPlayers().get(2).getName());
        }
        if (board.getPlayers().size()==4) {
            string = board.getPlayers().get(3).getPossession().getOrDefault(Gem.RED, 0) + "+" + board.getPlayers().get(3).getProduction().getOrDefault(Gem.RED, 0);
            if (redsResource111 == null) {
                redsResource111 = new Text(reds.getX(), reds.getY(), string);
                redsResource111.setFont(font);
                redsPane111.getChildren().add(redsResource111);
            } else redsResource111.setText(string);
            string = board.getPlayers().get(3).getPossession().getOrDefault(Gem.GREEN, 0) + "+" + board.getPlayers().get(3).getProduction().getOrDefault(Gem.GREEN,0);
            if (greensResource111==null) {
                greensResource111 = new Text(greens.getX(), greens.getY(), string);
                greensResource111.setFont(font);
                greensPane111.getChildren().add(greensResource111);
            } else greensResource111.setText(string);
            string = board.getPlayers().get(3).getPossession().getOrDefault(Gem.BLUE, 0) + "+" + board.getPlayers().get(3).getProduction().getOrDefault(Gem.BLUE,0);
            if (bluesResource111 == null) {
                bluesResource111 = new Text(blues.getX(), blues.getY(), board.getPlayers().get(3).getPossession().getOrDefault(Gem.BLUE, 0) + "+" + board.getPlayers().get(3).getProduction().getOrDefault(Gem.BLUE, 0));
                bluesResource111.setFont(font);
                bluesPane111.getChildren().add(bluesResource111);
            } else bluesResource111.setText(string);
            string = board.getPlayers().get(3).getPossession().getOrDefault(Gem.BROWN, 0) + "+" + board.getPlayers().get(3).getProduction().getOrDefault(Gem.BROWN,0);
            if (brownsResource111 == null) {
                brownsResource111 = new Text(browns.getX(), browns.getY(), board.getPlayers().get(3).getPossession().getOrDefault(Gem.BROWN, 0) + "+" + board.getPlayers().get(3).getProduction().getOrDefault(Gem.BROWN, 0));
                brownsResource111.setFont(font);
                brownsPane111.getChildren().add(brownsResource111);
            } else brownsResource111.setText(string);
            string = board.getPlayers().get(3).getPossession().getOrDefault(Gem.WHITE, 0) + "+" + board.getPlayers().get(3).getProduction().getOrDefault(Gem.WHITE,0);
            if (whitesResource111==null) {
                whitesResource111 = new Text(whites.getX(), whites.getY(), board.getPlayers().get(3).getPossession().getOrDefault(Gem.WHITE, 0) + "+" + board.getPlayers().get(3).getProduction().getOrDefault(Gem.WHITE, 0));
                whitesResource111.setFont(font);
                whitesPane111.getChildren().add(whitesResource111);
            } else whitesResource111.setText(string);
            string = board.getPlayers().get(3).getPossession().getOrDefault(Gem.GOLD, 0).toString();
            if (goldsResource111==null) {
                goldsResource111 = new Text(golds.getX(), golds.getY(), board.getPlayers().get(3).getPossession().getOrDefault(Gem.GOLD, 0).toString());
                goldsResource111.setFont(font);
                goldsPane111.getChildren().add(goldsResource111);
            } else goldsResource111.setText(string);
            poins111.setText("Points: " + board.getPlayers().get(3).getPoints());
            textRes11.setText("Reserved: " + board.getPlayers().get(3).getReserveNumber());
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
        System.out.println("Enter number of players 2-4: ");
        Scanner scanner = new Scanner(System.in);
        int numberOfPlayers = scanner.nextInt();
        for (int i=0; i<numberOfPlayers; i++){
            System.out.println("Enter players name: ");
            String playersName = scanner.next();
            if (playersName.equals("Voldemort"))
                players.add(new Player(playersName,10000));
            else players.add(new Player(playersName));
        }
        scanner.close();
        board = new Board(tradeRow, players, 7,7,7,7,7,5);
        setPictures();
        currentPlayer = players.get(0);
        red.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\red.png"));
        green.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\green.png"));
        blue.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\blue.png"));
        brown.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\brown.png"));
        white.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\white.png"));
        gold.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\gold.png"));
        reds.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\red.png"));
        greens.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\green.png"));
        blues.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\blue.png"));
        browns.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\brown.png"));
        whites.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\white.png"));
        golds.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\gold.png"));
        if (board.getPlayers().size()==2){
            reds11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\red.png"));
            greens11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\green.png"));
            blues11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\blue.png"));
            browns11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\brown.png"));
            whites11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\white.png"));
            golds11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\gold.png"));
        } else {
            reds1.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\red.png"));
            greens1.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\green.png"));
            blues1.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\blue.png"));
            browns1.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\brown.png"));
            whites1.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\white.png"));
            golds1.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\gold.png"));
            reds11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\red.png"));
            greens11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\green.png"));
            blues11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\blue.png"));
            browns11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\brown.png"));
            whites11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\white.png"));
            golds11.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\gold.png"));
        }
        if (board.getPlayers().size()==4) {
            reds111.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\red.png"));
            greens111.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\green.png"));
            blues111.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\blue.png"));
            browns111.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\brown.png"));
            whites111.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\white.png"));
            golds111.setImage(new Image("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\gold.png"));
        }
        updateFields();
    }

}