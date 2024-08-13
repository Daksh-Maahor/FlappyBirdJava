package flappy.tiles;

import flappy.gfx.Assets;

public class WinTile extends Tile {
	
	public WinTile(int id)
	{
		
		super(Assets.win, id);
		
	}
	
	@Override
	public boolean willWin()
	{
		return true;
	}
	
	public boolean isSolid()
	{
		return true;
	}

}
