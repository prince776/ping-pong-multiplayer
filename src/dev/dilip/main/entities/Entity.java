package dev.dilip.main.entities;

import org.lwjgl.util.Rectangle;

import dev.dilip.main.Handler;

public abstract class Entity {
	
	protected Handler handler;
	
	protected float x,y,width,height;
	
	protected Rectangle bounds;
	
	
	public Entity(Handler handler,float x, float y , float width,float height){
		this.handler=handler;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height =height;
		bounds = new Rectangle(0,0,0,0);
	}
	
	public boolean checkEntityCollision(float xOffset,float yOffset){
		for(Entity e:handler.getGameState().getEntityManager().getEntities()){
			if(e.equals(this)){
				continue;
			}

			if(e.getCollisionBounds(0, 0).intersects(this.getCollisionBounds(xOffset,yOffset))){
				return true;
			}
			
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset,float yOffset){
		return new Rectangle( (int)(x+bounds.getX()+xOffset),(int)(y+bounds.getY()+yOffset),(int)width,(int)height);
	}
	
	public abstract void tick();
	public abstract void render();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
}
