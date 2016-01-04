package com.udacity.gamedev.tictactoe;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.MathUtils;
import com.udacity.gamedev.tictactoe.Cell.CellValue;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public class RuleBasedStrategy implements StrategyType {

    public CellPosition determineBestPosition(Board board, Player forPlayer) {
        // middle
        Cell middleCell = board.cellAtPosition(new CellPosition(1, 1));
        // corners
        Array<Cell> emptyCornerCells = new Array<Cell>();
        if (board.cellAtPosition(new CellPosition(0, 0)).value == CellValue.EMPTY) {
            emptyCornerCells.add(board.cellAtPosition(new CellPosition(0, 0)));
        }
        if (board.cellAtPosition(new CellPosition(0, 2)).value == CellValue.EMPTY) {
            emptyCornerCells.add(board.cellAtPosition(new CellPosition(0, 2)));
        }
        if (board.cellAtPosition(new CellPosition(2, 0)).value == CellValue.EMPTY) {
            emptyCornerCells.add(board.cellAtPosition(new CellPosition(2, 0)));
        }
        if (board.cellAtPosition(new CellPosition(2, 2)).value == CellValue.EMPTY) {
            emptyCornerCells.add(board.cellAtPosition(new CellPosition(2, 2)));
        }
        // edges
        Array<Cell> emptyEdgeCells = new Array<Cell>();
        if (board.cellAtPosition(new CellPosition(0, 1)).value == CellValue.EMPTY) {
            emptyEdgeCells.add(board.cellAtPosition(new CellPosition(0, 1)));
        }
        if (board.cellAtPosition(new CellPosition(1, 0)).value == CellValue.EMPTY) {
            emptyEdgeCells.add(board.cellAtPosition(new CellPosition(1, 0)));
        }
        if (board.cellAtPosition(new CellPosition(1, 2)).value == CellValue.EMPTY) {
            emptyEdgeCells.add(board.cellAtPosition(new CellPosition(1, 2)));
        }
        if (board.cellAtPosition(new CellPosition(2, 1)).value == CellValue.EMPTY) {
            emptyEdgeCells.add(board.cellAtPosition(new CellPosition(2, 1)));
        }
        // try the middle, then the corners, then the edges
        if (middleCell.value == CellValue.EMPTY) {
            return middleCell.position;
        } else if (emptyCornerCells.size > 0) {
            int randomIndex = MathUtils.random(0, emptyCornerCells.size - 1);
            return emptyCornerCells.get(randomIndex).position;
        } else {
            int randomIndex = MathUtils.random(0, emptyEdgeCells.size - 1);
            return emptyEdgeCells.get(randomIndex).position;
        }
    }
}
