package com.team.innovation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Main_Game_Class implements ApplicationListener {

	SpriteBatch batch;
<<<<<<< HEAD
	Texture mT,texture;
	Player mP;	
=======
	Texture mT;
	Player mP;
>>>>>>> 566ad35d0cfc3ed8047f92cf0d1be7f7dda37941
	Vector2 position;
	Texture texture;

	@Override
	public void create() {
		batch = new SpriteBatch();
<<<<<<< HEAD
		mP = new Player(new Vector2(0, 0), "data/planeRed1.png");
		texture = new Texture(Gdx.files.internal("data/pppp.png"));
		
		}
				
			
		
=======
		mT = new Texture(Gdx.files.internal("data/testSS.png"));
		mP = new Player(new Vector2(10, 300), "data/mT.jpg");
		texture = new Texture(Gdx.files.internal("data/pppp.png"));
	}
>>>>>>> 566ad35d0cfc3ed8047f92cf0d1be7f7dda37941

	@Override
	public void dispose() {

		batch.dispose();

		mT.dispose();

	}

	@Override
<<<<<<< HEAD
	public void render() {		
=======
	public void render() {

		
>>>>>>> 566ad35d0cfc3ed8047f92cf0d1be7f7dda37941

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.draw(texture, 0, 0);

		batch.end();
<<<<<<< HEAD
		
=======

		mT.dispose();

>>>>>>> 566ad35d0cfc3ed8047f92cf0d1be7f7dda37941
		mP.update();

		batch.begin();

		batch.draw(mP.getCurrentFrame(), mP.getPosition().x, mP.getPosition().y);

		batch.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}