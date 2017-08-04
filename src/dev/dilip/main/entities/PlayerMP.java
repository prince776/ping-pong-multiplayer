package dev.dilip.main.entities;

import java.net.InetAddress;

import dev.dilip.main.Handler;

public class PlayerMP extends Player{
	
	public InetAddress ipAddress;
	public int port;
	
	public PlayerMP(Handler handler, float x, float y, float width, float height, float[] color,InetAddress ipAddress , int port) {
		super(handler, x, y, width, height, color);
		this.ipAddress=ipAddress;
		this.port=port;
	}
	
	public void tick(){
		//super.tick();
	}
	
	public void render(){
		super.render();
	}
	
}
