package dev.dilip.main.entities;

import dev.dilip.main.Handler;
import dev.dilip.main.gfx.Renderer;

public class NPC extends Paddle{
	
	public static byte score=0;
	
	public NPC(Handler handler, float x, float y, float width, float height,float[] color) {
		super(handler, x, y, width, height, color);
		speed=(byte) (DEFAULT_SPEED/2);
	}

	@Override
	public void tick() {
		chaseBall();
		move();
	}

	@Override
	public void render() {
		Renderer.renderQuad(x, y, width, height,color);
		Renderer.renderRect(x, y, width, height, color);
	}
	
	public void chaseBall(){
		yMove=0;
		if(handler.getBall().getY()>y+5) yMove=speed;
		
		if(handler.getBall().getY()<y-5) yMove=(byte) -speed;
		
	}
	

}
