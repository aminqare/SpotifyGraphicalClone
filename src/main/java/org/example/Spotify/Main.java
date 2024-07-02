package org.example.Spotify;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SpotifyApp app = new SpotifyApp();
        app.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
