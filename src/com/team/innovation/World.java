package com.team.innovation;

import java.util.Random;

import com.badlogic.gdx.utils.Array;

public class World {
	
	private static final float GRAVITY = -15f; //arbitrary for now
	Array<Obstacle> objects = new Array<Obstacle>();
	Random rand = new Random();
	
	public World() {
		createWorld();
	}
	
	public Array<Obstacle> getArray() {
		return objects;
	}
	
	private void createWorld() {
		for (int i = 0; i < 1000; i++) {
			objects.add(new Obstacle(rand.nextInt(10), rand.nextInt(10)));
		}
	}
}
