package com.udacity.gamedev.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.udacity.gamedev.tictactoe.strategy.MinimaxStrategy;
import com.udacity.gamedev.tictactoe.strategy.Strategy;

public class TicTacToeScreen extends InputAdapter implements Screen {

    public static final String TAG = TicTacToeScreen.class.getName();

    // world viewport
    ShapeRenderer renderer;
    ExtendViewport viewport;

    // HUD viewport
    SpriteBatch batch;
    ScreenViewport textViewport;
    BitmapFont font;

    // game data
    TicTacToeGame game;
    Strategy comStrategy;
    GameHandler handler;

    public TicTacToeScreen(TicTacToeGame game, Strategy comStrategy) {
        this.game = game;
        this.comStrategy = comStrategy;
    }

    @Override
    public void show () {
        // setup world
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        viewport = new ExtendViewport(Constants.WORLD_SIZE.x, Constants.WORLD_SIZE.y);
        handler = new GameHandler(game, comStrategy);
        // setup HUD
        batch = new SpriteBatch();
        textViewport = new ScreenViewport();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize (int width, int height) {
        viewport.update(width, height, true);
        textViewport.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_WIDTH * 2);
    }

    @Override
    public void render (float delta) {
        // clear screen
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // setup drawing for HUD
        batch.setProjectionMatrix(textViewport.getCamera().combined);
        // draw HUD
        batch.begin();
        batch.end();
        // setup drawing for world
        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        // draw world
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        handler.render(delta, renderer);
        renderer.end();
    }

    @Override
    public void dispose () {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        if (handler.board.gameOver() == false) {
            Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));
            handler.handleTouch(worldTouch);
        } else {
            handler.reset();
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
