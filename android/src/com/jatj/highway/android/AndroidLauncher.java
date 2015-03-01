package com.jatj.highway.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.jatj.highway.Highway;
import com.jatj.highway.interfaces.EmptyHandleAds;
import com.jatj.highway.interfaces.EmptyHandleGooglePlay;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Highway(new EmptyHandleGooglePlay(), new EmptyHandleAds()), config);
	}
}
