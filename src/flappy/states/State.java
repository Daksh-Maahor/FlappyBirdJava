package flappy.states;

import java.awt.Graphics;

import flappy.Handler;

public abstract class State {
	
	protected Handler handler;

	private static State currentState = null;
	
	public State(Handler handler)
	{
		this.handler = handler;
	}

	public static void setState(State state) {

		currentState = state;

	}

	public static State getState() {

		return currentState;

	}

	// CLASS
	public abstract void tick();

	public abstract void render(Graphics g);

}
