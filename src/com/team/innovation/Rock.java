package com.team.innovation;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
public class Rock {
	float xAxis;
	float yAxis;
	float radius = 94f;
	private Circle rock;
	private Player bob;
	private CollisionHelpers collide;
	Vector2 velocity = new Vector2();
	Vector2 position = new Vector2();
	String TextureLoc;
	public Rock(float xAxis, float yAxis, float radius){
		rock = new Circle();
		rock.set(xAxis, yAxis, radius);
		bob = new Player(position, TextureLoc);
		if(collide.collidesWithCircles(rock,bob.getCircle())){
			bob.setVelocity(velocity.set(-20f, -20f));
		}
	}
}