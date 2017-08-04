package dev.dilip.main.states;

import dev.dilip.main.Handler;

public abstract class State {
	
	protected Handler handler;
	
	public State(Handler handler){
		this.handler=handler;
	}
	
	public abstract void tick();
	public abstract void render();
	
	private static State currentState;
	
	public static void setCurrentState(State s){
		currentState = s;
	}
	
	public static State getCurrentState(){
		return currentState;
	}
	
}
