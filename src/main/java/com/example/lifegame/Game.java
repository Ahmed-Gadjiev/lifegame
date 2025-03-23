package com.example.lifegame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game {

    private final Grid grid = new Grid();

    void start(Stage primaryStage) {
        primaryStage.setTitle("LifeGame");
        Canvas canvas = new Canvas(Grid.CANVAS_WIDTH, Grid.CANVAS_HEIGHT);
        BorderPane root = new BorderPane();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.3), event -> {
                grid.drawGrid(gc, Cell.cells);
                nextGeneration();
            }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Button pause = new Button("Pause");
        Button resume = new Button("Resume");
        VBox buttons = new VBox(10, pause, resume, canvas);

        pause.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            timeline.pause();
        });

        resume.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            timeline.play();
        });

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            grid.drawCell(gc, (int) event.getX() / Grid.CELL_SIZE, (int) event.getY() / Grid.CELL_SIZE, true);
        });

        root.getChildren().add(canvas);
        root.setRight(buttons);
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }

    void nextGeneration() {
        // map для того, чтобы не менять клетки из списка сразу,
        // а только после того как будет посчитано: какие клетки умрут, а какие останутся живыми,
        // иначе неправильно работает логика игры
        Map<Integer, Boolean> map = new HashMap<>();
        int counter = 0;

        for (var cell : Cell.cells) {
            int countAliveNeighbors = cell.getCountAliveNeighbors();

            if (!cell.isAlive() && countAliveNeighbors == 3) {
                map.put(counter, true);
            }
            if (countAliveNeighbors < 2 || countAliveNeighbors > 3) {
                map.put(counter, false);
            }
            counter++;
        }

        for (var entry : map.entrySet()) {
            Cell.cells.get(entry.getKey()).setAlive(entry.getValue());
        }

    }


}
