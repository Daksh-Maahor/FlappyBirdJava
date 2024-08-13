package flappy.worlds;

import java.awt.Font;
import java.awt.Graphics;

import flappy.Handler;
import flappy.entites.EntityManager;
import flappy.entites.creatures.Bird;
import flappy.entites.creatures.EvilBird;
import flappy.scores.Score;
import flappy.tiles.Tile;
import flappy.utils.Utils;

public class World {

	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private Handler handler;
	private Score score;
	
	private EntityManager entityManager;

	public World(Handler handler, String path) {

		this.handler = handler;
		
		score = new Score(handler, new Font("Arial", Font.ITALIC, 20), handler.getGame().getWidth()/2, 200);
		
		entityManager = new EntityManager(handler, new Bird(handler, 100, 100));
		entityManager.addEntity(new EvilBird(handler, 100, 100)); //6400 max x value
		entityManager.addEntity(new EvilBird(handler, 500, 100));
		entityManager.addEntity(new EvilBird(handler, 1000, 100));
		entityManager.addEntity(new EvilBird(handler, 1500, 100));
		entityManager.addEntity(new EvilBird(handler, 2000, 100));
		entityManager.addEntity(new EvilBird(handler, 2500, 100));
		entityManager.addEntity(new EvilBird(handler, 3000, 100));
		entityManager.addEntity(new EvilBird(handler, 3500, 100));
		entityManager.addEntity(new EvilBird(handler, 4000, 100));
		entityManager.addEntity(new EvilBird(handler, 4500, 100));
		entityManager.addEntity(new EvilBird(handler, 5000, 100));
		entityManager.addEntity(new EvilBird(handler, 5500, 100));
		entityManager.addEntity(new EvilBird(handler, 6000, 100));
		
		loadWorld(path);
		
		entityManager.getBird().setX(spawnX);
		entityManager.getBird().setY(spawnY);

	}

	public void tick() {
		
		score.tick();
		entityManager.tick();
		

	}

	public void render(Graphics g) {
		
		int xStart = (int)Math.max(0,(handler.getGameCamera().getxOffset()/Tile.TILE_WIDTH));
		int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth())/Tile.TILE_WIDTH+1);
		int yStart = (int)Math.max(0,(handler.getGameCamera().getyOffset()/Tile.TILE_HEIGHT));
		int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight())/Tile.TILE_HEIGHT+1);
		
		for (int y =yStart; y<yEnd; y++)
		{
			for (int x=xStart; x<xEnd; x++)
			{
				getTile(x, y).render(g, (int) (x*Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), 
						(int)(y*Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		score.render(g);
		
		entityManager.render(g);

	}
	
	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

	public Tile getTile(int x, int y)
	{
		if (x<0 || y<0 || x>=width || y>=height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.grassTile;
		return t;
	}

	private void loadWorld(String path) {
		
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		
		for (int y =0; y<height; y++)
		{
			for (int x=0; x<width; x++)
			{
				tiles[x][y] = Utils.parseInt(tokens[(x+y*width)+4]);
			}
		}

	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
