package com.udacity.gamedev.tictactoe;

import com.udacity.gamedev.tictactoe.Player.PlayerType;

/**
 * Created by jarrodparkes on 1/3/16.
 */
interface GameDelegate {
    void didMoveAtPosition(CellPosition position, PlayerType type);
    void didEnd(Board board, String results);
}