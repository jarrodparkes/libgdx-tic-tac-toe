package com.udacity.gamedev.tictactoe.board;

public class Cell {

    public static final String TAG = Cell.class.getName();

    public enum CellValue {
        EMPTY, NOUGHT, CROSS
    }

    CellPosition position;
    CellValue value;

    public Cell(Cell cell) {
        this.position = new CellPosition(cell.position);
        this.value = cell.value;
    }

    public Cell(CellPosition position) {
        this.position = position;
        this.value = CellValue.EMPTY;
    }

    public Cell(CellPosition position, CellValue value) {
        this.position = position;
        this.value = value;
    }

    public CellValue getValue() {return value;}

    public CellPosition getPosition() { return position; }
}



