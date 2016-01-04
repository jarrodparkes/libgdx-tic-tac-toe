package com.udacity.gamedev.tictactoe;

import com.badlogic.gdx.Game;

public class TicTacToeGame extends Game {

    @Override
    public void create() {
        setScreen(new AIScreen());
    }
}