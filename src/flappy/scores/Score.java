package flappy.scores;

import java.awt.Font;
import java.awt.Graphics;

import flappy.Handler;
import flappy.utils.Utils;

public class Score {
	
	private Handler handler;
	private int x, y;
	private int score;
	private Font font;
	
	public Score(Handler handler, Font font, int x, int y)
	{
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.font = font;
		score = 0;
	}
	
	public void tick()
	{
		score = (int) handler.getWorld().getEntityManager().getBird().getX();
	}
	
	public void render(Graphics g)
	{
		g.setFont(font);
		g.drawString(Utils.toString((int)score/handler.getWorld().getEntityManager().getBird().getScore_Factor()), x, y);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
