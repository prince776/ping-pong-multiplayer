package dev.dilip.main.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import dev.dilip.main.Handler;
import dev.dilip.main.entities.Ball;
import dev.dilip.main.entities.PlayerMP;
import dev.dilip.main.net.packets.Packet;
import dev.dilip.main.net.packets.Packet00Login;
import dev.dilip.main.net.packets.Packet.PacketTypes;

public class Client extends Thread{
	
	private InetAddress ipAddress;
	private DatagramSocket socket;
	
	private Handler handler;
	private byte[] data ;
	
	public PlayerMP server = null;
	
	public Client(Handler handler,String address){
		try {
			this.handler=handler;
			this.ipAddress = InetAddress.getByName(address);
			socket = new DatagramSocket();
			data = new byte[64];
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void run() {
		while(true){
			
			DatagramPacket packet = new DatagramPacket(data,data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			parsePacket(packet.getAddress(),packet.getPort());
			
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
			
			server = new PlayerMP(handler,0,0,15,70,new float[]{0.2f,0.2f,0.2f,1f},ipAddress,port);
			handler.getPlayer().setX(handler.getWidth()-15);
			handler.getPlayer().setY(handler.getHeight()/2);

			handler.getGameState().getEntityManager().addEntity(server);
			
			break;
		case MOVE:
			String[] data = message.split("\\s+");
			int yMove = Integer.parseInt(data[1]);
			
			server.setyMove((byte)yMove);
			break;
		case BALL:
			String[] d = message.split("\\s+");
			int xM = Integer.parseInt(d[1]);
			int yM = Integer.parseInt(d[2]);
			
			Ball.run=true;
			handler.getGameState().getEntityManager().getBall().setX(xM);
			handler.getGameState().getEntityManager().getBall().setY(yM);
			break;
		}
	}

	public void sendData(byte[] data){
		DatagramPacket packet= new DatagramPacket(data,data.length,ipAddress,Server.port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
