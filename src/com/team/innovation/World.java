package com.team.innovation;

import java.util.Random;

import sun.org.mozilla.javascript.internal.ast.Block;

import com.badlogic.gdx.math.Vector2;
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
		for (int i = 0; i < rand.nextInt(6); i++) {
			objects.add(new Obstacle(rand.nextInt(10), rand.nextInt()));
		}
		objects.add(new Obstacle(rand.nextInt(10), rand.nextInt(7)));
	}
}
