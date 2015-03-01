package com.jatj.highway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.jatj.highway.Highway;
import com.jatj.highway.gameworld.GameRenderer;
import com.jatj.highway.gameworld.GameWorld;
import com.jatj.highway.helpers.InputHandler;

/**
 * Created by knepe on 2015-03-01.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;
    private Highway game;

    // This is the constructor, not the class declaration
    public GameScreen(Highway game) {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameHeight = 136;
        float gameWidth =  screenWidth / (screenHeight / gameHeight);
        int midPointY = (int) (gameHeight / 2);

        this.game = game;
        world = new GameWorld(midPointY);
        Gdx.input.setInputProcessor(new InputHandler(world, this.game, screenWidth / gameWidth, screenHeight / gameHeight));
        Gdx.app.log("width", gameWidth + "");
        Gdx.app.log("height", gameHeight + "");
        renderer = new GameRenderer(world, game, (int) gameWidth, midPointY);
        world.setRenderer(renderer);
        game.adsHandler.showAds(true);
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(delta, runTime);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        if (!game.googlePlayHandler.getSignedIn())
            game.googlePlayHandler.login();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
