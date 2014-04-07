package com.team.innovation;

import java.io.IOException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Main_Game_Class implements ApplicationListener {
	
	SpriteBatch batch;
	Texture mT;
	Player mP;	
	Vector2 position;
	World world;
	WorldRender worldRender;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		mT = new Texture(Gdx.files.internal("data/testSS.png"));
		world = new World();
		worldRender = new WorldRender(world);
		
		if(Gdx.files.local("player.dat").exists()){
			try {
				mP = new Player(new Vector2(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() /2), "data/mT.jpg");
				mP.setPosition(Player.readPlayer());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("File exists, reading file");
		}else{
				mP = new Player(new Vector2(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() /2), "data/mT.jpg");
				try {
					Player.savePlayer(mP);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Player does not exist, creating and saving player.");
			}
		}
				
			
		

	@Override
	public void dispose() {
		try {
			Player.savePlayer(mP);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void render() {		
		
		Texture texture = new Texture(Gdx.files.internal("data/pppp.png"));

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