package com.team.innovation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Main_Game_Class implements ApplicationListener {

	SpriteBatch batch;
	Texture mT, texture;
	Player mP;
	Vector2 position;
	OrthographicCamera camera;
	Texture ramp;
	float scoreString;
	BitmapFont text;
	BitmapFont score;
	Array<Obstacle> lArr;
	World world;
	int check = 0;
	BitmapFont font;
	
	@Override
	public void create() {
		final int HEIGHT = Gdx.graphics.getHeight();
		final int WIDTH = Gdx.graphics.getWidth();

		scoreString = 0;
		text = new BitmapFont();
		score = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		String message = ""; // TODO
		world = new World();

		/** Create SpriteBatch and player, load background texture **/
		batch = new SpriteBatch();
		mP = new Player(new Vector2(WIDTH * 1 / 10, HEIGHT * 4 / 5),
				"data/planeRed2.png"); // This does nothing at all.
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
		
		/////// Menu
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
       
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
        font.draw(batch, "Welcome to Innovation Flight!!!! ", 100, 400);
        font.draw(batch, "Tap Anywhere to Begin ", 220, 325);
        batch.end();

        if (Gdx.input.isTouched()) {
           
        	check = 1;
           
        }
		
		/////// Game
        
        if (check == 1) {

		batch.begin();

		batch.draw(texture, 0, 0);

		for (int i = 0; i <= mP.getPosition().x / 4096; i++)
			batch.draw(texture, 4096 * (i + 1), 0);

		batch.draw(ramp, 0, 0);
		
		lArr = world.getArray(); // needs to be implemented...?

		batch.end();

		mP.update();

		camera.translate(
				mP.getPosition().x - camera.position.x
						+ Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()
						/ 10, 0);

		camera.update();

		batch.begin();

		/* Text Display */
		float x = mP.getPosition().x - Gdx.graphics.getWidth() / 10;
		float y = Gdx.graphics.getHeight();

		scoreString += mP.getVelocity().x / 5;
		score.drawMultiLine(batch, String.valueOf((int) scoreString), x, y);
		if (mP.getPosition().x / 50 < 13) {
			text.scale(mP.getPosition().x / 75);
			text.drawMultiLine(batch, "Go!", x, y);
		} else
			text.dispose();
		/**/

		batch.draw(mP.getCurrentFrame(), mP.getPosition().x, mP.getPosition().y);

		batch.end();
		
        }
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