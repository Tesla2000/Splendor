package edu.ib.splendor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class GameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private int collectLimit;

    private void buyEstate(Tier reserve, int i) {

    }
    private void collectGem(int limit, Gem gem){

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
    void collectBlue(MouseEvent event, Object gem) {
        collectGem(collectLimit, Gem.BLUE);
    }

    @FXML
    void collectBrown(MouseEvent event) {
        collectGem(collectLimit, Gem.BROWN);
    }

    @FXML
    void collectGreen(MouseEvent event) {
        collectGem(collectLimit, Gem.GREEN);
    }

    @FXML
    void collectRed(MouseEvent event) {
        collectGem(collectLimit, Gem.RED);
    }

    @FXML
    void collectWhite(MouseEvent event) {
        collectGem(collectLimit, Gem.WHITE);
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

    }

}