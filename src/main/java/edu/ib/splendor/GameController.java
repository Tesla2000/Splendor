package edu.ib.splendor;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class GameController {
    private Player currentPlayer;
    private Board board;

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
    private Circle blue;

    @FXML
    private Text blues;

    @FXML
    private Text blues1;

    @FXML
    private Text blues11;

    @FXML
    private Text blues111;

    @FXML
    private Circle brown;

    @FXML
    private Text browns;

    @FXML
    private Text browns1;

    @FXML
    private Text browns11;

    @FXML
    private Text browns111;

    @FXML
    private Circle gold;

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
    private Text greens;

    @FXML
    private RadioButton reserve;

    @FXML
    private RadioButton build;

    @FXML
    private RadioButton getThree;

    @FXML
    private RadioButton getTwo;

    @FXML
    private Text greens1;

    @FXML
    private Text greens11;

    @FXML
    private Text greens111;

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
    private ImageView res1;

    @FXML
    private ImageView res2;

    @FXML
    private Text textRes;

    @FXML
    private Text textRes1;

    @FXML
    private Text textRes11;

    @FXML
    private Circle white;

    @FXML
    private Text whites;

    @FXML
    private Text whites1;

    @FXML
    private Text whites11;

    @FXML
    private Text whites111;



    private void buyEstate(Tier tier, int i) {
        Card card;
        int goldNeeded = 0;
        if (build.isSelected()){
            if (!tier.equals(Tier.RESERVE))
                card = board.getTradeRow().getCard(tier,i);
            else card = currentPlayer.getReserve()[i];
            for (Gem gem: card.getCost().keySet())
                goldNeeded += Math.max(card.getCost().get(gem)- currentPlayer.getPossession().get(gem)
                        - currentPlayer.getProduction().get(gem),0);
            if (goldNeeded <= currentPlayer.getPossession().get(Gem.GOLD)) {
                currentPlayer.changeGem(Gem.GOLD, goldNeeded);
                for (Gem gem: card.getCost().keySet())
                    currentPlayer.changeGem(gem, card.getCost().get(gem));
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
            if (!tier.equals(Tier.RESERVE)) {
                card = board.getTradeRow().getCard(tier, i);
                currentPlayer.addReserve(card);
                if (board.getStored(Gem.GOLD)>0)
                currentPlayer.changeGem(Gem.GOLD, -1);
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
            if (currentPlayer.getCollectLimit()==0) endTurn();
            else updateFields();
        }

    }

    private void endTurn() {
        board.turn();
        currentPlayer = board.getPlayers().get(0);
        updateFields();
        setPictures();
    }
    private void setPictures(){
            Image image = new Image(board.getTradeRow().getCard(Tier.FIRST, 0).getPicture());
            building10.setImage(image);
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 0).getPicture());
            building20.setImage(image);
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 0).getPicture());
            building30.setImage(image);
            image = new Image(board.getTradeRow().getCard(Tier.FIRST, 1).getPicture());
            building11.setImage(image);
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 1).getPicture());
            building21.setImage(image);
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 1).getPicture());
            building31.setImage(image);
            image = new Image(board.getTradeRow().getCard(Tier.FIRST, 2).getPicture());
            building12.setImage(image);
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 2).getPicture());
            building22.setImage(image);
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 2).getPicture());
            building32.setImage(image);
            image = new Image(board.getTradeRow().getCard(Tier.FIRST, 3).getPicture());
            building13.setImage(image);
            image = new Image(board.getTradeRow().getCard(Tier.SECOND, 3).getPicture());
            building23.setImage(image);
            image = new Image(board.getTradeRow().getCard(Tier.THIRD, 3).getPicture());
            building33.setImage(image);
            // Tutaj wstaw pozostałe


    }


    private void updateFields() {
        if (board.getPlayers().size()==2){
            reds.setText(String.valueOf(currentPlayer.getPossession().get(Gem.RED)));
            greens.setText(String.valueOf(currentPlayer.getPossession().get(Gem.GREEN)));
            browns.setText(String.valueOf(currentPlayer.getPossession().get(Gem.BROWN)));
            blues.setText(String.valueOf(currentPlayer.getPossession().get(Gem.BLUE)));
            whites.setText(String.valueOf(currentPlayer.getPossession().get(Gem.WHITE)));
            golds.setText(String.valueOf(currentPlayer.getPossession().get(Gem.GOLD)));
            reds11.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.RED)));
            greens11.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.GREEN)));
            browns11.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.BROWN)));
            blues11.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.BLUE)));
            whites11.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.WHITE)));
            golds11.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.GOLD)));
        } else if (board.getPlayers().size()==3){
            reds.setText(String.valueOf(currentPlayer.getPossession().get(Gem.RED)));
            greens.setText(String.valueOf(currentPlayer.getPossession().get(Gem.GREEN)));
            browns.setText(String.valueOf(currentPlayer.getPossession().get(Gem.BROWN)));
            blues.setText(String.valueOf(currentPlayer.getPossession().get(Gem.BLUE)));
            whites.setText(String.valueOf(currentPlayer.getPossession().get(Gem.WHITE)));
            golds.setText(String.valueOf(currentPlayer.getPossession().get(Gem.GOLD)));
            reds1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.RED)));
            greens1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.GREEN)));
            browns1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.BROWN)));
            blues1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.BLUE)));
            whites1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.WHITE)));
            golds1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.GOLD)));
            reds11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.RED)));
            greens11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.GREEN)));
            browns11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.BROWN)));
            blues11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.BLUE)));
            whites11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.WHITE)));
            golds11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.GOLD)));
        } else {
            reds.setText(String.valueOf(currentPlayer.getPossession().get(Gem.RED)));
            greens.setText(String.valueOf(currentPlayer.getPossession().get(Gem.GREEN)));
            browns.setText(String.valueOf(currentPlayer.getPossession().get(Gem.BROWN)));
            blues.setText(String.valueOf(currentPlayer.getPossession().get(Gem.BLUE)));
            whites.setText(String.valueOf(currentPlayer.getPossession().get(Gem.WHITE)));
            golds.setText(String.valueOf(currentPlayer.getPossession().get(Gem.GOLD)));
            reds1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.RED)));
            greens1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.GREEN)));
            browns1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.BROWN)));
            blues1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.BLUE)));
            whites1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.WHITE)));
            golds1.setText(String.valueOf(board.getPlayers().get(1).getPossession().get(Gem.GOLD)));
            reds11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.RED)));
            greens11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.GREEN)));
            browns11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.BROWN)));
            blues11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.BLUE)));
            whites11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.WHITE)));
            golds11.setText(String.valueOf(board.getPlayers().get(2).getPossession().get(Gem.GOLD)));
            reds111.setText(String.valueOf(board.getPlayers().get(3).getPossession().get(Gem.RED)));
            greens111.setText(String.valueOf(board.getPlayers().get(3).getPossession().get(Gem.GREEN)));
            browns111.setText(String.valueOf(board.getPlayers().get(3).getPossession().get(Gem.BROWN)));
            blues111.setText(String.valueOf(board.getPlayers().get(3).getPossession().get(Gem.BLUE)));
            whites111.setText(String.valueOf(board.getPlayers().get(3).getPossession().get(Gem.WHITE)));
            golds111.setText(String.valueOf(board.getPlayers().get(3).getPossession().get(Gem.GOLD)));
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
        ArrayList<Card> cardsFirst = new ArrayList<>();
        ArrayList<Card> cardsSecond = new ArrayList<>();
        ArrayList<Card> cardsThird = new ArrayList<>();
        for (int i=0; i<10; i++) {
            cardsFirst.add(new Card(Tier.FIRST, 1, 1, 1, 1, 1, Gem.RED, 1, "C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\splendor.jpg"));
            cardsSecond.add(new Card(Tier.SECOND, 1, 1, 1, 1, 1, Gem.RED, 1, "C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\splendor.jpg"));
            cardsThird.add(new Card(Tier.FIRST, 1, 1, 1, 1, 1, Gem.RED, 1, "C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\splendor.jpg"));
        }
        TradeRow tradeRow = new TradeRow(cardsFirst,cardsSecond,cardsThird);
        ArrayList<Player> players = new ArrayList<>();
        for (int i=0; i<4; i++){
            players.add(new Player());
        }
        board = new Board(tradeRow, players);
        setPictures();
        currentPlayer = players.get(0);
        updateFields();
    }

}