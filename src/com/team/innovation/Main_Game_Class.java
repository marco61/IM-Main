package com.team.innovation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Main_Game_Class implements ApplicationListener {

	SpriteBatch batch;
	Texture mT, texture;
	Player mP;
	Vector2 position;
	OrthographicCamera camera;
	Texture ramp;
	String message;
	BitmapFont text;

	@Override
	public void create() {
		final int HEIGHT = Gdx.graphics.getHeight();
		final int WIDTH = Gdx.graphics.getWidth();
		String message = "";

		/** Create SpriteBatch and player, load background texture **/
		batch = new SpriteBatch();
		mP = new Player(new Vector2(WIDTH * 1 / 10, HEIGHT * 4 / 5), "data/planeRed2.png");
		texture = new Texture(Gdx.files.internal("data/btb.png"));

		/** Set up camera **/
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.setToOrtho(false, WIDTH, HEIGHT);
		camera.update();
		
		/** Build the ramp **/
		ramp = new Texture(Gdx.files.internal("data/Start Area.png")); 
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		batch.draw(texture, 0, 0);
		
		batch.draw(ramp,0,0);

		batch.end();

		mP.update();

		camera.translate(mP.getPosition().x - camera.position.x + Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 10, 0);

		camera.update();

		batch.begin();

		batch.draw(mP.getCurrentFrame(), mP.getPosition().x, mP.getPosition().y);

		batch.end();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public boolean pan(float x, float y, float deltaX, float deltaY) {

		camera.translate(deltaX, 0);

		camera.update();

		return false;

	}
}