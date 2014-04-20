package com.team.innovation;
import com.badlogic.gdx.math.Circle;
public class Star {
float x;
float y;
float radius;
private Circle star;
private Player bob;
private CollisionHelpers collide;
	public Star(float x, float y, float radius){
		star = new Circle();
		star.set(x, y, radius);
		bob = new Player(null, null);
		if(collide.collidesWithCircles(star,bob.getCircle())){
		
		}
	}
}