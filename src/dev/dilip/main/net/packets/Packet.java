package dev.dilip.main.net.packets;

import dev.dilip.main.net.Client;
import dev.dilip.main.net.Server;

public abstract class Packet {
	
	
	public enum PacketTypes{
		INVALID(-1),LOGIN(00),MOVE(01),BALL(02);
		
		int packetID;
		
		private PacketTypes(int packetID){
			this.packetID=packetID;
		}
		
		public int getID(){
			return this.packetID;
		}
		
	}
	
	byte id;
	
	public Packet(int id){
		this.id=(byte)id;
	}
	
	public abstract byte[] getData();
	public abstract void sendToClient(Server server);
	public abstract void sendToServer(Client client);
	
	public static PacketTypes LookupPackets(int ID){
		for(PacketTypes p : PacketTypes.values()){
			if(p.getID() == ID)
				return p;
		}
		return PacketTypes.INVALID;
	}
	public static PacketTypes LookupPacketsa(String id){
		return LookupPackets(Integer.parseInt(id));
	}
	
}
