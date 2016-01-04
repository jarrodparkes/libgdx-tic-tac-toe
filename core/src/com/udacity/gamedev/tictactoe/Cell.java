package com.udacity.gamedev.tictactoe;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public class Cell {

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
}



