package com.udacity.gamedev.tictactoe.player;

import com.udacity.gamedev.tictactoe.board.Board;
import com.udacity.gamedev.tictactoe.board.CellPosition;
import com.udacity.gamedev.tictactoe.player.Player;

public class HumanPlayer extends Player {

    public static final String TAG = HumanPlayer.class.getName();

    public HumanPlayer(Board board, PlayerType type) {
        super(board, type);
    }

    public CellPosition setCellAtPosition(CellPosition position) {
        getBoard().setCell(position, this.getPlayerType().getValue());
        return position;
    }
}