package flappy.tiles;

import flappy.gfx.Assets;

public class PipeTile extends Tile {
	
	public PipeTile(int id)
	{
		
		super(Assets.pipe, id);
		
	}
	
	@Override
	public boolean isSolid()
	{
		
		return true;
		
	}
	
	@Override
	public boolean canKill()
	{
		return true;
	}

}
