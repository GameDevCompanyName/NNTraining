package com.gamedev.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gamedev.NNTraining;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "NNTraining";
		config.width = 800;
		config.height = 600;
		config.resizable = false;
		config.backgroundFPS = 30;
		config.foregroundFPS = 60;
		new LwjglApplication(new NNTraining(), config);
	}
}
