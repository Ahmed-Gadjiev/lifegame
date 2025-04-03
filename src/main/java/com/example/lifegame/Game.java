package com.example.lifegame;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
import javafx.util.Pair;

public class Game {

    private final CellGrid grid = new CellGrid();
    private final GridView gridView = new GridView(grid);
    private final Canvas canvas = new Canvas(GridView.CANVAS_WIDTH, GridView.CANVAS_HEIGHT);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private final Stage primaryStage;

    static int fps = 20;

    public Game(Stage primaryStage) {this.primaryStage = primaryStage;}

    public void start() {
        primaryStage.setTitle("LifeGame");
        BorderPane root = new BorderPane();

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1.0 / fps), event -> {
                handleLogic();
                renderGrid();
            }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Button pause = new Button("Pause");
        Button resume = new Button("Resume");
        Button randomGen = new Button("Random");
        VBox buttons = new VBox(10, pause, resume, randomGen, canvas);

        pause.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            timeline.pause();
        });

        resume.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            timeline.play();
        });

        randomGen.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            randomGeneration();
        });

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            int cellX = (int) event.getX() / GridView.CELL_SIZE;
            int cellY = (int) event.getY() / GridView.CELL_SIZE;

            grid.addCell(cellX, cellY);
        });

        root.getChildren().add(canvas);
        root.setRight(buttons);
        primaryStage.setScene(new Scene(root, 1500, 1000));
        primaryStage.show();
    }

    private void renderGrid() {
        gridView.updateGrid(gc);
    }

    private void handleLogic() {
        nextGeneration();
    }

    void nextGeneration() {
        Map<Pair<Integer, Integer>, Boolean> toChange = new HashMap<>();

        for (int i = 0; i < GridView.WIDTH_IN_CELLS; i++) {
            for (int j = 0; j < GridView.HEIGHT_IN_CELLS; j++) {

                int countAliveNeighbors = grid.getNeighbors(i, j).size();
                boolean isAlive = (grid.getCell(i, j) != null);

                if (!isAlive && countAliveNeighbors == 3) {
                    toChange.put(new Pair<>(i, j), true);
                }
                if (countAliveNeighbors < 2 || countAliveNeighbors > 3) {
                    toChange.put(new Pair<>(i, j), false);
                }
            }
        }

        for (var change : toChange.entrySet()) {
            int x = change.getKey().getKey();
            int y = change.getKey().getValue();

            if (change.getValue()) {
                grid.addCell(x, y);
            } else {
                grid.deleteCell(x, y);

            }
        }
    }

    void randomGeneration() {
        Random rand = new Random();

        for (int i = 0; i < GridView.WIDTH_IN_CELLS; i++) {
            for (int j = 0; j < GridView.HEIGHT_IN_CELLS; j++) {
                boolean randBool = rand.nextBoolean();
                if (randBool) {
                    grid.addCell(i, j);
                }
            }
        }
    }


}
