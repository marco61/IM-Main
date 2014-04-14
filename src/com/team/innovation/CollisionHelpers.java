package com.team.innovation;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionHelpers {
	  public static final Rectangle rectA = new Rectangle();
	  public static final Rectangle rectB = new Rectangle();
	  public static final Circle circA = new Circle();
	  public static final Circle circB = new Circle();
	  
	  public static boolean collidesWithCircles(Circle entityA, Circle entityB){
		  return circA.overlaps(circB);
	  }
	  
	  public static boolean collidesRectangles(Rectangle entityA, Rectangle entityB) {
	      return rectA.overlaps(rectB);
	   }
	  
	   public static boolean RectangleCircleCollision(Circle circleEntity, Rectangle rectangleEntity){
		   	  boolean collide = false;
		      float circleDistanceX = Math.abs(circA.x - rectA.x - rectA.width/2);
		      float circleDistanceY = Math.abs(circA.y - rectA.y - rectA.height/2);
		      
		      if(circleDistanceX > (rectA.width/2 + circA.radius) || circleDistanceY > (rectA.height/2 + circA.radius)){
		         collide = false;
		      }
		      if(circleDistanceX <= (rectA.width/2) || circleDistanceX <= (rectA.height/2)){
		         collide = true;
		      }
		      return collide;
		   }
	   }
