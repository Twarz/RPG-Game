package com.elemens.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.elemens.ElemensGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Elemens";
		config.useGL30 = false;
		config.width = 1600;
		config.height = 900;
		Application app = new LwjglApplication(new ElemensGame(), config);
		Gdx.app = app;
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}