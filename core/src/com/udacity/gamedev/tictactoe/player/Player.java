package com.udacity.gamedev.tictactoe.player;

import com.udacity.gamedev.tictactoe.board.*;
import com.udacity.gamedev.tictactoe.board.Cell.CellValue;

public class Player {

    public static final String TAG = Player.class.getName();

    public enum PlayerType {
        PLAYER_X, PLAYER_O;

        private PlayerType opposite;
        private CellValue cellValue;

        static {
            PLAYER_X.opposite = PLAYER_O;
            PLAYER_X.cellValue = CellValue.CROSS;
            PLAYER_O.opposite = PLAYER_X;
            PLAYER_O.cellValue = CellValue.NOUGHT;
        }

        public PlayerType oppositePlayer() {
            return opposite;
        }

        public CellValue getValue() {
            return cellValue;
        }
    }

    Board board;
    PlayerType playerType;

    public Player(Board board, PlayerType type) {
        this.board = board;
        this.playerType = type;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
    public Board getBoard() { return board;}
}
