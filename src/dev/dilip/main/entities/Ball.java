package dev.dilip.main.entities;

import dev.dilip.main.Game;
import dev.dilip.main.Handler;
import dev.dilip.main.gfx.Renderer;
import dev.dilip.main.net.packets.Packet02Ball;

public class Ball extends Entity{
	
	private byte xMove=0,yMove=0,speed=3,maxSpeed=7,DEFAULT_SPEED=3;
	
	private float[] color;
	private float initialX=0,initialY=0;
	public static boolean run=false;
	private boolean sendData=true;
	
	private String winner="";
	
	private Packet02Ball packet;
	
	public Ball(Handler handler, float x, float y, float width, float height,float[] color) {
		super(handler, x, y, width, height);
		this.initialX = handler.getWidth()/2-width/2;
		this.initialY = handler.getHeight()/2-height/2;
		this.x=initialX;
		this.y = initialY;
		this.color=color;
		if(!Game.isClient){
			this.xMove=speed;
			this.yMove=speed;
		}if(Game.isClient){
			run=true;
		}
		packet = new Packet02Ball(0,0,(run)?"true":"false");
	}

	@Override
	public void tick() {
		
		if(run){
			sendMoveData();
			moveX();
			moveY();
		}else{
			speed = DEFAULT_SPEED;
			x=initialX;
			y=initialY;
			
		}
	}
	
	public void sendMoveData(){
		if(Game.isServer && sendData){
			packet.setxMove((int) x);
			packet.setyMove((int) y);
			packet.sendToClient(handler.getServer());
		}
	}
	
	@Override
	public void render() {
		Renderer.renderQuad(x, y, width, height, color);
		Renderer.renderRect(x, y, width, height, color);
		if(!run)
		Renderer.renderFont(initialX, initialY, winner, true);
	}
	
	private void moveX(){
		if(x+xMove <=0){
			xMove=speed;
			x=initialX;
			y=initialY;
			speed=DEFAULT_SPEED;
			run=false;
			
			if(Game.isServer){
				
				handler.getServer().client.score++;
				handler.getGameState().rightScore++;

			}
			else if(Game.isClient){
				handler.getPlayer().score++;
			}if(!Game.isServer && !Game.isClient){
				NPC.score++;
			}
			
		}
		if(x+xMove>=handler.getWidth() - width){
			xMove=(byte) -speed;
			x=initialX;
			y=initialY;
			speed=DEFAULT_SPEED;
			run=false;
			
			if(Game.isServer){
				handler.getPlayer().score++;
				
			}
			
			else if(Game.isClient){
				handler.getGameState().leftScore++;
				handler.getClient().server.score++;
				
			}if(!Game.isServer && !Game.isClient){
				handler.getPlayer().score++;
			}
			

		}
		if(checkEntityCollision(xMove,0)){
			xMove=(byte) -xMove;
			speed+=1;
			if(speed>=7)
				speed=7;
		}
		x+=xMove;
	}
	private void moveY(){
		
		if(y+yMove <=0){
			yMove=speed;
		}
		if(y+yMove>=handler.getHeight() - height){
			yMove=(byte) -speed;
		}
		if(checkEntityCollision(0,yMove)){
			speed+=1;
			if(speed>=7)
				speed=7;
			yMove=(byte) -yMove;
		}
		y+=yMove;
	}

	public byte getxMove() {
		return xMove;
	}

	public void setxMove(byte xMove) {
		this.xMove = xMove;
	}

	public byte getyMove() {
		return yMove;
	}

	public void setyMove(byte yMove) {
		this.yMove = yMove;
	}

	
	
}
