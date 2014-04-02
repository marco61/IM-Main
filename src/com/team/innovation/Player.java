package com.team.innovation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	Vector2 position;
	Vector2 velocity = new Vector2();
	Vector2 acceleration = new Vector2();
	String textureLoc;

	private static final int col = 4;
	private static final int row = 4;
<<<<<<< HEAD
	private static final float GRAVITY = -15f; //arbitrary for now
=======
	private static final float GRAVITY = -15f;
>>>>>>> abc6a19a441a922b81c01489c2484320d229123a

	Animation animation;
	Texture playerTexture;
	TextureRegion[] frames;
	TextureRegion currentFrame;
	float stateTime;

	public Player(Vector2 position, String textureLoc) {
		this.position = position;

		playerTexture = new Texture(Gdx.files.internal("data/dgbh.png"));
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

		if (Gdx.input.isTouched()) {
			System.out.println("Touched!");
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

	public static void savePlayer(Player playerPosition) throws IOException {
		FileHandle file = Gdx.files.local("player.dat");
		OutputStream out = null;
		try {
			file.writeBytes(serialize(playerPosition.getPosition()), false);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception ex) {
				}
		}

		System.out.println("Saving Player");
	}

	public static Vector2 readPlayer() throws IOException,
			ClassNotFoundException {
		Vector2 playerPosition = null;
		FileHandle file = Gdx.files.local("player.dat");
		playerPosition = (Vector2) deserialize(file.readBytes());
		return playerPosition;
	}

	@SuppressWarnings("unused")
	private static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(obj);
		return b.toByteArray();
	}

	public static Object deserialize(byte[] bytes) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		ObjectInputStream o = new ObjectInputStream(b);
		return o.readObject();
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