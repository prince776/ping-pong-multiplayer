package dev.dilip.main.entities;

import org.lwjgl.input.Keyboard;

import dev.dilip.main.Game;
import dev.dilip.main.Handler;
import dev.dilip.main.gfx.Renderer;
import dev.dilip.main.net.packets.Packet01Move;

public class Player extends Paddle {
	
	public  byte score=0;
	public String username;
	
	private Packet01Move packet;
	
	public Player(Handler handler, float x, float y, float width, float height,float[] color) {
		super(handler, x, y, width, height,color);
		packet = new Packet01Move(0);
	}

	@Override
	public void tick() {
		getInput();
		move();
		sendMoveData();
	}
	
	public void sendMoveData(){
		if(Game.isClient){
			packet.setyMove(yMove);
			packet.sendToServer(handler.getClient());
		}
		else if(Game.isServer){
			packet.setyMove(yMove);
			packet.sendToClient(handler.getServer());
		}
	}
	
	@Override
	public void render() {
		//Renderer.renderFont(x, y-30, username, true);
		Renderer.renderQuad(x, y, width, height,color);
		Renderer.renderRect(x, y, width, height, color);

	}
	
	public void getInput(){
		yMove=0;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			yMove = (byte) -speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			yMove = (byte) speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
			if(!Game.isClient)
			Ball.run=true;
		}
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
