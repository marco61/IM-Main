package com.team.innovation;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Obstacle {

	static final float SIZE = 1f;
	Vector2 position = new Vector2();
	Rectangle rectangle = new Rectangle();
	
	public Obstacle(Vector2 position) {
		this.position = position;
		this.rectangle.width = SIZE;
		this.rectangle.height = SIZE;
	}
}
