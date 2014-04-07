package com.team.innovation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class WorldRender {
	private World world;
	private OrthographicCamera cam;

	/** DEBUG **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	public WorldRender(World world) {
		this.world = world;
		cam = new OrthographicCamera(10, 7);
		cam.position.set(5, 3.5f, 0);
		cam.update();
	}

	public void render() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		for (Obstacle obstacle : world.getArray()) {
			Rectangle rectangle = obstacle.getBounds();
			float x1 = obstacle.getPosition().x + rectangle.x;
			float y1 = obstacle.getPosition().y + rectangle.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rectangle.width, rectangle.height);
			debugRenderer.end();
		}
	}

}
