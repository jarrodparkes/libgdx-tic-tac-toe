package com.udacity.gamedev.tictactoe;

/**
 * Created by jarrodparkes on 1/3/16.
 */
interface StrategyType {
    public CellPosition determineBestPosition(Board board, Player forPlayer);
}
