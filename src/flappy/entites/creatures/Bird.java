package flappy.entites.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import flappy.Handler;
import flappy.gfx.Animation;
import flappy.gfx.Assets;

public class Bird extends Creature {
	
	private Animation animFly;
	private Animation animFall;
	private int score;
	private final int score_Factor = 10;

	public Bird(Handler handler, float x, float y) {

		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		animFly = new Animation(500, Assets.bird_flying);
		animFall = new Animation(500, Assets.bird_falling);
		
		this.setTileImmune(false);
		score = 0;

	}

	@Override
	public void tick() {
		
		animFly.tick();
		animFall.tick();
		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);

	}
	
	private void getInput()
	{
		xMove = 0;
		yMove = 0;
		
		if (handler.getKeyManager().jump)
			yMove = -speedY;
		else
			yMove = speedX;
		
		xMove = speedX;
		speedX+=.01;
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(getCurrentAnimFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

	}
	
	private BufferedImage getCurrentAnimFrame()
	{
		if (yMove > 0)
			return animFall.getCurrentFrame();
		else
			return animFly.getCurrentFrame();
	}
	
	public void die()
	{
		score = (int) (x/score_Factor);
		System.out.println("You Died");
		System.out.println("Score : " + score);
		handler.getGame().setState(handler.getGame().scoreState);
	}

	@Override
	public void attack() {
		
	}
	
	public int getScore()
	{
		return score;
	}

	public int getScore_Factor() {
		return score_Factor;
	}

	@Override
	public void win() {
		System.out.println("You Won");
		handler.getGame().setState(handler.getGame().winState);
		
	}

}
