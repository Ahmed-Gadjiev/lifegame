package com.example.lifegame;

import static com.example.lifegame.Grid.CANVAS_HEIGHT;
import static com.example.lifegame.Grid.CANVAS_WIDTH;
import static com.example.lifegame.Grid.CELL_SIZE;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private boolean isAlive = false;
    private final int x;
    private final int y;
    public static int countAlive;

    static List<Cell> cells = new ArrayList<>();

    static {
        for (int i = 0; i < CANVAS_HEIGHT / CELL_SIZE; i++) {
            for (int j = 0; j < CANVAS_WIDTH / CELL_SIZE; j++) {
                cells.add(new Cell(i, j));
            }
        }
    }

    private Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Cell getCell(int x, int y) {
        return cells.stream()
            .filter(cell -> cell.x == x && cell.y == y)
            .findFirst()
            .orElse(null);
    }

    public List<Cell> getNeighbors() {
        List<Cell> neighbors = new ArrayList<>();

        int[][] offsets = {
            {1, 0}, {0, 1}, {1, 1}, {-1, 0}, {0, -1}, {-1, -1}, {1, -1}, {-1, 1}
        };

        for (int[] offset : offsets) {
            int newX = this.getX() + offset[0];
            int newY = this.getY() + offset[1];
            if (newX >= 0 && newX <= Grid.getWidthInCells() && newY >= 0 && newY <= Grid.getHeightInCells()) {
                neighbors.add(Cell.getCell(newX, newY));
            }
        }

        return neighbors;
    }

    public int getCountAliveNeighbors() {
        return (int) this.getNeighbors().stream().filter(n -> {
            try {
                return n.isAlive();
            } catch (Exception ignored) {}
            return false;
        }).count();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        if (alive) {
            isAlive = true;
            countAlive++;
        } else {
            isAlive = false;
            countAlive--;
        }
    }

    @Override
    public String toString() {
        return "Cell{" +
               "isAlive=" + isAlive +
               ", x=" + x +
               ", y=" + y +
               '}';
    }
}
