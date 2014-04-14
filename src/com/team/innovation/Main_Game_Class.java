package com.team.innovation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Main_Game_Class implements ApplicationListener {

	private static final int CAMERA_WIDTH = 1920;
	private static final int CAMERA_HEIGHT = 1080;
	SpriteBatch batch;
	Texture mT, texture;
	Player mP;
	Vector2 position;
	OrthographicCamera camera;

	@Override
	public void create() {
		/** Create SpriteBatch and player, load background texture **/
		batch = new SpriteBatch();
		mP = new Player(new Vector2(0, Gdx.graphics.getHeight() * 4 / 5),
				"data/planeRed2.png");
		texture = new Texture(Gdx.files.internal("data/btb.png"));

		/** Set up camera **/
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
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

		batch.end();

		mP.update();

		camera.translate(mP.getPosition().x - camera.position.x, 0);
		
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