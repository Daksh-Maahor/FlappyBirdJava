package flappy.entites;

import java.awt.Graphics;
import java.util.ArrayList;

import flappy.Handler;
import flappy.entites.creatures.Bird;

public class EntityManager {
	
	private Handler handler;
	private Bird bird;
	private ArrayList<Entity> entities;
	
	public EntityManager(Handler handler, Bird bird)
	{
		this.handler = handler;
		this.bird = bird;
		entities = new ArrayList<Entity>();
		entities.add(bird);
	}
	
	public void tick()
	{
		for (int i =0; i< entities.size(); i++)
		{
			Entity e = entities.get(i);
			e.tick();
			if (!e.isActive())
				entities.remove(e);
		}
		
	}
	
	public void render(Graphics g)
	{
		for (Entity e : entities)
		{
			e.render(g);
		}
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	//Gettes Settes

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Bird getBird() {
		return bird;
	}

	public void setBird(Bird bird) {
		this.bird = bird;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
