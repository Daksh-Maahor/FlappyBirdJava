package flappy.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import flappy.Handler;
import flappy.gfx.Assets;
import flappy.ui.ClickListener;
import flappy.ui.UIImageButton;
import flappy.ui.UIManager;
import flappy.worlds.World;

public class MenuState extends State {
	
	private UIManager uiManager;
	private World world;
	
	private String title;

	public MenuState(Handler handler) {
		
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
		world = new World(handler, "res/worlds/world1.txt");
		
		title = "Flappy Bird";
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener() {

			@Override
			public void onClick() {
				State.setState(handler.getGame().gameState);
				
			}}));
		
		uiManager.addObject(new UIImageButton(200, 280, 128, 64, Assets.btn_credit, new ClickListener() {

			@Override
			public void onClick() {
				State.setState(handler.getGame().creditState);
				
			}}));

	}

	@Override
	public void tick() {
		
		uiManager.tick();

	}

	@Override
	public void render(Graphics g) {
		
		world.render(g);
		g.setColor(Color.black);
		uiManager.render(g);
		g.setFont(new Font("Arial", Font.ITALIC, 20));
		g.drawString("Made By Daksh Maahor", 400, 500);
		g.drawString(title, 250, 75);

	}

}
