package com.team.innovation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Obstacle {

	static final float SIZE = 1f;
	Vector2 position = new Vector2();
	Rectangle rectangle = new Rectangle();
	Texture obstacleTexture;
	TextureRegion[] frames;
	TextureRegion currentFrame;
	
	public Rectangle getBounds() {
		return rectangle;
	}
	
	public Obstacle(Vector2 position) {
		obstacleTexture = new Texture(Gdx.files.internal("data/sw.png")); //needs actual asset
		TextureRegion[][] tmp = TextureRegion
				.split(obstacleTexture, obstacleTexture.getWidth(),
						obstacleTexture.getHeight());
		this.position = position;
		this.rectangle.width = SIZE;
		this.rectangle.height = SIZE;
	}

	public Vector2 getPosition() {
		return position;
	}
}
