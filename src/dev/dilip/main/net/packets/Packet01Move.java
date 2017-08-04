package dev.dilip.main.net.packets;

import dev.dilip.main.net.Client;
import dev.dilip.main.net.Server;

public class Packet01Move extends Packet{
	
	int yMove;
	
	public Packet01Move(int yMove) {
		super(01);
		this.yMove=yMove;
	}

	@Override
	public byte[] getData() {
		return ("01"+" " +yMove+ " ").getBytes();
	}

	@Override
	public void sendToClient(Server server) {
		server.sendToClient(getData());
	}

	@Override
	public void sendToServer(Client client) {
		client.sendData(getData());
	}

	public int getyMove() {
		return yMove;
	}

	public void setyMove(int yMove) {
		this.yMove = yMove;
	}

}
