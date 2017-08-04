package dev.dilip.main.states;

import dev.dilip.main.Game;
import dev.dilip.main.Handler;
import dev.dilip.main.entities.Ball;
import dev.dilip.main.entities.EntityManager;
import dev.dilip.main.entities.NPC;
import dev.dilip.main.entities.Player;
import dev.dilip.main.gfx.Renderer;

public class GameState extends State {
	
	private Player player;
	private EntityManager entityManager;
	private NPC npc;
	public byte leftScore=0,rightScore=0;
	public GameState(Handler handler) {
		super(handler);
		this.player = new Player(handler,0,0,15,70,new float[]{0.2f,0.2f,0.2f,1f});
		entityManager = new EntityManager(player,new Ball(handler,50,50,25,25,new float[]{0,5f,0,5f,0,5f,1f}));
		npc=new NPC(handler,handler.getWidth()-15,handler.getHeight()/2,15,70,new float[]{0.2f,0.2f,0.2f,1f});
		
	}

	@Override
	public void tick() {
		entityManager.tick();
	}

	@Override
	public void render() {
		renderField();
		entityManager.render();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void renderField(){
		
		//byte leftScore = (Game.isServer)?handler.getPlayer().score:(Game.isClient)?handler.getClient().server.score:handler.getPlayer().score;
		//byte rightScore=(Game.isClient)?handler.getPlayer().score:(Game.isServer)?handler.getServer().client.score:NPC.score;
		
		if(Game.isServer){
			leftScore = handler.getPlayer().score;
			//rightScore = (byte)handler.getServer().client.getyMove();
		}if(Game.isClient){
			rightScore = handler.getPlayer().score;
			//leftScore =(handler.getClient().server.equals(null))?0:handler.getClient().server.score ;
		}
		else{
			leftScore = handler.getPlayer().score;
			rightScore = NPC.score;
		}
		Renderer.renderLine(handler.getWidth()/2, 0, handler.getWidth()/2, handler.getHeight(), new float[]{0,0,0,1});
		Renderer.renderRect(handler.getWidth()/2-50, handler.getHeight()/2-50, 100, 100, new float[]{0,0,0,1});
		Renderer.renderFont((handler.getWidth())/4,handler.getHeight()/4,leftScore+"",true);
		Renderer.renderFont((handler.getWidth()*3)/4,handler.getHeight()/4, rightScore+"",true);

	}
	
	public NPC getNpc(){
		return npc;
	}
	
}
