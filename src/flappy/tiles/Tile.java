package flappy.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//Static Stuff
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile pipeTile = new PipeTile(1);
	public static Tile winTile = new WinTile(2);
	
	//Class
	
	public static final int TILE_WIDTH = 32,
							TILE_HEIGHT = 32;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id)
	{
		
		this.id = id;
		this.texture = texture;
		
		tiles[id] = this;
		
	}
	
	public int getId()
	{
		return id;
	}
	
	public boolean isSolid()
	{
		
		return false;
		
	}
	
	public boolean canKill()
	{
		
		return false;
		
	}
	
	public boolean willWin()
	{
		
		return false;
		
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}

}
