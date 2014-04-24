package com.team.innovation;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
public class Star {
float xAxis;//add the values from google doc.
float yAxis;
float radius = 90f ;
private Circle star;
private Player bob;
private CollisionHelpers collide;
Vector2 acceleration = new Vector2();
Vector2 position = new Vector2();
String TextureLoc;
	public Star(float xAxis, float yAxis, float radius){
		star = new Circle();
		star.set(xAxis, yAxis, radius);
		bob = new Player(position, TextureLoc);
//		if(collide.collidesWithCircles(star,bob.getCircle())){
//			bob.setVelocity(acceleration.set(8f, 8f));
//		}
	}
}