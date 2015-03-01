package com.jatj.highway.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.jatj.highway.Highway;
import com.jatj.highway.gameobjects.Player;
import com.jatj.highway.gameworld.GameWorld;
import com.jatj.highway.ui.SimpleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knepe on 2015-03-01.
 */
public class InputHandler implements InputProcessor {
    private Player player;
    private GameWorld myWorld;
    private Highway game;

    private List<SimpleButton> menuButtons;

    private SimpleButton playButton;
    private SimpleButton highScoreButton;

    private float scaleFactorX;
    private float scaleFactorY;

    public InputHandler(GameWorld myWorld, Highway game, float scaleFactorX,
                        float scaleFactorY) {
        this.myWorld = myWorld;
        player = myWorld.getPlayer();
        this.game = game;

        int midPointY = myWorld.getMidPointY();

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(
                136 / 2 - (AssetLoader.playButtonUp.getRegionWidth()),
                midPointY + 50, 29, 16, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown);
        highScoreButton = new SimpleButton(
                126 / 2 + (AssetLoader.highScoreButtonUp.getRegionWidth() / 2),
                midPointY + 50, 29, 16, AssetLoader.highScoreButtonUp,
                AssetLoader.highScoreButtonDown);
        menuButtons.add(playButton);
        menuButtons.add(highScoreButton);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        Gdx.app.log("screenx", screenX + "");

        if (myWorld.isMenu() || myWorld.isGameOver() || myWorld.isHighScore()) {
            playButton.isTouchDown(screenX, screenY);
            highScoreButton.isTouchDown(screenX, screenY);
        } else if (myWorld.isReady()) {
            myWorld.start();
            player.onClick();
        } else if (myWorld.isRunning()) {
            player.onClick();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (myWorld.isMenu()) {
            if (playButton.isTouchUp(screenX, screenY)) {
                myWorld.ready();
                return true;
            }
            else if(highScoreButton.isTouchUp(screenX, screenY)){
                game.googlePlayHandler.showLeaderboard();
                return true;
            }
        }
        else if(myWorld.isGameOver() || myWorld.isHighScore()){
            if (playButton.isTouchUp(screenX, screenY)) {
                myWorld.restart();
                return true;
            }
            else if(highScoreButton.isTouchUp(screenX, screenY)){
                game.googlePlayHandler.showLeaderboard();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        // Can now use Space Bar to play the game
        if (keycode == Input.Keys.SPACE) {

            if (myWorld.isMenu()) {
                myWorld.ready();
            } else if (myWorld.isReady()) {
                myWorld.start();
            }

            player.onClick();

            if (myWorld.isGameOver() || myWorld.isHighScore()) {
                myWorld.restart();
            }

        }
        if(keycode == Input.Keys.BACK && (myWorld.isMenu() || myWorld.isReady()))
            Gdx.app.exit();

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }
}