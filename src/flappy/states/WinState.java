package flappy.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import flappy.Handler;

public class WinState extends State {

	public WinState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.PINK);
		g.fillRect(0, 0, handler.getGame().getWidth(), handler.getGame().getHeight());
		g.setColor(Color.BLUE);
		g.setFont(new Font("Arial", Font.ITALIC, 20));
		g.drawString("You Won", 300, 300);
		
	}

}
