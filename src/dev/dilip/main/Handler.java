package dev.dilip.main;

import dev.dilip.main.entities.Ball;
import dev.dilip.main.entities.Player;
import dev.dilip.main.net.Client;
import dev.dilip.main.net.Server;
import dev.dilip.main.states.GameState;

public class Handler {
	public Game game;
	
	public Handler(Game game){
		this.game=game;
	}
	public int getWidth(){
		return game.getWidth();
	}
	public int getHeight(){
		return game.getHeight();
	}
	
	public GameState getGameState(){
		return this.game.getGameState();
	}
	public Ball getBall(){
		return game.getGameState().getEntityManager().getBall();
	}
	
	public Player getPlayer(){
		return game.getGameState().getEntityManager().getPlayer();
	}
	
	public Client getClient(){
		return game.client;
	}
	
	public Server getServer(){
		return game.server;
	}
	
}
