package dev.dilip.main.entities;

import org.lwjgl.util.Rectangle;

import dev.dilip.main.Handler;

public abstract class Paddle extends Entity{
	
	protected byte yMove;
	public static byte DEFAULT_SPEED=6;
	protected byte speed;
	
	public float[] color;
	
	public Paddle(Handler handler, float x, float y, float width, float height,float[] color) {
		super(handler, x, y, width, height);
		bounds.setX(0);
		bounds.setY(0);
		bounds.setWidth((int)width);
		bounds.setHeight((int)height);
		this.color=color;
		this.yMove=0;
		this.speed = DEFAULT_SPEED;
	}

	public abstract void tick();

	public abstract void render();
	
	protected void move(){
		if(y+yMove>=0 && y+yMove<=handler.getHeight()-height)
			y+=yMove;
	}

	public byte getyMove() {
		return yMove;
	}

	public void setyMove(byte yMove) {
		this.yMove = yMove;
	}
	
	
	
}
