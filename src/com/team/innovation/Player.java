package com.team.innovation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {

	Vector2 position;
	Vector2 velocity = new Vector2();
	Vector2 acceleration = new Vector2();
	String textureLoc;

	private static final int col = 1;
	private static final int row = 1;

	private static final float GRAVITY = -15f; //arbitrary for now

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

		animation = new Animation(1f, frames);
		stateTime = 0f;
		currentFrame = animation.getKeyFrame(0);
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

	public void update() {
		/**DEBUG 
		float x = this.position.x;
		float y = this.position.y;
		System.out.print("(" + x + ", ");
		System.out.println(y + ")");
		**/
		if (stateTime < 8) {
			stateTime += Gdx.graphics.getDeltaTime();
		} else {
			stateTime = 0;
		}
		acceleration.y = GRAVITY;
		stateTime += Gdx.graphics.getDeltaTime();

		
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
		

		float accelX = Gdx.input.getAccelerometerX();
		float accelY = Gdx.input.getAccelerometerY();

		if (accelX < -1) {
			position.y -= accelX;
			currentFrame = animation.getKeyFrame(8 + stateTime);
		}
	
		if (accelX > +1) {
			position.y -= accelX;
			currentFrame = animation.getKeyFrame(0 + stateTime);
		}
		
		if (accelY < -1) {
			position.x += accelY;
			currentFrame = animation.getKeyFrame(16 + stateTime);
		}
		
		if (accelY > +1) {
			position.x += accelY;
			currentFrame = animation.getKeyFrame(24 + stateTime);
		}

		if (accelX > 3) {
			System.out.println("x axis is " + accelX);
		}

		if (accelX < -3) {
			System.out.println("x axis is " + accelX);
		}

		if (accelY > 3) {
			System.out.println("y axis is " + accelX);
		}

		if (accelY < -3) {
			System.out.println("y axis is " + accelX);
		}

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

}