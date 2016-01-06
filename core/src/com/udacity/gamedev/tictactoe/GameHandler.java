package com.udacity.gamedev.tictactoe;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.tictactoe.board.*;
import com.udacity.gamedev.tictactoe.player.*;
import com.udacity.gamedev.tictactoe.player.Player.PlayerType;
import com.udacity.gamedev.tictactoe.strategy.*;

public class GameHandler {

    public static final String TAG = GameHandler.class.getName();

    TicTacToeGame game;
    Board board;
    HumanPlayer player1;
    AIPlayer player2;

    Array<Constants.GridPosition> crosses;
    Array<Constants.GridPosition> circles;

    public GameHandler(TicTacToeGame game, Strategy comStrategy) {
        this.game = game;
        board = new Board();
        player1 = new HumanPlayer(board, PlayerType.PLAYER_X);
        player2 = new AIPlayer(board, PlayerType.PLAYER_O, comStrategy);
        crosses = new Array<Constants.GridPosition>();
        circles = new Array<Constants.GridPosition>();
    }

    public void moveAIPlayer() {
        CellPosition changedPosition = player2.makeAIMove();
        didMoveAtPosition(changedPosition, player2.getPlayerType());
        checkEndGame();
    }

    public void moveHumanPlayer(PlayerType playerType, CellPosition position) {
        if (playerType == PlayerType.PLAYER_O) {
            return;
        } else {
            HumanPlayer human = (HumanPlayer) player1;
            CellPosition changedPosition = human.setCellAtPosition(position);
            didMoveAtPosition(changedPosition, human.getPlayerType());
            makeNextMove();
        }
    }

    public void makeNextMove() {
        if (board.gameOver() == false) {
            moveAIPlayer();
        } else {
            endGame();
        }
    }

    public void checkEndGame() {
        if (board.gameOver()) {
            endGame();
        }
    }

    public void endGame() {
        String resultString = "";
        Results gameResults = board.getResults();
        if (gameResults.getWinner() == Cell.CellValue.CROSS) {
            resultString += "You win!";
        } else {
            resultString += "You lose!";
        }
        didEnd(board, resultString);
    }

    public void reset() {
        crosses.clear();
        circles.clear();
        board.clearBoard();
    }

    public void render(float delta, ShapeRenderer renderer) {
        // draw reset "button"
        renderer.setColor(Constants.PLAYFIELD_COLOR);
        renderer.circle(Constants.RESET_CENTER.x, Constants.RESET_CENTER.y, Constants.CIRCLE_RADIUS, 20);
        // draw playfield
        renderPlayField(delta, renderer);
        // draw an X (needs 150 by 150 space?)
        for (Constants.GridPosition gp: crosses) {
            renderer.setColor(Constants.CROSS_COLOR);
            renderer.rectLine(
                    gp.position.x - Constants.CROSS_OFFSET_FROM_CENTER,
                    gp.position.y + Constants.CROSS_OFFSET_FROM_CENTER,
                    gp.position.x + Constants.CROSS_OFFSET_FROM_CENTER,
                    gp.position.y - Constants.CROSS_OFFSET_FROM_CENTER,
                    Constants.CROSS_THICKNESS);
            renderer.rectLine(
                    gp.position.x - Constants.CROSS_OFFSET_FROM_CENTER,
                    gp.position.y - Constants.CROSS_OFFSET_FROM_CENTER,
                    gp.position.x + Constants.CROSS_OFFSET_FROM_CENTER,
                    gp.position.y + Constants.CROSS_OFFSET_FROM_CENTER,
                    Constants.CROSS_THICKNESS);
        }
        // draw an O
        for (Constants.GridPosition gp: circles) {
            renderer.setColor(Constants.CIRCLE_COLOR);
            renderer.circle(gp.position.x, gp.position.y, Constants.CIRCLE_RADIUS, 20);
            renderer.setColor(Constants.BACKGROUND_COLOR);
            renderer.circle(gp.position.x, gp.position.y, Constants.CIRCLE_RADIUS - Constants.CIRCLE_THICKNESS, 20);
        }
    }

    public void renderPlayField(float delta, ShapeRenderer renderer) {
        renderer.setColor(Constants.PLAYFIELD_COLOR);
        renderer.rectLine(
                Constants.PLAYFIELD_CENTER.x - Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.y + Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.x + Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.y + Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_LINE_THICKNESS);
        renderer.rectLine(
                Constants.PLAYFIELD_CENTER.x - Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.y - Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.x + Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.y - Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_LINE_THICKNESS);
        renderer.rectLine(
                Constants.PLAYFIELD_CENTER.x - Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.y - Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.x - Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.y + Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_LINE_THICKNESS);
        renderer.rectLine(
                Constants.PLAYFIELD_CENTER.x + Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.y - Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.x + Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.y + Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_LINE_THICKNESS);
    }

    public void didMoveAtPosition(CellPosition position, PlayerType type) {
        if (type == PlayerType.PLAYER_X) {
            crosses.add(cellPositionToGridPosition(position));
        } else {
            circles.add(cellPositionToGridPosition(position));
        }
    }

    public void didEnd(Board board, String results) {

    }

    public Constants.GridPosition cellPositionToGridPosition(CellPosition position) {
        Constants.GridPosition finalPosition = Constants.GridPosition.UPPER_LEFT;
        if (position.getRow() == 0) {
            if (position.getColumn() == 0) {
                finalPosition = Constants.GridPosition.UPPER_LEFT;
            }
            if (position.getColumn() == 1) {
                finalPosition = Constants.GridPosition.UPPER_MIDDLE;
            }
            if (position.getColumn() == 2) {
                finalPosition = Constants.GridPosition.UPPER_RIGHT;
            }
        } else if (position.getRow() == 1) {
            if (position.getColumn() == 0) {
                finalPosition = Constants.GridPosition.MIDDLE_LEFT;
            }
            if (position.getColumn() == 1) {
                finalPosition = Constants.GridPosition.MIDDLE_MIDDLE;
            }
            if (position.getColumn() == 2) {
                finalPosition = Constants.GridPosition.MIDDLE_RIGHT;
            }
        } else {
            if (position.getColumn() == 0) {
                finalPosition = Constants.GridPosition.LOWER_LEFT;
            }
            if (position.getColumn() == 1) {
                finalPosition = Constants.GridPosition.LOWER_MIDDLE;
            }
            if (position.getColumn() == 2) {
                finalPosition = Constants.GridPosition.LOWER_RIGHT;
            }
        }
        return finalPosition;
    }

    public CellPosition gridPositionToCellPosition(Constants.GridPosition position) {
        CellPosition finalPosition = new CellPosition(0, 0);
        switch (position) {
            case LOWER_LEFT:
                finalPosition.setRow(2);
                finalPosition.setColumn(0);
                break;
            case LOWER_MIDDLE:
                finalPosition.setRow(2);
                finalPosition.setColumn(1);
                break;
            case LOWER_RIGHT:
                finalPosition.setRow(2);
                finalPosition.setColumn(2);
                break;
            case MIDDLE_LEFT:
                finalPosition.setRow(1);
                finalPosition.setColumn(0);
                break;
            case MIDDLE_MIDDLE:
                finalPosition.setRow(1);
                finalPosition.setColumn(1);
                break;
            case MIDDLE_RIGHT:
                finalPosition.setRow(1);
                finalPosition.setColumn(2);
                break;
            case UPPER_LEFT:
                finalPosition.setRow(0);
                finalPosition.setColumn(0);
                break;
            case UPPER_MIDDLE:
                finalPosition.setRow(0);
                finalPosition.setColumn(1);
                break;
            case UPPER_RIGHT:
                finalPosition.setRow(0);
                finalPosition.setColumn(2);
                break;
        }

        return finalPosition;
    }

    public void handleTouch(Vector2 worldTouch) {
        for (Constants.GridPosition position : Constants.GridPosition.values()) {
            boolean inX = false;
            boolean inY = false;
            if (worldTouch.x > position.position.x - Constants.PLAYFIELD_GRID_SIZE_HALF &&
                    worldTouch.x < position.position.x + Constants.PLAYFIELD_GRID_SIZE_HALF) {
                inX = true;
            }
            if (worldTouch.y > position.position.y - Constants.PLAYFIELD_GRID_SIZE_HALF &&
                    worldTouch.y < position.position.y + Constants.PLAYFIELD_GRID_SIZE_HALF) {
                inY = true;
            }
            if (inX && inY) {
                moveHumanPlayer(Player.PlayerType.PLAYER_X, gridPositionToCellPosition(position));
                break;
            }
        }

        if (worldTouch.dst(Constants.RESET_CENTER) < Constants.CIRCLE_RADIUS) {
            game.showSettingsScreen();
        }
    }
}
