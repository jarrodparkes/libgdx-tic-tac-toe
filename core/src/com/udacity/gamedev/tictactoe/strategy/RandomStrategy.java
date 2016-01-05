package com.udacity.gamedev.tictactoe.strategy;

import com.badlogic.gdx.math.MathUtils;
import com.udacity.gamedev.tictactoe.board.*;
import com.udacity.gamedev.tictactoe.player.Player;

import java.util.List;

public class RandomStrategy implements Strategy {

    public static final String TAG = RandomStrategy.class.getName();

    public CellPosition determineBestPosition(Board board, Player forPlayer) {
        List<CellPosition> availableCells = board.emptyCellPositions();
        int randomIndex = MathUtils.random(0, availableCells.size() - 1);
        return availableCells.get(randomIndex);
    }
}
