package com.udacity.gamedev.tictactoe.strategy;

import com.udacity.gamedev.tictactoe.board.*;
import com.udacity.gamedev.tictactoe.player.Player;

public interface Strategy {
    CellPosition determineBestPosition(Board board, Player forPlayer);
}
