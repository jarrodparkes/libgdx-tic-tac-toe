package com.udacity.gamedev.tictactoe.strategy;

import com.udacity.gamedev.tictactoe.board.*;
import com.udacity.gamedev.tictactoe.player.Player;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public interface Strategy {
    CellPosition determineBestPosition(Board board, Player forPlayer);
}
