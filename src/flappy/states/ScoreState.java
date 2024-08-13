package flappy.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import flappy.Handler;
import flappy.utils.Utils;

public class ScoreState extends State {
	

	public ScoreState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0, handler.getGame().getWidth(), handler.getGame().getHeight());
		g.setColor(Color.BLUE);
		g.setFont(new Font("Arial", Font.ITALIC, 20));
		g.drawString("You Lose", 300, 300);
		g.drawString("Score : " + Utils.toString(handler.getWorld().getEntityManager().getBird().getScore()), 300, 500);
		
	}

}
