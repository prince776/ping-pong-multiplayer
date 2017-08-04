package dev.dilip.main.entities;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityManager {
	
	private ArrayList<Entity> entities;
	private Iterator<Entity> iterator;
	private Player player;
	private Ball ball;
	
	public EntityManager(Player player,Ball ball){
		
		this.player=player;
		this.ball=ball;
		this.entities=new ArrayList<Entity>();
		this.iterator = entities.iterator();
		entities.add(player);
		entities.add(ball);

	}
	
	public void tick(){
		iterator=entities.iterator();
		while(iterator.hasNext()){
			Entity e = iterator.next();
			e.tick();
		}
	}
	
	public void render(){
		iterator=entities.iterator();
		while(iterator.hasNext()){
			Entity e = iterator.next();
			e.render();
		}		
	}
	
	public void addEntity(Entity e){
		entities.add(e);
		iterator=entities.iterator();
	}
	
	//GETTERS AND SETTERS
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}
	
	
	
	
}
