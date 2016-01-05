package com.udacity.gamedev.tictactoe.strategy;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.MathUtils;
import com.udacity.gamedev.tictactoe.board.*;
import com.udacity.gamedev.tictactoe.board.Cell.CellValue;
import com.udacity.gamedev.tictactoe.player.Player;

public class RuleBasedStrategy implements Strategy {

    public static final String TAG = RuleBasedStrategy.class.getName();

    public CellPosition determineBestPosition(Board board, Player forPlayer) {
        // middle
        Cell middleCell = board.cellAtPosition(new CellPosition(1, 1));
        // corners
        Array<Cell> emptyCornerCells = new Array<Cell>();
        if (board.cellAtPosition(new CellPosition(0, 0)).getValue() == CellValue.EMPTY) {
            emptyCornerCells.add(board.cellAtPosition(new CellPosition(0, 0)));
        }
        if (board.cellAtPosition(new CellPosition(0, 2)).getValue() == CellValue.EMPTY) {
            emptyCornerCells.add(board.cellAtPosition(new CellPosition(0, 2)));
        }
        if (board.cellAtPosition(new CellPosition(2, 0)).getValue() == CellValue.EMPTY) {
            emptyCornerCells.add(board.cellAtPosition(new CellPosition(2, 0)));
        }
        if (board.cellAtPosition(new CellPosition(2, 2)).getValue() == CellValue.EMPTY) {
            emptyCornerCells.add(board.cellAtPosition(new CellPosition(2, 2)));
        }
        // edges
        Array<Cell> emptyEdgeCells = new Array<Cell>();
        if (board.cellAtPosition(new CellPosition(0, 1)).getValue() == CellValue.EMPTY) {
            emptyEdgeCells.add(board.cellAtPosition(new CellPosition(0, 1)));
        }
        if (board.cellAtPosition(new CellPosition(1, 0)).getValue() == CellValue.EMPTY) {
            emptyEdgeCells.add(board.cellAtPosition(new CellPosition(1, 0)));
        }
        if (board.cellAtPosition(new CellPosition(1, 2)).getValue() == CellValue.EMPTY) {
            emptyEdgeCells.add(board.cellAtPosition(new CellPosition(1, 2)));
        }
        if (board.cellAtPosition(new CellPosition(2, 1)).getValue() == CellValue.EMPTY) {
            emptyEdgeCells.add(board.cellAtPosition(new CellPosition(2, 1)));
        }
        // try the middle, then the corners, then the edges
        if (middleCell.getValue() == CellValue.EMPTY) {
            return middleCell.getPosition();
        } else if (emptyCornerCells.size > 0) {
            int randomIndex = MathUtils.random(0, emptyCornerCells.size - 1);
            return emptyCornerCells.get(randomIndex).getPosition();
        } else {
            int randomIndex = MathUtils.random(0, emptyEdgeCells.size - 1);
            return emptyEdgeCells.get(randomIndex).getPosition();
        }
    }
}
