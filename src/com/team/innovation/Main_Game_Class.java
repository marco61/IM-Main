package com.team.innovation;

import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Main_Game_Class implements ApplicationListener {

	public enum gameState {
		PAUSED, RUNNING, RESUMED, STOPPED, MENU
	}

	SpriteBatch batch;
	Texture mT, backdrop, star, gB, jet, rock, runway;
	Player mP;
	Vector2 position;
	OrthographicCamera camera;
	OrthographicCamera camera2;
	float scoreString;
	BitmapFont text;
	BitmapFont score;
	Array<Obstacle> lArr;
	Array<String> objArr;
	World world;
	private gameState state = gameState.MENU;
	int check = 0;
	BitmapFont font;
	Preferences prefs;
	int prob_rock = 0, prob_star = 0, prob_jet = 0;
	Random rand;
	int lastHighscore = 0;

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
		objArr = world.getTypes();
		prefs = Gdx.app.getPreferences("Preferences");
		rand = new Random();

		/** Create SpriteBatch and player, load background & obj textures **/
		batch = new SpriteBatch();
		mP = new Player(new Vector2(WIDTH * 1 / 10, 120), "data/planeRed2.png");
		backdrop = new Texture(Gdx.files.internal("data/btb.png"));
		star = new Texture(Gdx.files.internal("data/Star.png"));
		jet = new Texture(Gdx.files.internal("data/jet.png"));
		rock = new Texture(Gdx.files.internal("data/Rock.png"));

		/** Set up camera **/
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.setToOrtho(false, WIDTH, HEIGHT);
		camera.update();

		/** Set up camera2 **/
		camera2 = new OrthographicCamera(WIDTH, HEIGHT);
		camera2.setToOrtho(false, WIDTH, HEIGHT);
		camera2.update();

		/** Build the runway **/
		runway = new Texture(Gdx.files.internal("data/Runway.png"));

		/** Grass Block Texture **/
		gB = new Texture(Gdx.files.internal("data/Grass Block.png")); // Height
																		// 171

	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {

		final int HEIGHT = Gdx.graphics.getHeight();
		final int WIDTH = Gdx.graphics.getWidth();

		switch (state) {

		/* MENU */
		case MENU:
			Gdx.gl.glClearColor(1, 1, 1, 1);

			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.setProjectionMatrix(camera2.combined);

			batch.begin();

			if (scoreString > 0) {
				if ((int) scoreString > prefs.getInteger("highscore")) {
					lastHighscore = prefs.getInteger("highscore");
					prefs.putInteger("highscore", (int) scoreString);
					prefs.flush();
				}
				if ((int) scoreString > lastHighscore) {
					menuPrint("New highscore!", 7);
				}
				menuPrint("Score: " + (int) scoreString, 6);
				menuPrint("Game Over", 8);
				menuPrint("Tap Anywhere to Play Again", 4);
			} else {
				menuPrint("Welcome to Innovation Flight!!!!", 6);
				menuPrint("Tap Anywhere to Begin", 4);
			}

			batch.end();

			if (Gdx.input.justTouched()) {
				/* Test purposes only, resets highscore */
				if (Gdx.input.getX() > WIDTH * 8 / 10
						&& Gdx.input.getY() < HEIGHT / 8) {
					prefs.putInteger("highscore", 0);
					scoreString = 0;
					Gdx.input.vibrate(100);
					batch.begin();
					font.draw(batch, "-Reset-", 0, 0);
					batch.end();
					System.out.println("Reset");
					prefs.flush();
				} /**/
				else {
					if (mP.gameOver()) {
						this.reset();
					}
					setGameState(gameState.RUNNING);
				}
			}
			break;

		/* GAME */
		case RUNNING:
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.setProjectionMatrix(camera.combined);

			batch.begin();

			batch.draw(backdrop, 0, 0);

			batch.draw(runway, 0, 0);

			batch.draw(gB, 1000, 0);

			/* Draw backdrop and grass */
			for (int i = 0; i <= mP.getPosition().x / 4096; i++) {
				batch.draw(backdrop, 4096 * (i + 1), 0);
			}

			for (int i = 0; i <= (mP.getPosition().x / 101) + WIDTH; i++) {
				batch.draw(gB, 1000 + 101 * (i + 1), 0);
			}

			batch.end();

			mP.update();

			camera.translate(mP.getPosition().x - camera.position.x
					+ Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()
					/ 10, 0);

			camera.update();

			batch.begin();

			/* Text Display */
			float x = mP.getPosition().x - Gdx.graphics.getWidth() / 10;

			scoreString += mP.getVelocity().x / 5;
			score.drawMultiLine(batch, String.valueOf((int) scoreString), x,
					HEIGHT);
			if (mP.getPosition().x / 50 < 13) {
				text.scale(mP.getPosition().x / 75);
				text.drawMultiLine(batch, "Go!", x, HEIGHT);
			} else
				text.dispose();
			if (mP.getPosition().x < WIDTH * 3 / 4
					+ font.getBounds("Pull up!").width
					&& mP.getPosition().y < HEIGHT / 3) {
				font.draw(batch, "Pull up!", WIDTH * 3 / 4, HEIGHT / 3);
			}
			/**/

			batch.draw(mP.getCurrentFrame(), mP.getPosition().x,
					mP.getPosition().y);

			batch.end();

			batch.begin();

			/* Objects */

			for (int i = 0; i < 500; i++) {
				if (lArr.get(i).x > 3000 && lArr.get(i).y > 120) {
					if (objArr.get(i).equals("ROCK")) {
						batch.draw(rock, lArr.get(i).x, lArr.get(i).y);
						if (mP.getCircle().overlaps(
								new Circle(lArr.get(i).x, lArr.get(i).y, 42f))) {
							mP.collide(-10f, 100);
						}
					} else {
						batch.draw(star, lArr.get(i).x, lArr.get(i).y);
						if (mP.getCircle().overlaps(
								new Circle(lArr.get(i).x, lArr.get(i).y, 30f))) {
							mP.collide(mP.getVelocity().x * 2, 25);
							lArr.set(i, new Obstacle(0, 0));
						}
					}
				}
			}

			/* */

			batch.end();

			if (Gdx.input.justTouched()) {
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
			batch.begin();
			batch.draw(mP.getCurrentFrame(), mP.getPosition().x,
					mP.getPosition().y);
			batch.end();
			if (Gdx.input.justTouched()) {
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

	/**
	 * Automates printing horizontally-centered BitmapFont text.
	 * 
	 * @param s
	 *            The string to print.
	 * @param height
	 *            The height to print at, out of 10.
	 */
	private void menuPrint(String s, float height) {
		font.draw(batch, s, Gdx.graphics.getWidth() / 2
				- font.getBounds(s).width / 2,
				(height / 10) * Gdx.graphics.getHeight());
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
		this.dispose();
		this.create();
	}

	public void setGameState(gameState s) {
		state = s;
	}
}