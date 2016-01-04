package com.udacity.gamedev.tictactoe;

import com.udacity.gamedev.tictactoe.Cell.CellValue;

/**
 * Created by jarrodparkes on 12/30/15.
 */
public class Player {

    public enum PlayerType {
        Player_X, Player_O;

        private PlayerType opposite;
        private CellValue cellValue;

        static {
            Player_X.opposite = Player_O;
            Player_X.cellValue = CellValue.CROSS;
            Player_O.opposite = Player_X;
            Player_O.cellValue = CellValue.NOUGHT;
        }

        public PlayerType oppositePlayer() {
            return opposite;
        }

        public CellValue value() {
            return cellValue;
        }
    }

    Board board;
    PlayerType type;

    public Player(Board board, PlayerType type) {
        this.board = board;
        this.type = type;
    }
}