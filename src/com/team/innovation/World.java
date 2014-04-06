package com.team.innovation;

import java.util.Random;

import sun.org.mozilla.javascript.internal.ast.Block;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
	
	private static final float GRAVITY = -15f; //arbitrary for now
	Array objects = new Array();
	Random rand = new Random();
	
	public World() {
		createWorld();
	}
	
	private void createWorld() {
		
	}
}
