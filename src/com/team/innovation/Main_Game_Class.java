package com.team.innovation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Main_Game_Class implements ApplicationListener {

	public enum gameState {
		PAUSED, RUNNING, RESUMED, STOPPED, MENU
	}

	SpriteBatch batch;
	Texture mT, texture, oT,gB;
	Player mP;
	Vector2 position;
	OrthographicCamera camera;
	OrthographicCamera camera2;
	Texture ramp;
	float scoreString;
	BitmapFont text;
	BitmapFont score;
	Array<Obstacle> lArr;
	World world;
	private gameState state = gameState.MENU;
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
		world = new World();
		lArr = world.getArray();

		/** Create SpriteBatch and player, load background texture **/
		batch = new SpriteBatch();
		mP = new Player(new Vector2(WIDTH * 1 / 10, HEIGHT * 4 / 5),
				"data/planeRed2.png"); // This does nothing at all.
		texture = new Texture(Gdx.files.internal("data/btb.png"));
		oT = new Texture(Gdx.files.internal("data/Star.png"));

		/** Set up camera **/
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.setToOrtho(false, WIDTH, HEIGHT);
		camera.update();
		
		/** Set up camera2 **/
		camera2 = new OrthographicCamera(WIDTH, HEIGHT);
		camera2.setToOrtho(false, WIDTH, HEIGHT);
		camera2.update();

		/** Build the ramp **/
		ramp = new Texture(Gdx.files.internal("data/Start Area.png"));
		
		/** Grass Block Texture **/
		gB = new Texture(Gdx.files.internal("data/Grass Block.png")); //Height 171


	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {

		final int HEIGHT = Gdx.graphics.getHeight();
		final int WIDTH = Gdx.graphics.getWidth();

		switch (state) {
		case MENU:
			/* MENU */
			
			Gdx.gl.glClearColor(1, 1, 1, 1);

			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			if (scoreString != 0) { this.reset(); }

			batch.setProjectionMatrix(camera2.combined);

			batch.begin();
			
			font.draw(batch, "Welcome to Innovation Flight!!!! ", (WIDTH / 9),
					HEIGHT - 100);

			font.draw(batch, "Tap Anywhere to Begin ", (WIDTH / 6) + 50, HEIGHT - 250);
			if (scoreString > 0) {
				score.drawMultiLine(batch, "Score: " + String.valueOf((int) scoreString),
						(WIDTH / 3), HEIGHT / 4);
			}

			batch.end();

			if (Gdx.input.isTouched()) {
				setGameState(gameState.RUNNING);
			}
			break;

		/* GAME */
		case RUNNING:
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.setProjectionMatrix(camera.combined);

			batch.begin();

			batch.draw(texture, 0, 0);
			
			batch.draw(gB, 0, 0);

			for (int i = 0; i <= mP.getPosition().x / 4096; i++) {
				batch.draw(texture, 4096 * (i + 1), 0);
			}
			
			for (int i = 0; i <= (mP.getPosition().x / 101) + WIDTH; i++) {
				batch.draw(gB, 101 * (i + 1), 0);
			}

			//batch.draw(ramp, 0, 0);

			batch.end();

			mP.update();

			camera.translate(mP.getPosition().x - camera.position.x
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

			batch.draw(mP.getCurrentFrame(), mP.getPosition().x,
					mP.getPosition().y);

			batch.end();

			batch.begin();

			/* Objects */
			lArr = world.getArray();
			
			for (int z = 0; z < 5; z++) {
					batch.draw(oT, lArr.get(z).x, lArr.get(z).y);
				}
				
			/* */
			batch.end();

			if (Gdx.input.isTouched()) {
				if (Gdx.input.getX() < WIDTH / 10
						&& Gdx.input.getY() < HEIGHT / 8) {
					setGameState(gameState.PAUSED);
				}
			}

			if (mP.gameOver()) {
				setGameState(gameState.MENU);
			}
			break;

		case PAUSED:
			if (Gdx.input.isTouched()) {
				if (Gdx.input.getX() < WIDTH / 10
						&& Gdx.input.getY() < HEIGHT / 8) {
					setGameState(gameState.RUNNING);
				} else if (Gdx.input.getX() > WIDTH * 9 / 10
						&& Gdx.input.getY() < HEIGHT / 8) {
					setGameState(gameState.MENU);
				}
			}

			break;

		case RESUMED:
			break;

		default:
			break;
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		state = gameState.PAUSED;
	}

	@Override
	public void resume() {
		state = gameState.RESUMED;
	}
	
	public void reset() {
		
		
		
	}

	public void setGameState(gameState s) {
		state = s;
	}
}