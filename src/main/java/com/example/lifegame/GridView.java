package com.example.lifegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridView {
    private final CellGrid grid;

    public static final int CELL_SIZE = 5;
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 1000;
    public static final int WIDTH_IN_CELLS = CANVAS_WIDTH / CELL_SIZE;
    public static final int HEIGHT_IN_CELLS = CANVAS_HEIGHT / CELL_SIZE;

    public GridView(CellGrid grid) {this.grid = grid;}


    public void updateGrid(GraphicsContext gc) {
        for (AliveCell cell : grid.getAliveCells()) {
            drawCell(gc, cell.getX(), cell.getY(), true);
        }

        while (grid.getDeadCells().peek() != null) {
            AliveCell cell = grid.getDeadCells().poll();
            drawCell(gc, cell.getX(), cell.getY(), false);
        }
    }

    public void drawCell(GraphicsContext gc, int cellX, int cellY, boolean isAlive) {
        int x = cellX * CELL_SIZE;
        int y = cellY * CELL_SIZE;

        if (isAlive) {
            gc.setFill(Color.BLACK);
        } else {
            gc.setFill(Color.WHITE);
        }
        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
    }
}
