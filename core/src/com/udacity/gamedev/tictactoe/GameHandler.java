package com.udacity.gamedev.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.udacity.gamedev.tictactoe.board.*;
import com.udacity.gamedev.tictactoe.player.*;
import com.udacity.gamedev.tictactoe.player.Player.PlayerType;
import com.udacity.gamedev.tictactoe.strategy.*;
import com.udacity.gamedev.tictactoe.Constants.GridPosition.*;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public class GameHandler {

    public static final String TAG = GameHandler.class.getName();

    Board board;
    Player player1;
    Player player2;
    Player nextPlayer;

    Array<Constants.GridPosition> crosses;
    Array<Constants.GridPosition> circles;

    public GameHandler(Strategy strategy1) {
        board = new Board();
        player1 = new HumanPlayer(board, PlayerType.PLAYER_X);
        player2 = new AIPlayer(board, PlayerType.PLAYER_O, strategy1);
        nextPlayer = player2;

        crosses = new Array<Constants.GridPosition>();
        circles = new Array<Constants.GridPosition>();
    }

    public void moveAIPlayer() {
        AIPlayer currentAIPlayer = (AIPlayer) nextPlayer;
        CellPosition changedPosition = currentAIPlayer.makeAIMove();
        didMoveAtPosition(changedPosition, currentAIPlayer.getPlayerType());
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
            Gdx.app.log(TAG, "game over");
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
        board.clearBoard();
        nextPlayer = player2;
    }

    public void render(float delta, ShapeRenderer renderer) {
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
}
