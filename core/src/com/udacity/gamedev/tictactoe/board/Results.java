package com.udacity.gamedev.tictactoe.board;

import com.udacity.gamedev.tictactoe.board.Cell.*;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public class Results {


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

    public void hasWinner(boolean hasWinner) {
        this.hasWinner = hasWinner;
    }
}
