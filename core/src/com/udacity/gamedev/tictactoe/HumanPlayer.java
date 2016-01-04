package com.udacity.gamedev.tictactoe;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(Board board, PlayerType type) {
        super(board, type);
    }

    public CellPosition setCellAtPosition(CellPosition position) {
        board.setCell(position, this.type.value());
        return position;
    }
}