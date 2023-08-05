package com.badlogic.pokemon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.pokemon.screen.GameScreen;

public class Pokemon extends Game {

	private GameScreen screen;
	private AssetManager assetManager;

	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		assetManager = new AssetManager();
		assetManager.load("packed/red_walking.atlas", TextureAtlas.class);
		assetManager.finishLoading();

		screen = new GameScreen(this);
		this.setScreen(screen);

		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
