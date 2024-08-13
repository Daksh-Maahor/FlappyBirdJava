package flappy.entites;

import java.awt.Graphics;
import java.awt.Rectangle;

import flappy.Handler;

public abstract class Entity {

	protected float x, y;
	protected int health;
	protected int width, height;
	protected Handler handler;
	protected Rectangle bounds;
	protected boolean active = true;
	protected boolean tileImmune = true;

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static final int DEFAULT_HEALTH = 10;
	
	public Entity(Handler handler, float x, float y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;

		health = DEFAULT_HEALTH;
		
		bounds = new Rectangle(0, 0, width, height);

	}
	
	public abstract void die();
	
	public void hurt(int amt)
	{
		health -= amt;
		if (health <= 0) {
			active = false;
			die();
		}
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width,
				bounds.height);
	}

	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0, 0).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}

	// Get-Set Methods
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

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

	public boolean isTileImmune() {
		return tileImmune;
	}

	public void setTileImmune(boolean tileImmune) {
		this.tileImmune = tileImmune;
	}

}
