package com.team.innovation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Main_Game_Class implements ApplicationListener {
	
	SpriteBatch batch;
	Texture mT,texture;
	Player mP;	
	Vector2 position;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		mP = new Player(new Vector2(0, 0), "data/planeRed1.png");
		texture = new Texture(Gdx.files.internal("data/pppp.png"));
		
		}
				
			
		

	@Override
	public void dispose() {
		
		batch.dispose();
		
		mT.dispose();
			
	}

	@Override
	public void render() {		

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); 
	     
		batch.begin();
	     
		batch.draw(texture, 0, 0);
	     
		batch.end();
		
		mP.update();
		
		batch.begin();
		
		batch.draw(mP.getCurrentFrame(), mP.getPosition().x , mP.getPosition().y);
		
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