package com.udacity.gamedev.tictactoe;

import com.badlogic.gdx.Game;
import com.udacity.gamedev.tictactoe.strategy.Strategy;

public class TicTacToeGame extends Game {

    @Override
    public void create() {
        showSettingsScreen();
    }

    public void showSettingsScreen() {
        setScreen(new SettingsScreen(this));
    }

    public void showTicTacToeScreen(Strategy comStrategy) {
        setScreen(new TicTacToeScreen(this, comStrategy));
    }
}
