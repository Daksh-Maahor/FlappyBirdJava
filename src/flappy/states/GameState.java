package flappy.states;

import java.awt.Graphics;

import flappy.Handler;
import flappy.worlds.World;

public class GameState extends State {
	
	private World world;

	public GameState(Handler handler) {
		
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);

	}

	@Override
	public void tick() {
		
		world.tick();
		
	}

	@Override
	public void render(Graphics g) {
		
		world.render(g);
		
	}

}
