package com.udacity.gamedev.tictactoe.strategy;

import com.udacity.gamedev.tictactoe.board.*;
import com.udacity.gamedev.tictactoe.player.*;

public class MinimaxStrategy implements Strategy {

    public static final String TAG = MinimaxStrategy.class.getName();

    private class MinimaxResults {
        int bestScore;
        CellPosition bestPosition;

        public MinimaxResults(MinimaxResults results) {
            this.bestScore = results.bestScore;
            this.bestPosition = new CellPosition(results.bestPosition);
        }

        public MinimaxResults(int bestScore, CellPosition bestPosition) {
            this.bestScore = bestScore;
            this.bestPosition = bestPosition;
        }
    }

    public CellPosition determineBestPosition(Board board, Player forPlayer) {
        MinimaxResults results = miniMax(board, 2, Integer.MIN_VALUE, Integer.MAX_VALUE, forPlayer.getPlayerType());
        return results.bestPosition;
    }

    private MinimaxResults miniMax(Board tempBoard, int depth, int alpha, int beta, Player.PlayerType playerType) {

        if (tempBoard.gameOver() || depth == 0) {
            return new MinimaxResults(tempBoard.getScore(), new CellPosition(-1, -1));
        }

        // playerX is trying to maximize, therefore we initialize to "-infinity" and begin looking for higher scores
        // playerO is trying to minimize, therefore we initialize to "+infinity" and begin looking for lower scores
        int bestScore = (playerType == Player.PlayerType.PLAYER_X) ? Integer.MIN_VALUE: Integer.MAX_VALUE;
        CellPosition bestPosition = new CellPosition(-1, -1);

        for(CellPosition position: tempBoard.emptyCellPositions()) {

            Board nextBoard = new Board(tempBoard.boardAfterMove(position, playerType.getValue()));

            if (playerType == Player.PlayerType.PLAYER_X) {
                MinimaxResults result = new MinimaxResults(miniMax(nextBoard, depth-1, alpha, beta, playerType.oppositePlayer()));
                if (result.bestScore > bestScore) {
                    bestScore = result.bestScore;
                    bestPosition = new CellPosition(position);
                }
                alpha = Math.max(alpha, result.bestScore);
                if (beta <= alpha) {
                    break;
                }
            } else {
                MinimaxResults result = new MinimaxResults(miniMax(nextBoard, depth-1, alpha, beta, playerType.oppositePlayer()));
                if (result.bestScore < bestScore) {
                    bestScore = result.bestScore;
                    bestPosition = new CellPosition(position);
                }
                beta = Math.min(beta, result.bestScore);
                if (beta <= alpha) {
                    break;
                }
            }
        }

        return new MinimaxResults(bestScore, bestPosition);
    }
}
