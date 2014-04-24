package com.team.innovation;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class World {

	Array<Obstacle> objects = new Array<Obstacle>();
	Random rand = new Random();
	Array<String> objTypes = new Array<String>();

	public World() {
		createWorld();
	}

	public Array<Obstacle> getArray() {
		return objects;
	}

	public Array<String> getTypes() {
		return objTypes;
	}

	private void createWorld() {
		int z = 4;
		for (int i = 0; i < 1000; i++) {
			objects.add(new Obstacle(rand.nextInt(Gdx.graphics.getWidth() * z),
					rand.nextInt(Gdx.graphics.getHeight())));
			z++;
		}
		for (int i = 0; i < 1000; i++) {
			if (rand.nextInt(100) < 95)
				objTypes.add("ROCK");
			else
				objTypes.add("STAR");
		}
	}
}
