package dev.dilip.main.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import dev.dilip.main.Handler;
import dev.dilip.main.entities.PlayerMP;
import dev.dilip.main.net.packets.Packet;
import dev.dilip.main.net.packets.Packet.PacketTypes;
import dev.dilip.main.net.packets.Packet00Login;

public class Server extends Thread{
	public static int port = 1936;
	
	private DatagramSocket socket;
	private Handler handler;
	
	public PlayerMP client=null;
	
	private byte[] data;
	
	public Server(Handler handler){
		this.handler=handler;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		data = new byte[64];

	}
	
	public void run(){
		
		while(true){
			DatagramPacket packet = new DatagramPacket(data,data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			parsePacket(packet.getAddress(), packet.getPort());
		}
	}
	
	public void parsePacket(InetAddress ipAddress, int port){
		String message = new String(data).trim();
		String id = message.split("\\s+")[0];
		PacketTypes type = Packet.LookupPacketsa(id);
		
		switch(type){
		default:
			break;
		case INVALID:
			break;	
		case LOGIN:
			
			Packet00Login login = new Packet00Login(handler.getPlayer().getUsername());
			
			client = new PlayerMP(handler,handler.getWidth()-15,handler.getHeight()/2,15,70,new float[]{0.3f,0.3f,0.3f,1f},ipAddress,port);
			handler.getGameState().getEntityManager().addEntity(client);
			
			login.sendToClient(this);
			break;
		case MOVE:
			
			String[] data = message.split("\\s+");
			int yMove = Integer.parseInt(data[1]);
			
			client.setyMove((byte)yMove);
			
			break;
			
		}
	}
	
	public void sendData(byte[] data , InetAddress ipAddress,int port){
		DatagramPacket packet = new DatagramPacket(data,data.length,ipAddress,port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendToClient(byte[] data){
		if(client !=null)
		sendData(data, client.ipAddress, client.port);
	}
}
