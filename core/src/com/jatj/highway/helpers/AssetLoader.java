package com.jatj.highway.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by knepe on 2015-03-01.
 */
public class AssetLoader {
    public static Texture texture, logoTexture;

    public static TextureRegion bg;
    public static TextureRegion roadLine;
    public static TextureRegion player;
    public static BitmapFont font;
    public static BitmapFont scoreFont;
    public static TextureRegion logo;
    public static TextureRegion playButtonUp;
    public static TextureRegion playButtonDown;
    public static TextureRegion highScoreButtonUp;
    public static TextureRegion highScoreButtonDown;

    public static void load() {
        texture = new Texture(Gdx.files.internal("texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        logoTexture = new Texture(Gdx.files.internal("logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture bgTexture = new Texture(Gdx.files.internal("road_background_3lanes.png"));
        bgTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture playerTexture = new Texture(Gdx.files.internal("player.png"));
        playerTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture roadLineTexture = new Texture(Gdx.files.internal("roadline.png"));
        roadLineTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        roadLine = new TextureRegion(roadLineTexture, 0, 0, 53, 28);
        roadLine.flip(false, true);
        player = new TextureRegion(playerTexture, 0, 0, 33, 27);
        player.flip(false, true);

        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        bg = new TextureRegion(bgTexture, 0, 0, 921, 493);
        bg.flip(false, true);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        highScoreButtonUp = new TextureRegion(texture, 0, 99, 29, 16);
        highScoreButtonDown = new TextureRegion(texture, 29, 99, 29, 16);
        highScoreButtonUp.flip(false, true);
        highScoreButtonDown.flip(false, true);

        font = new BitmapFont(Gdx.files.internal("whitetext.fnt"));
        font.setScale(.25f, -.25f);

        scoreFont = new BitmapFont(Gdx.files.internal("whitetext.fnt"));
        scoreFont.setScale(.1f, -.1f);
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        font.dispose();
    }
}
