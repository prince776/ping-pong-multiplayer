package dev.dilip.main;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import dev.dilip.main.display.DisplayClass;
import dev.dilip.main.gfx.Renderer;
import dev.dilip.main.net.Client;
import dev.dilip.main.net.Server;
import dev.dilip.main.net.packets.Packet00Login;
import dev.dilip.main.states.GameState;
import dev.dilip.main.states.State;

public class Game {
	
	private int width,height;
	private String title;
	
	private DisplayClass displayClass;
	
	private Handler handler;
	
	private GameState gameState;
	
	public Server server;
	public Client client;
	
	public boolean run=false;
	public static boolean isServer=false,isClient=false;
	public Game(String title,int width,int height){
		
		this.width=width;
		this.height=height;
		this.title=title;
		init();
		
	}
	
	public void init(){
		displayClass=new DisplayClass(title,width,height);
		Renderer.init();
		handler = new Handler(this);
		gameState = new GameState(handler);
		State.setCurrentState(gameState);
	}
	
	
	public void run(){
		
		long lastTime=System.nanoTime();
		long now;
		double delta=0;
		int fps=60;
		long timer=0;
		int frames=0,ticks=0;
		
		double nsPerTick = 1000000000/fps;
		
		if(JOptionPane.showConfirmDialog(null, "DO YOU WANT TO RUN THE SERVER?", "QUERY",1)==0){
			server = new Server(handler);
			handler.getGameState().getEntityManager().getPlayer().username =JOptionPane.showInputDialog(null, "Enter Username:");
			server.start();
			run=true;
			isServer=true;
		}else{
			if(JOptionPane.showConfirmDialog(null, "DO YOU WANT TO JOIN THE SERVER?", "QUERY",1)==0){
				client = new Client(handler,JOptionPane.showInputDialog(null, "Enter IpAddress"));
				client.start();
				run=true;
				isClient=true;
				handler.getPlayer().setUsername(JOptionPane.showInputDialog(null, "Enter Username:"));
				Packet00Login packet = new Packet00Login(handler.getPlayer().getUsername());
				packet.sendToServer(client);
				handler.getPlayer().color= new float[]{0.3f,0.3f,0.3f,1f};

			}else{
				run=false;
			}
			
		}
		if(!isClient&&!isServer)
			gameState.getEntityManager().addEntity(gameState.getNpc());
		run=true;
		
		while(!Display.isCloseRequested()){
			if(run){
				now = System.nanoTime();
				delta +=(now-lastTime)/nsPerTick;
				timer += now - lastTime;
				lastTime=now;
				
				if(delta>=1){
					tick();
					ticks++;
					delta--;
				}
				
				
				render();
				frames++;
				
				if(timer>=1000000000){
					Display.setTitle(title+" FPS: "+frames+" UPS: "+ticks);
					ticks=0;
					frames=0;
					timer=0;
				}
				
				Display.update();
			}
		}Display.destroy();System.exit(1);
	}

	private void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1,1,1,1);
		if(State.getCurrentState()!=null){
			State.getCurrentState().render();
		}
	}

	private void tick() {
		if(State.getCurrentState() !=null){
			State.getCurrentState().tick();
		}
	}

	
	//GETTERS AND SETTERS
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public GameState getGameState() {
		return gameState;
	}	
	
}
