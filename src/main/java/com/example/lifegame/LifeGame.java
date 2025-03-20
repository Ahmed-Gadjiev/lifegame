package com.example.lifegame;

import javafx.application.Application;
import javafx.stage.Stage;

public class LifeGame extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();

        primaryStage.setTitle("Пример работы с невидимой сеткой на Canvas");

        game.start(primaryStage);
    }

}