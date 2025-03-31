package com.example.lifegame;

import java.util.Objects;

public final class AliveCell {

    private final int x;
    private final int y;

    public AliveCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Cell{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }

    public int getX() {return x;}

    public int getY() {return y;}

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (AliveCell) obj;
        return this.x == that.x &&
               this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
