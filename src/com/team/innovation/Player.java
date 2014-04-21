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
	Vector2 acceleration = new Vector2();
	String textureLoc;

	private static final int HEIGHT = Gdx.graphics.getHeight();
	private static final int WIDTH = Gdx.graphics.getWidth();

	private static final int col = 1;
	private static final int row = 1;

	private static final float GRAVITY = -5f;

	private Circle boundingCircle;
	private static final float xComp = 5f;
	private static final float yComp = 5f;
	private static final float playerRadius = 5f;
	private float velocityX = 5f;

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

		velocity.x = 5f;
		animation = new Animation(1f, frames);
		stateTime = 0f;
		currentFrame = animation.getKeyFrame(0);
		boundingCircle = new Circle(xComp, yComp, playerRadius);
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
		// velocityX = 5f + (int) position.x % 2000;

		if (stateTime < 8) {
			stateTime += Gdx.graphics.getDeltaTime();
		} else {
			stateTime = 0;
		}
		acceleration.y = GRAVITY;
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
			if (touchUp(Gdx.input.getX(), Gdx.input.getY()) && velocity.x > 0)
				position.y += 15f;
			if (touchDown(Gdx.input.getX(), Gdx.input.getY()))
				position.y -= 10f;
			else if (position.y > 0)
				position.y += GRAVITY;
			else if (position.y <= 0) {
				Gdx.input.vibrate(100);
				if (velocity.x > 0) {
					position.y += 10f;
					velocity.x -= .1f;
				}
			}
		}

		/** Gravity and Ground **/
		else if (position.y > 0)
			position.y += GRAVITY;
		else if (position.y <= 0) {
			Gdx.input.vibrate(100);
			if (velocity.x > 0) {
				position.y += 10f;
				velocity.x -= .1f;
			}
		}

		if (velocity.x > 5f) {
			if (velocity.x - .03f < 5f)
				velocity.x = 5f;
			else
				velocity.x -= .03f;
		} else if (velocity.x < 5f && position.y > 10 && velocity.x > 0) {
			if (velocity.x + .04f > 5f)
				velocity.x = 5f;
			else
				velocity.x += .04f;
		}

		/** Horizontal Movement **/
		position.x += velocity.x;

		if (velocity.x <= 0)
			velocity.x = 0;
	}

	/** End Update Loop **/

	public boolean touchUp(int x, int y) {
		if (x > WIDTH / 2 && y < HEIGHT / 2 && position.y < HEIGHT) {
			return true;
		}
		return false;
	}

	public boolean touchDown(int x, int y) {
		if (x > WIDTH / 2 && y > HEIGHT / 2 && position.y > 0) {
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

	public Vector2 getAcceleration() {
		return acceleration;
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

	public void setAcceleration(Vector2 accel) {
		this.acceleration.x += accel.x;
		this.acceleration.y += accel.y;
	}

	public void setVelocity(Vector2 v) {
		this.velocity.x += v.x;
		this.velocity.y += v.y;
	}
}