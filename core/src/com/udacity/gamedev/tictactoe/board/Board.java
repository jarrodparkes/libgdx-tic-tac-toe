package com.udacity.gamedev.tictactoe.board;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.udacity.gamedev.tictactoe.Constants;
import com.udacity.gamedev.tictactoe.board.Cell.*;

public class Board {

    public static final String TAG = Board.class.getName();

    Cell[][] cells;

    public int getScore() {
        int totalScore = 0;
        for (CellPosition[] pattern: Constants.WINNING_PATTERNS) {
            // count the number of Empty, Cross, and Nought in a row
            HashMap<CellValue, Integer> counts = new HashMap<CellValue, Integer>();
            int emptyCells = 0;
            int crossCells = 0;
            int noughtCells = 0;
            for (CellPosition position: pattern) {
                switch(cells[position.r][position.c].value) {
                    case EMPTY:
                        emptyCells++;
                        break;
                    case CROSS:
                        crossCells++;
                        break;
                    case NOUGHT:
                        noughtCells++;
                        break;
                }
            }
            counts.put(CellValue.EMPTY, emptyCells);
            counts.put(CellValue.CROSS, crossCells);
            counts.put(CellValue.NOUGHT, noughtCells);
            // calculate score based on counts
            totalScore += calculateScoreForRow(counts);
        }
        return totalScore;
    }

    public List<CellPosition> emptyCellPositions() {
        List<CellPosition> positions = new ArrayList<CellPosition>();
        for (int i = 0; i < cells.length; i++) {
            for (int x = 0; x < cells[i].length; x++) {
                if (cells[i][x].value == CellValue.EMPTY) {
                    positions.add(cells[i][x].position);
                }
            }
        }
        return positions;
    }

    public Boolean gameOver() {
        return getResults().hasWinner() == true || emptyCellPositions().size() == 0;
    }

    public Board() {
        this.cells = new Cell[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                cells[r][c] = new Cell(new CellPosition(r, c));
            }
        }
    }

    public Board(Board board) {
        this.cells = new Cell[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                cells[r][c] = new Cell(board.cells[r][c]);
            }
        }
    }

    public Board boardAfterMove(CellPosition position, CellValue newValue) {
        Board nextBoard = new Board(this);
        nextBoard.cells[position.r][position.c].position = position;
        nextBoard.cells[position.r][position.c].value = newValue;
        return nextBoard;
    }

    private int calculateScoreForRow(HashMap<CellValue, Integer> counts) {
        int score = 0;
        int emptyCount = counts.get(CellValue.EMPTY);
        int crossCount = counts.get(CellValue.CROSS);
        int noughtCount = counts.get(CellValue.NOUGHT);
        if (crossCount == 3 && emptyCount == 0) {
            score += Constants.THREE_CROSS_BONUS;
        }
        if (crossCount == 2 && emptyCount == 1) {
            score += Constants.TWO_CROSS_BONUS;
        }
        if (crossCount == 1 && emptyCount == 2) {
            score += Constants.ONE_CROSS_BONUS;
        }
        if (noughtCount == 3 && emptyCount == 0) {
            score += Constants.THREE_NOUGHT_BONUS;
        }
        if (noughtCount == 2 && emptyCount == 1) {
            score += Constants.TWO_NOUGHT_BONUS;
        }
        if (noughtCount == 1 && emptyCount == 2) {
            score += Constants.ONE_NOUGHT_BONUS;
        }
        return score;
    }

    public void setCell(CellPosition position, CellValue value) {
        if (cells[position.r][position.c].value == CellValue.EMPTY) {
            cells[position.r][position.c].value = value;
        } else {
            Gdx.app.log(TAG, "cell already taken");
        }
    }

    public void clearBoard() {
        this.cells = new Cell[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                cells[r][c] = new Cell(new CellPosition(r, c));
            }
        }
    }

    public Cell cellAtPosition(CellPosition position) {
        return cells[position.r][position.c];
    }

    public Results getResults() {
        Results tempResults = new Results();
        for (CellPosition[] pattern: Constants.WINNING_PATTERNS) {
            Cell cell = cells[pattern[0].r][pattern[0].c];
            if (cell.value == CellValue.EMPTY) {
                continue;
            }
            if (cells[pattern[0].r][pattern[0].c].value == cells[pattern[1].r][pattern[1].c].value &&
                    cells[pattern[1].r][pattern[1].c].value == cells[pattern[2].r][pattern[2].c].value) {
                tempResults.setWinner(cells[pattern[0].r][pattern[0].c].value);
                tempResults.setHasWinner(true);
                break;
            }
        }
        return tempResults;
    }
}
