package flappy;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import flappy.display.Display;
import flappy.gfx.Assets;
import flappy.gfx.GameCamera;
import flappy.input.KeyManager;
import flappy.input.MouseManager;
import flappy.states.CreditState;
import flappy.states.GameState;
import flappy.states.MenuState;
import flappy.states.ScoreState;
import flappy.states.State;
import flappy.states.WinState;

public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	// States
	public State gameState;
	public State menuState;
	public State creditState;
	public State scoreState;
	public State winState;

	private KeyManager keyManager;
	private MouseManager mouseManager;

	private GameCamera gameCamera;

	private Handler handler;

	public Game(String title, int width, int height) {

		this.width = width;
		this.height = height;
		this.title = title;

		keyManager = new KeyManager();
		mouseManager = new MouseManager();

	}

	private void init() {

		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();

		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		creditState = new CreditState(handler);
		scoreState = new ScoreState(handler);
		winState = new WinState(handler);
		State.setState(menuState);

	}

	private void tick() {

		keyManager.tick();

		if (State.getState() != null)
			State.getState().tick();

	}

	private void render() {

		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		//g.clearRect(0, 0, width, height);

		// Draw

		if (State.getState() != null)
			State.getState().render(g);

		// Draw Finish

		bs.show();
		g.dispose();

	}

	private int fps;
	private double timePerTick, delta;
	private long now, lastTime, timer, ticks;
	
	@Override
	public void run() {

		init();

		fps = 60;
		timePerTick = 1000000000 / fps;
		delta = 0;
		lastTime = System.nanoTime();
		timer = 0;
		ticks = 0;

		while (running) {

			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {

				tick();
				render();
				ticks++;
				delta--;

			}

			if (timer >= 1000000000) {
				System.out.println("Ticks : " + ticks);
				timer = 0;
				ticks = 0;
			}

		}

		stop();

	}
	
	public void setState(State s)
	{
		State.setState(s);
	}

	public KeyManager getKeyManager() {

		return keyManager;

	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public synchronized void start() {

		if (running)
			return;
		running = true;

		thread = new Thread(this);
		thread.start();

	}

	public synchronized void stop() {

		if (!running)
			return;
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public long getTicks() {
		return ticks;
	}

}
