package com.example.lifegame;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grid {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 1000;
    public static final int CELL_SIZE = 25;

    public void drawGrid(GraphicsContext gc, List<Cell> cells) {
        for (var cell : cells) {
            drawCell(gc, cell.getX(), cell.getY(), cell.isAlive());
        }
    }

    public void drawCell(GraphicsContext gc, int cellX, int cellY, boolean alive) {
        double x = cellX * CELL_SIZE;
        double y = cellY * CELL_SIZE;

        if (alive) {
        gc.setFill(Color.BLACK);
        } else {
            gc.setFill(Color.WHITE);
        }

        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        Cell.getCell(cellX, cellY).setAlive(alive);
    }

    public static int getWidthInCells() {
        return CANVAS_WIDTH / CELL_SIZE;
    }

    public static int getHeightInCells() {
        return CANVAS_HEIGHT / CELL_SIZE;
    }
}
