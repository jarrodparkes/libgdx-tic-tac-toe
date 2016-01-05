package com.udacity.gamedev.tictactoe.board;

import com.udacity.gamedev.tictactoe.board.Cell.*;

public class Results {

    public static final String TAG = Results.class.getName();

    Boolean hasWinner;
    CellValue winnerType;

    public Results() {
        this.hasWinner = false;
        this.winnerType = CellValue.EMPTY;
    }

    public CellValue getWinner() {
        return winnerType;
    }

    public Boolean hasWinner() {
        return hasWinner;
    }

    public void setWinner(CellValue winnerType) {
        this.winnerType = winnerType;
    }

    public void setHasWinner(boolean hasWinner) {
        this.hasWinner = hasWinner;
    }
}
