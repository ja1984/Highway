package com.jatj.highway;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jatj.highway.helpers.AssetLoader;
import com.jatj.highway.interfaces.IHandleAds;
import com.jatj.highway.interfaces.IHandleGooglePlay;
import com.jatj.highway.screens.GameScreen;
import com.jatj.highway.screens.SplashScreen;

public class Highway extends Game {
    public Highway(IHandleGooglePlay googlePlayHandler, IHandleAds adsHandler){
        super();

        this.googlePlayHandler = googlePlayHandler;
        this.adsHandler = adsHandler;
    }

    public IHandleGooglePlay googlePlayHandler;
    public IHandleAds adsHandler;
	
	@Override
	public void create () {
        Gdx.input.setCatchBackKey(true);
        AssetLoader.load();
        setScreen(new GameScreen(this));
	}

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
