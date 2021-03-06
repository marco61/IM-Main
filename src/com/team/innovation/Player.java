package com.team.innovation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Circle;

public class Player {

	Vector2 position;
	Vector2 velocity = new Vector2();
	String textureLoc;

	private static final int HEIGHT = Gdx.graphics.getHeight();
	private static final int WIDTH = Gdx.graphics.getWidth();

	private static final int col = 1;
	private static final int row = 1;

	private static final float GRAVITY = -3f;

	private Circle boundingCircle;
	private static final float playerRadius = 32f;
	private float targetVelocity = 5f;

	Animation animation;
	Texture playerTexture;
	TextureRegion[] frames;
	TextureRegion currentFrame;
	float stateTime;

	public Player(Vector2 position, String textureLoc) {
		this.position = position;

		playerTexture = new Texture(Gdx.files.internal("data/planeRed1.png"));
		TextureRegion[][] tmp = TextureRegion
				.split(playerTexture, playerTexture.getWidth() / col,
						playerTexture.getHeight() / row);
		frames = new TextureRegion[col * row];

		int index = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				frames[index++] = tmp[i][j];
			}
		}

		velocity.x = 0f;
		animation = new Animation(1f, frames);
		stateTime = 0f;
		currentFrame = animation.getKeyFrame(0);
		boundingCircle = new Circle(position, playerRadius);
	}

	public Circle getCircle() {
		return boundingCircle;
	}

	public String getTextureLoc() {
		return textureLoc;
	}

	public void setTextureLoc(String textureLoc) {
		this.textureLoc = textureLoc;
	}

	public Texture getPlayerTexture() {
		return playerTexture;
	}

	public void setPlayerTexture(Texture playerTexture) {
		this.playerTexture = playerTexture;
	}

	/** Update Loop **/
	public void update() {
		if (targetVelocity < 30f) { // Velocity cap
			targetVelocity = 5f + ((int) position.x / 1500) * .5f;
		}

		boundingCircle.set(position, playerRadius);

		if (stateTime < 8) {
			stateTime += Gdx.graphics.getDeltaTime();
		} else {
			stateTime = 0;
		}
		stateTime += Gdx.graphics.getDeltaTime();

		/** Desktop Controls **/
		if (Gdx.input.isKeyPressed(Keys.W)) {
			position.y += 2f;
			currentFrame = animation.getKeyFrame(8 + stateTime);
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			position.x -= 2f;
			currentFrame = animation.getKeyFrame(16 + stateTime);
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			position.x += 2f;
			currentFrame = animation.getKeyFrame(24 + stateTime);
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			position.y -= 2f;
			currentFrame = animation.getKeyFrame(0 + stateTime);
		}

		/** Touch Controls **/
		if (Gdx.input.isTouched()) {
			if (touchUp(Gdx.input.getX(), Gdx.input.getY()))
				if (velocity.x <= targetVelocity)
					position.y += 15f * (velocity.x / targetVelocity);
				else
					position.y += 15f;
			if (touchDown(Gdx.input.getX(), Gdx.input.getY()))
				if (velocity.x <= targetVelocity)
					position.y -= 10f * (velocity.x / targetVelocity);
				else
					position.y -= 10f;
			else if (position.y > 120)
				position.y += GRAVITY;
			else if (position.y <= 120 && position.x > 1000) {
				Gdx.input.vibrate(100);
				if (velocity.x > 0) {
					position.y += 10f;
					velocity.x -= .1f;
				}
			}
		}

		/** Gravity and Ground **/
		else if (position.y > 120)
			position.y += GRAVITY;
		else if (position.y <= 120 && position.x > 1000) {
			Gdx.input.vibrate(100);
			if (velocity.x > 0) {
				position.y += 10f;
				velocity.x -= targetVelocity / 50;
			}
		}

		if (velocity.x > targetVelocity) {
			if (velocity.x - .03f < targetVelocity)
				velocity.x = targetVelocity;
			else
				velocity.x -= .03f;
		} else if (velocity.x < targetVelocity
				&& ((position.y > 130 && (velocity.x > 0)) || position.x < 1000)) {
			if (velocity.x + .03f > targetVelocity)
				velocity.x = targetVelocity;
			else
				velocity.x += .03f;
		}

		/** Horizontal Movement **/
		position.x += velocity.x;

		if (velocity.x < 0)
			velocity.x = 0;
	}

	/** End Update Loop **/

	public boolean touchUp(int x, int y) {
		if (x > WIDTH / 2 && y > 3 * HEIGHT / 4 && position.y < HEIGHT) {
			return true;
		}
		return false;
	}

	public boolean touchDown(int x, int y) {
		if (x < WIDTH / 2 && y > 3 * HEIGHT / 4 && position.y > 120) {
			return true;
		}
		return false;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public TextureRegion[] getFrames() {
		return frames;
	}

	public void setFrames(TextureRegion[] frames) {
		this.frames = frames;
	}

	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	/**
	 * Changes player velocity because of a collision.
	 * 
	 * @param xVel
	 *            The amount by which to change the velocity.
	 * @param vibrate
	 *            How many ms to vibrate, if any.
	 */
	public void collide(float xVel, int vibrate) {
		velocity.x += xVel;
		if (vibrate > 0)
			Gdx.input.vibrate(vibrate);
	}

	public boolean gameOver() {
		if (velocity.x <= 0 && position.x > 1000)
			return true;
		else
			return false;
	}
}