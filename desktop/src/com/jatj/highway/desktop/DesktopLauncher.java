package com.jatj.highway.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jatj.highway.Highway;
import com.jatj.highway.interfaces.EmptyHandleAds;
import com.jatj.highway.interfaces.EmptyHandleGooglePlay;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1024;
        config.height = 720;
		new LwjglApplication(new Highway(new EmptyHandleGooglePlay(), new EmptyHandleAds()), config);
	}
}
