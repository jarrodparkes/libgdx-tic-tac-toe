package com.udacity.gamedev.tictactoe;

import com.udacity.gamedev.tictactoe.Cell.CellValue;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public class Results {
    Boolean winner;
    CellValue winnerType;

    public Results() {
        this.winner = false;
        this.winnerType = CellValue.EMPTY;
    }
}
