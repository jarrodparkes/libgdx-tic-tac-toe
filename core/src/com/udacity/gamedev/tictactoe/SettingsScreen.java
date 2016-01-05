package com.udacity.gamedev.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.udacity.gamedev.tictactoe.strategy.MinimaxStrategy;
import com.udacity.gamedev.tictactoe.strategy.RandomStrategy;
import com.udacity.gamedev.tictactoe.strategy.RuleBasedStrategy;

public class SettingsScreen extends InputAdapter implements Screen {

    public static final String TAG = SettingsScreen.class.getName();

    TicTacToeGame game;

    ShapeRenderer renderer;
    SpriteBatch batch;
    FitViewport viewport;

    BitmapFont font;

    public SettingsScreen(TicTacToeGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        viewport = new FitViewport(Constants.SETTINGS_WORLD_SIZE, Constants.SETTINGS_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getData().setScale(Constants.SETTINGS_LABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Constants.SETTINGS_BUBBLE_COLOR);
        renderer.circle(Constants.RANDOM_CENTER.x, Constants.RANDOM_CENTER.y, Constants.SETTINGS_BUBBLE_RADIUS);
        renderer.circle(Constants.RULE_BASED_CENTER.x, Constants.RULE_BASED_CENTER.y, Constants.SETTINGS_BUBBLE_RADIUS);
        renderer.circle(Constants.MINIMAX_CENTER.x, Constants.MINIMAX_CENTER.y, Constants.SETTINGS_BUBBLE_RADIUS);
        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        final GlyphLayout easyLayout = new GlyphLayout(font, Constants.RANDOM_LABEL);
        font.draw(batch, Constants.RANDOM_LABEL, Constants.RANDOM_CENTER.x, Constants.RANDOM_CENTER.y + easyLayout.height / 2, 0, Align.center, false);
        final GlyphLayout mediumLayout = new GlyphLayout(font, Constants.RULE_BASED_LABEL);
        font.draw(batch, Constants.RULE_BASED_LABEL, Constants.RULE_BASED_CENTER.x, Constants.RULE_BASED_CENTER.y + mediumLayout.height / 2, 0, Align.center, false);
        final GlyphLayout hardLayout = new GlyphLayout(font, Constants.MINIMAX_LABEL);
        font.draw(batch, Constants.MINIMAX_LABEL, Constants.MINIMAX_CENTER.x, Constants.MINIMAX_CENTER.y + hardLayout.height / 2, 0, Align.center, false);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        if (worldTouch.dst(Constants.RANDOM_CENTER) < Constants.SETTINGS_BUBBLE_RADIUS) {
            game.showTicTacToeScreen(new RandomStrategy());
        }

        if (worldTouch.dst(Constants.RULE_BASED_CENTER) < Constants.SETTINGS_BUBBLE_RADIUS) {
            game.showTicTacToeScreen(new RuleBasedStrategy());
        }

        if (worldTouch.dst(Constants.MINIMAX_CENTER) < Constants.SETTINGS_BUBBLE_RADIUS) {
            game.showTicTacToeScreen(new MinimaxStrategy());
        }

        return true;
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}
}