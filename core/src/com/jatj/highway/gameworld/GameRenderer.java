package com.jatj.highway.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jatj.highway.Highway;
import com.jatj.highway.gameobjects.Player;
import com.jatj.highway.gameobjects.RoadLine;
import com.jatj.highway.gameobjects.ScrollHandler;
import com.jatj.highway.helpers.AssetLoader;
import com.jatj.highway.helpers.InputHandler;
import com.jatj.highway.tweenaccessors.Value;
import com.jatj.highway.ui.SimpleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knepe on 2015-03-01.
 */
public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    public Highway game;

    private SpriteBatch batcher;

    private int midPointY;

    // Game Objects
    private Player player;
    private ScrollHandler scroller;
    private RoadLine roadLine;

    // Game Assets
    private TextureRegion bg, roadLineAsset, playerAsset;
    // Buttons
    private List<SimpleButton> menuButtons;
    private Color transitionColor;

    private List<String> unlockedAchievements;

    public GameRenderer(GameWorld world, Highway game, int gameWidth, int midPointY) {
        myWorld = world;
        this.game = game;

        this.midPointY = midPointY;
        this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
                .getMenuButtons();

        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, 136);

        batcher = new SpriteBatch();
        batcher.enableBlending();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        unlockedAchievements = new ArrayList<String>();

        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        player = myWorld.getPlayer();
        scroller = myWorld.getScroller();
        roadLine = scroller.getRoadLine();
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        roadLineAsset = AssetLoader.roadLine;
        playerAsset = AssetLoader.player;
    }

    private void drawRoadLines() {
        batcher.draw(roadLineAsset, roadLine.getX(), roadLine.getY(),
                roadLine.getWidth(), roadLine.getHeight(), roadLine.getWidth(), roadLine.getHeight(), 1, 1, -.45f);
    }


    private void drawPLayerCentered(float runTime) {
        batcher.draw(playerAsset, 59, player.getY(),
                player.getWidth() / 2.0f, player.getHeight() / 2.0f,
                player.getWidth(), player.getHeight(), 1, 1, player.getRotation());
    }

    private void drawPlayer(float runTime) {
        batcher.draw(playerAsset, player.getX(), player.getY(),
                player.getWidth() / 2.0f, player.getHeight() / 2.0f,
                player.getWidth(), player.getHeight(), 1, 1, player.getRotation());
    }

    private void drawMenuUI() {
        for (SimpleButton button : menuButtons) {
            button.draw(batcher);
        }
    }

    private void drawReady() {
        AssetLoader.font.draw(batcher, "GET READY!",
                24, midPointY - 51);
    }

    private void drawGameOver() {
        AssetLoader.font.draw(batcher, "GAME OVER",
                22, midPointY - 51);
    }

    private void drawScore() {
        int length = ("" + myWorld.getScore()).length();
        AssetLoader.scoreFont.draw(batcher, "" + myWorld.getScore(),
                68 - (3 * length), midPointY - 83);
    }

    public void render(float delta, float runTime) {
        float[] values = hextoRGB("#29343c");
        Gdx.gl.glClearColor(values[0], values[1], values[2], 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.enableBlending();
        batcher.draw(bg, 0, 0, 300, 136);
        drawRoadLines();

        if (myWorld.isRunning()) {
            drawPlayer(runTime);
            drawScore();
        } else if (myWorld.isReady()) {
            drawPlayer(runTime);
            drawReady();
        } else if (myWorld.isMenu()) {
            drawPLayerCentered(runTime);
            drawMenuUI();
        } else if (myWorld.isGameOver()) {
            drawPlayer(runTime);
            drawGameOver();
        } else if (myWorld.isHighScore()) {
            drawPlayer(runTime);
        }



        batcher.end();
        unlockAchievement();
    }

    private void unlockAchievement(){
        int score = myWorld.getScore();
        if(score < 25) return;

        if(score >= 25 && !unlockedAchievements.contains("xxx")){
            game.googlePlayHandler.unlockAchievement("xxx");
            unlockedAchievements.add("xxx");
        }
        if(score >= 75 && !unlockedAchievements.contains("xxx")){
            game.googlePlayHandler.unlockAchievement("xxx");
            unlockedAchievements.add("xxx");
        }
        if(score >= 150 && !unlockedAchievements.contains("xxx")){
            game.googlePlayHandler.unlockAchievement("xxx");
            unlockedAchievements.add("xxx");
        }
        if(score >= 200 && !unlockedAchievements.contains("xxx")){
            game.googlePlayHandler.unlockAchievement("xxx");
            unlockedAchievements.add("xxx");
        }
        if(score >= 250 && !unlockedAchievements.contains("xxx")){
            game.googlePlayHandler.unlockAchievement("xxx");
            unlockedAchievements.add("xxx");
        }
    }


    /* re-map RGB colors so they can be used in OpenGL */
    private float[] map(float[]rgb) {

    /* RGB is from 0 to 255 */
    /* THIS is from 0 to 1 (float) */

        // 1 : 2 = x : 4 >>> 2

    /*
    *
    * 240 : 255 = x : 1
    *
    * */

        float[] result = new float[3];
        result[0] = rgb[0] / 255;
        result[1] = rgb[1] / 255;
        result[2] = rgb[2] / 255;
        return result;
    }

    private float[] hextoRGB(String hex) {

        float[] rgbcolor = new float[3];
        rgbcolor[0] = Integer.valueOf( hex.substring( 1, 3 ), 16 );
        rgbcolor[1] = Integer.valueOf( hex.substring( 3, 5 ), 16 );
        rgbcolor[2] = Integer.valueOf( hex.substring( 5, 7 ), 16 );
        return map(rgbcolor);
    }

}
