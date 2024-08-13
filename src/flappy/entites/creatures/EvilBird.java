package flappy.entites.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import flappy.Handler;
import flappy.gfx.Animation;
import flappy.gfx.Assets;

public class EvilBird extends Creature {
	
	private Animation animFly;
	private int trial;

	public EvilBird(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		animFly = new Animation(500, Assets.evil_flying);
		trial = 0;
		
		this.setTileImmune(true);
	}

	@Override
	public void tick() {
		animFly.tick();
		
		hover();
		move();
		attack();
	}
	
	private void hover()
	{
		yMove = 0;
		
		if ((trial%300)-150 >= 0)
			yMove = speedY;
		else
			yMove = -speedY;
		
		trial++;
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(animFly.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
	}

	@Override
	public void die() {
		
	}

	@Override
	public void attack() {
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle arU = new Rectangle();
		Rectangle arD = new Rectangle();
		Rectangle arL = new Rectangle();
		Rectangle arR = new Rectangle();
		int arSize = 20;
		
		arU.width = arSize;
		arU.height = arSize;
		
		arD.width = arSize;
		arD.height = arSize;
		
		arL.width = arSize;
		arL.height = arSize;
		
		arR.width = arSize;
		arR.height = arSize;
		
		arU.x = cb.x + cb.width/2 - arSize/2;
		arU.y = cb.y - arSize;
		
		arD.x = cb.x + cb.width/2 - arSize/2;
		arD.y = cb.y + cb.height;
		
		arL.x = cb.x - arSize;
		arL.y = cb.y + cb.height / 2 - arSize / 2;
		
		arR.x = cb.x + cb.width;
		arR.y = cb.y + cb.height / 2 - arSize / 2;
		
		if ((handler.getWorld().getEntityManager().getBird().getCollisionBounds(0, 0).intersects(arU)) || (handler.getWorld().getEntityManager().getBird().getCollisionBounds(0, 0).intersects(arD)) || (handler.getWorld().getEntityManager().getBird().getCollisionBounds(0, 0).intersects(arL)) || (handler.getWorld().getEntityManager().getBird().getCollisionBounds(0, 0).intersects(arR)))
			handler.getWorld().getEntityManager().getBird().hurt(100);
		
	}

	@Override
	public void win() {
		// TODO Auto-generated method stub
		
	}

}
 