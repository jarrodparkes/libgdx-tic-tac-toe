package com.udacity.gamedev.tictactoe.player;

import com.udacity.gamedev.tictactoe.board.Board;
import com.udacity.gamedev.tictactoe.board.CellPosition;
import com.udacity.gamedev.tictactoe.player.Player;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(Board board, PlayerType type) {
        super(board, type);
    }

    public CellPosition setCellAtPosition(CellPosition position) {
        getBoard().setCell(position, this.getPlayerType().getValue());
        return position;
    }
}