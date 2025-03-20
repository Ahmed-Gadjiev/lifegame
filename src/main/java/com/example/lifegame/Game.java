package com.example.lifegame;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game {

    private final Grid grid = new Grid();

    void start(Stage primaryStage) {
        primaryStage.setTitle("LifeGame");
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(Grid.CANVAS_WIDTH, Grid.CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(4), event -> {
                grid.drawGrid(gc, Cell.cells);
                nextGeneration();
            }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            grid.drawCell(gc, (int) event.getX() / Grid.CELL_SIZE, (int) event.getY() / Grid.CELL_SIZE, true);
            System.out.println(event.getX());
            System.out.println(event.getY());
            System.out.println((int) event.getX() / Grid.CELL_SIZE);
            System.out.println((int) event.getY() / Grid.CELL_SIZE);
            System.out.println(Cell.getCell((int) event.getX() / Grid.CELL_SIZE, (int) event.getY() / Grid.CELL_SIZE).getCountAliveNeighbors());
            System.out.println();
        });

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    void nextGeneration() {
        List<Cell> newCells;

        newCells = Cell.cells.stream().map(
            elem -> {
                int countAliveNeighbors = elem.getCountAliveNeighbors();

                if (!elem.isAlive() && countAliveNeighbors == 3) {
                    elem.setAlive(true);
                }
                if (countAliveNeighbors < 2 || countAliveNeighbors > 3) {
                    elem.setAlive(false);
                }
                return elem;
            }
        ).toList();

        Cell.cells = newCells;
    }


}
