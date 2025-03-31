package com.example.lifegame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CellGrid {
    private final List<AliveCell> aliveCells = new ArrayList<>();
    private final Queue<AliveCell> deadCells = new ArrayDeque<>();
    private final List<AliveCell> lastChanges = new ArrayList<>();

    public void addCell(int x, int y) {
        if (getCell(x, y) == null) {
            aliveCells.add(new AliveCell(x, y));
        }
    }

    public AliveCell getCell(int x, int y) {
        for (var cell : aliveCells) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        return null;
    }

    public void deleteCell(int x, int y) {
        AliveCell cell = getCell(x, y);

        if (cell != null) {
            aliveCells.remove(cell);
            deadCells.offer(cell);
        }
    }

    public List<AliveCell> getNeighbors(int x, int y) {
        List<AliveCell> neighbors = new ArrayList<>();

        int[][] offsets = {
            {1, 0}, {0, 1}, {1, 1}, {-1, 0}, {0, -1}, {-1, -1}, {1, -1}, {-1, 1}
        };

        for (int[] offset : offsets) {
            int newX = x + offset[0];
            int newY = y + offset[1];
            if (newX >= 0 && newX <= GridView.WIDTH_IN_CELLS && newY >= 0 && newY <= GridView.HEIGHT_IN_CELLS) {
                if (getCell(newX, newY) != null) {
                    neighbors.add(getCell(newX, newY));
                }
            }
        }

        return neighbors;
    }

    public void addChange(AliveCell cell) {
        lastChanges.add(cell);
    }

    public List<AliveCell> getAliveCells() {
        return aliveCells;
    }

    public Queue<AliveCell> getDeadCells() {
        return deadCells;
    }

    public List<AliveCell> getLastChanges() {
        return lastChanges;
    }
}
