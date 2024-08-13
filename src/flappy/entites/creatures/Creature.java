package flappy.entites.creatures;

import flappy.Handler;
import flappy.entites.Entity;
import flappy.tiles.Tile;

public abstract class Creature extends Entity {

	
	protected float speedY, speedX;

	public static final float DEFAULT_Y_SPEED = 4.0f;
	public static final float DEFAULT_X_SPEED = 5.0f;

	public static final int DEFAULT_CREATURE_WIDTH = 32, DEFAULT_CREATURE_HEIGHT = 32;

	protected float xMove, yMove;

	public Creature(Handler handler, float x, float y, int width, int height) {

		super(handler, x, y, width, height);

		speedY = DEFAULT_Y_SPEED;
		speedX = DEFAULT_X_SPEED;

		xMove = 0;
		yMove = 0;

	}

	public void move() {
		if (!checkEntityCollisions(xMove, 0f))
			moveX();
		if (!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	public abstract void attack();
	public abstract void win();

	public void moveX() {
		if (xMove > 0) { //Moving Right
			int tx = (int)(x + xMove + bounds.x + bounds.width)/Tile.TILE_WIDTH;
			if (!collisionWithTile(tx, (int)(y + bounds.y)/Tile.TILE_HEIGHT) && 
					!collisionWithTile(tx, (int)(y + bounds.y + bounds.height)/Tile.TILE_HEIGHT))
			{
				x += xMove;
			}else {
				if (!canKill(tx, (int)(y + bounds.y)/Tile.TILE_HEIGHT) && 
						!canKill(tx, (int)(y + bounds.y + bounds.height)/Tile.TILE_HEIGHT) && !willWin(tx, (int)(y + bounds.y)/Tile.TILE_HEIGHT) && 
						!willWin(tx, (int)(y + bounds.y + bounds.height)/Tile.TILE_HEIGHT)) {
					x = tx*Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
				} else {
					
					if (canKill(tx, (int)(y + bounds.y)/Tile.TILE_HEIGHT) && 
						canKill(tx, (int)(y + bounds.y + bounds.height)/Tile.TILE_HEIGHT))
					{
						if (!this.isTileImmune())
							hurt(100);
						else
							x = tx*Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
					
					}
					else if (willWin(tx, (int)(y + bounds.y)/Tile.TILE_HEIGHT) && 
						willWin(tx, (int)(y + bounds.y + bounds.height)/Tile.TILE_HEIGHT)) {
						if (!this.isTileImmune())
							win();
						else
							x = tx*Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
					}
				}
			}

		}
	}

	public void moveY() {
		if (yMove < 0)
		{ //Moving up
			int ty = (int)(y + yMove + bounds.y)/Tile.TILE_HEIGHT;
			if (!collisionWithTile((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) &&
					!collisionWithTile((int)(x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty))
			{
				y += yMove;
			}else {
				if (!canKill((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) && 
						!canKill((int)(x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty) && !willWin((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) && 
						!willWin((int)(x + bounds.x)/Tile.TILE_WIDTH, ty)) {
					y = ty*Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
				} else {
					
					if (canKill((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) && 
						canKill((int)(x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty))
					{
						if (!this.isTileImmune())
							hurt(100);
						else
							y = ty*Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
					
					}
					else if (willWin((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) && 
						willWin((int)(x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty)) {
						if (!this.isTileImmune())
							win();
						else
							y = ty*Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
					}
				}
			}
		}
		else if (yMove > 0)
		{ //Moving down
			int ty = (int)(y + yMove + bounds.y + bounds.height)/Tile.TILE_HEIGHT;
			if (!collisionWithTile((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) &&
					!collisionWithTile((int)(x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty))
			{
				y += yMove;
			}else {
				if (!canKill((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) && 
						!canKill((int)(x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty) && !willWin((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) && 
						!willWin((int)(x + bounds.x)/Tile.TILE_WIDTH, ty)) {
					y = ty*Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
				} else {
					
					if (canKill((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) && 
						canKill((int)(x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty))
					{
						if (!this.isTileImmune())
							hurt(100);
						else
							y = ty*Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
					
					}
					else if (willWin((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) && 
						willWin((int)(x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty)) {
						if (!this.isTileImmune())
							win();
						else
							y = ty*Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
					}
				}
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	protected boolean canKill(int x, int y) {
		return handler.getWorld().getTile(x, y).canKill();
	}
	
	protected boolean willWin(int x, int y) {
		return handler.getWorld().getTile(x, y).willWin();
	}

	// Getters Setters

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

}
