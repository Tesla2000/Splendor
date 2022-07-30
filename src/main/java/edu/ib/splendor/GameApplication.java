package edu.ib.splendor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("setGame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 193, 118);
        stage.setTitle("Splendor");
        stage.setScene(scene);
        String css = getClass().getResource("board.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}