package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	public static final int SCR_WIDTH = 1920;
	public static final int SCR_HEIGHT = 1080;
	SpriteBatch batch;
	ScreenGame screenGame;
	ShopScreen shopScreen;
	public OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);


		screenGame = new ScreenGame(this);
		shopScreen = new ShopScreen(this);
		setScreen(screenGame);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}

