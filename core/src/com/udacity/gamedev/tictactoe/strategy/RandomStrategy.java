package com.udacity.gamedev.tictactoe.strategy;

import com.badlogic.gdx.math.MathUtils;
import com.udacity.gamedev.tictactoe.board.*;
import com.udacity.gamedev.tictactoe.player.Player;

import java.util.List;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public class RandomStrategy implements Strategy {

    public CellPosition determineBestPosition(Board board, Player forPlayer) {
        List<CellPosition> availableCells = board.emptyCellPositions();
        int randomIndex = MathUtils.random(0, availableCells.size() - 1);
        return availableCells.get(randomIndex);
    }
}
