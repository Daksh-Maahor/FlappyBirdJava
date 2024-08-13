package flappy.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import flappy.Handler;

public class CreditState extends State {

	public CreditState(Handler handler) {
		
		super(handler);

	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.magenta);
		g.fillRect(0, 0, handler.getGame().getWidth(), handler.getGame().getHeight());
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.ITALIC, 20));
		g.drawString("Made By Daksh Maahor", 300, 300);

	}

}
