package com.udacity.gamedev.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.udacity.gamedev.tictactoe.Player.PlayerType;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public class Game implements GameDelegate {

    public static final String TAG = Game.class.getName();

    Board board;
    Player player1;
    Player player2;
    Player nextPlayer;
    GameDelegate delegate;

    Array<Constants.GridPosition> crosses;
    Array<Constants.GridPosition> circles;

    public Game(StrategyType strategy1) {
        board = new Board();
        player1 = new HumanPlayer(board, PlayerType.Player_X);
        player2 = new AIPlayer(board, PlayerType.Player_O, strategy1);
        nextPlayer = player2;

        crosses = new Array<Constants.GridPosition>();
        circles = new Array<Constants.GridPosition>();

        delegate = this;
    }

    public void moveAIPlayer() {
        AIPlayer currentAIPlayer = (AIPlayer) nextPlayer;
        CellPosition changedPosition = currentAIPlayer.makeAIMove();
        delegate.didMoveAtPosition(changedPosition, currentAIPlayer.type);
        checkEndGame();
    }

    public void moveHumanPlayer(PlayerType playerType, CellPosition position) {
        if (playerType == PlayerType.Player_O) {
            return;
        } else {
            HumanPlayer human = (HumanPlayer) player1;
            CellPosition changedPosition = human.setCellAtPosition(position);
            delegate.didMoveAtPosition(changedPosition, human.type);
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
        if (gameResults.winnerType == Cell.CellValue.CROSS) {
            resultString += "You win!";
        } else {
            resultString += "You lose!";
        }
        delegate.didEnd(board, resultString);
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

    @Override
    public void didMoveAtPosition(CellPosition position, PlayerType type) {
        if (type == PlayerType.Player_X) {
            crosses.add(cellPositionToGridPosition(position));
        } else {
            circles.add(cellPositionToGridPosition(position));
        }
    }

    @Override
    public void didEnd(Board board, String results) {

    }

    public Constants.GridPosition cellPositionToGridPosition(CellPosition position) {
        Constants.GridPosition finalPosition = Constants.GridPosition.UPPER_LEFT;
        if (position.r == 0) {
            if (position.c == 0) {
                finalPosition = Constants.GridPosition.UPPER_LEFT;
            }
            if (position.c == 1) {
                finalPosition = Constants.GridPosition.UPPER_MIDDLE;
            }
            if (position.c == 2) {
                finalPosition = Constants.GridPosition.UPPER_RIGHT;
            }
        } else if (position.r == 1) {
            if (position.c == 0) {
                finalPosition = Constants.GridPosition.MIDDLE_LEFT;
            }
            if (position.c == 1) {
                finalPosition = Constants.GridPosition.MIDDLE_MIDDLE;
            }
            if (position.c == 2) {
                finalPosition = Constants.GridPosition.MIDDLE_RIGHT;
            }
        } else {
            if (position.c == 0) {
                finalPosition = Constants.GridPosition.LOWER_LEFT;
            }
            if (position.c == 1) {
                finalPosition = Constants.GridPosition.LOWER_MIDDLE;
            }
            if (position.c == 2) {
                finalPosition = Constants.GridPosition.LOWER_RIGHT;
            }
        }
        return finalPosition;
    }

    public CellPosition gridPositionToCellPosition(Constants.GridPosition position) {
        CellPosition finalPosition = new CellPosition(0, 0);
        if (position == Constants.GridPosition.UPPER_LEFT) {
            finalPosition.r = 0;
            finalPosition.c = 0;
        }
        if (position == Constants.GridPosition.UPPER_MIDDLE) {
            finalPosition.r = 0;
            finalPosition.c = 1;
        }
        if (position == Constants.GridPosition.UPPER_RIGHT) {
            finalPosition.r = 0;
            finalPosition.c = 2;
        }
        if (position == Constants.GridPosition.MIDDLE_LEFT) {
            finalPosition.r = 1;
            finalPosition.c = 0;
        }
        if (position == Constants.GridPosition.MIDDLE_MIDDLE) {
            finalPosition.r = 1;
            finalPosition.c = 1;
        }
        if (position == Constants.GridPosition.MIDDLE_RIGHT) {
            finalPosition.r = 1;
            finalPosition.c = 2;
        }
        if (position == Constants.GridPosition.LOWER_LEFT) {
            finalPosition.r = 2;
            finalPosition.c = 0;
        }
        if (position == Constants.GridPosition.LOWER_MIDDLE) {
            finalPosition.r = 2;
            finalPosition.c = 1;
        }
        if (position == Constants.GridPosition.LOWER_RIGHT) {
            finalPosition.r = 2;
            finalPosition.c = 2;
        }

        return finalPosition;
    }
}
