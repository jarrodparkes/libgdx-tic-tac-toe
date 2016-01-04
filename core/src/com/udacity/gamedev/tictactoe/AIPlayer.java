package com.udacity.gamedev.tictactoe;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public class AIPlayer extends Player {

    StrategyType strategy;

    public AIPlayer(Board board, PlayerType type, StrategyType strategy) {
        super(board, type);
        this.strategy = strategy;
    }

    public CellPosition makeAIMove() {
        CellPosition bestPosition = strategy.determineBestPosition(board, this);
        board.setCell(bestPosition, type.value());
        return bestPosition;
    }
}