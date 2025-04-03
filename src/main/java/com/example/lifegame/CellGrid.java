package com.example.lifegame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import javafx.util.Pair;

public class CellGrid {
    private final Map<Pair<Integer, Integer>, AliveCell> aliveCells = new HashMap<>();

    public void addCell(int x, int y) {
        aliveCells.put(new Pair<>(x, y), new AliveCell(x, y));
    }

    public AliveCell getCell(int x, int y) {
        return aliveCells.getOrDefault(new Pair<>(x, y), null);
    }

    public void deleteCell(int x, int y) {
        AliveCell cell = getCell(x, y);

        if (cell != null) {
            aliveCells.remove(new Pair<>(x, y));
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


    public List<AliveCell> getAliveCells() {
        return aliveCells.values().stream().toList();
    }
}
