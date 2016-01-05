package com.udacity.gamedev.tictactoe;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.tictactoe.board.CellPosition;

public class Constants {

    public static final Vector2 WORLD_SIZE = new Vector2(16f, 9f);

    public static final float HUD_FONT_REFERENCE_SCREEN_WIDTH = 480.0f;

    public static final Color BACKGROUND_COLOR = Color.BLUE;

    public static final Color PLAYFIELD_COLOR = Color.WHITE;
    public static final float PLAYFIELD_LINE_THICKNESS = 0.1f;
    public static final Vector2 PLAYFIELD_CENTER = new Vector2(WORLD_SIZE.x / 4, WORLD_SIZE.y / 2);
    public static final float PLAYFIELD_GRID_SIZE = WORLD_SIZE.x / 8;
    public static final float PLAYFIELD_GRID_SIZE_HALF = PLAYFIELD_GRID_SIZE / 2;

    public static final Color CROSS_COLOR = Color.WHITE;
    public static final float CROSS_THICKNESS = 0.1f;
    public static final float CROSS_OFFSET_FROM_CENTER = PLAYFIELD_GRID_SIZE * 0.3f;

    public static final Color CIRCLE_COLOR = Color.WHITE;
    public static final float CIRCLE_RADIUS = PLAYFIELD_GRID_SIZE * 0.4f;
    public static final float CIRCLE_THICKNESS = 0.1f;

    public static final CellPosition[][] WINNING_PATTERNS = new CellPosition[][]{
            { new CellPosition(0, 0), new CellPosition(0, 1), new CellPosition(0, 2) },
            { new CellPosition(1, 0), new CellPosition(1, 1), new CellPosition(1, 2) },
            { new CellPosition(2, 0), new CellPosition(2, 1), new CellPosition(2, 2) },
            { new CellPosition(0, 0), new CellPosition(1, 0), new CellPosition(2, 0) },
            { new CellPosition(0, 1), new CellPosition(1, 1), new CellPosition(2, 1) },
            { new CellPosition(0, 2), new CellPosition(1, 2), new CellPosition(2, 2) },
            { new CellPosition(0, 0), new CellPosition(1, 1), new CellPosition(2, 2) },
            { new CellPosition(2, 0), new CellPosition(1, 1), new CellPosition(0, 2) }
    };

    public static final int THREE_CROSS_BONUS = 100;
    public static final int TWO_CROSS_BONUS = 10;
    public static final int ONE_CROSS_BONUS = 1;

    public static final int THREE_NOUGHT_BONUS = -100;
    public static final int TWO_NOUGHT_BONUS = -10;
    public static final int ONE_NOUGHT_BONUS = -1;

    public static final Vector2 RESET_CENTER = new Vector2(WORLD_SIZE.x * 3 / 4, WORLD_SIZE.y / 2);

    public enum GridPosition {
        LOWER_LEFT(PLAYFIELD_CENTER.x - PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y - PLAYFIELD_GRID_SIZE),
        LOWER_MIDDLE(PLAYFIELD_CENTER.x, PLAYFIELD_CENTER.y - PLAYFIELD_GRID_SIZE),
        LOWER_RIGHT(PLAYFIELD_CENTER.x + PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y - PLAYFIELD_GRID_SIZE),
        MIDDLE_LEFT(PLAYFIELD_CENTER.x - PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y),
        MIDDLE_MIDDLE(PLAYFIELD_CENTER.x, PLAYFIELD_CENTER.y),
        MIDDLE_RIGHT(PLAYFIELD_CENTER.x + PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y),
        UPPER_LEFT(PLAYFIELD_CENTER.x - PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y + PLAYFIELD_GRID_SIZE),
        UPPER_MIDDLE(PLAYFIELD_CENTER.x, PLAYFIELD_CENTER.y + PLAYFIELD_GRID_SIZE),
        UPPER_RIGHT(PLAYFIELD_CENTER.x + PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y + PLAYFIELD_GRID_SIZE);

        Vector2 position;

        GridPosition(float x, float y) {
            this.position = new Vector2(x, y);
        }
    }

    public static final float SETTINGS_WORLD_SIZE = 480.0f;
    public static final float SETTINGS_BUBBLE_RADIUS = SETTINGS_WORLD_SIZE / 8.5f;
    public static final Color SETTINGS_BUBBLE_COLOR = new Color(0.2f, 0.2f, 1, 1);
    public static final float SETTINGS_LABEL_SCALE = 1.5f;

    public static final Vector2 RANDOM_CENTER = new Vector2(SETTINGS_WORLD_SIZE / 4, SETTINGS_WORLD_SIZE / 2);
    public static final Vector2 RULE_BASED_CENTER = new Vector2(SETTINGS_WORLD_SIZE / 2, SETTINGS_WORLD_SIZE / 2);
    public static final Vector2 MINIMAX_CENTER = new Vector2(SETTINGS_WORLD_SIZE * 3 / 4, SETTINGS_WORLD_SIZE / 2);

    public static final String RANDOM_LABEL = "Random";
    public static final String RULE_BASED_LABEL = "Rules";
    public static final String MINIMAX_LABEL = "Minimax";
}