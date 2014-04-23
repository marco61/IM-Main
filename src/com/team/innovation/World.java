package com.team.innovation;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class World {
	
	Array<Obstacle> objects = new Array<Obstacle>();
	Random rand = new Random();
	
	public World() {
		createWorld();
	}
	
	public Array<Obstacle> getArray() {
		return objects;
	}
	
	private void createWorld() {
		int z = 4;
		for (int i = 0; i < 1000; i++) {
			objects.add(new Obstacle(rand.nextInt(Gdx.graphics.getWidth() * z), rand.nextInt(Gdx.graphics.getHeight())));
			z++;
		}
	}
}
