package dev.dilip.main.net.packets;

import dev.dilip.main.net.Client;
import dev.dilip.main.net.Server;

public class Packet02Ball extends Packet{
	
	private int x,y;
	
	public Packet02Ball(int x,int y,String run) {
		super(02);
		this.x=x;
		this.y=y;
	}

	@Override
	public byte[] getData() {
		return ("02"+" " +x+" " +y+" ").getBytes();
	}

	@Override
	public void sendToClient(Server server) {
		server.sendToClient(getData());
	}

	@Override
	public void sendToServer(Client client) {
		client.sendData(getData());
	}

	public int getX() {
		return x;
	}

	public void setxMove(int x) {
		this.x = x;
	}

	public int getY() {
		return x;
	}

	public void setyMove(int y) {
		this.y = y;
	}

}
