package com.udacity.gamedev.tictactoe.board;

public class CellPosition {

    public static final String TAG = CellPosition.class.getName();

    int r;
    int c;

    public CellPosition(CellPosition position) {
        this.r = position.r;
        this.c = position.c;
    }

    public CellPosition(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "r: " + r + ", c: " + c;
    }

    public int getRow() {
        return r;
    }

    public int getColumn() {
        return c;
    }

    public void setRow(int r) {
        this.r = r;
    }

    public void setColumn(int c) {
        this.c = c;
    }
}
