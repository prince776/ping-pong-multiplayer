package dev.dilip.main.net.packets;

import dev.dilip.main.net.Client;
import dev.dilip.main.net.Server;

public class Packet00Login extends Packet {
	
	private String username;
	
	public Packet00Login(String username) {
		super(00);
		this.username=username;
	}

	@Override
	public byte[] getData() {
		return ("00"+ " " +username+ " ").getBytes();
	}

	@Override
	public void sendToClient(Server server) {
		server.sendToClient(getData());
	}

	@Override
	public void sendToServer(Client client) {
		client.sendData(getData());
	}

}
