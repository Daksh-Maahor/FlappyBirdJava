package flappy.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage[] bird_flying;
	public static BufferedImage[] bird_falling;
	
	public static BufferedImage[] evil_flying;
	public static BufferedImage[] evil_falling;
	
	public static BufferedImage[] btn_start;
	public static BufferedImage[] btn_credit;
	
	public static BufferedImage pipe, grass, win;
	
	public static void init()
	{
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/assets.jpg"));
		
		bird_flying = new BufferedImage[2];
		bird_falling = new BufferedImage[2];
		
		evil_flying = new BufferedImage[2];
		
		btn_start = new BufferedImage[2];
		btn_credit = new BufferedImage[2];
		
		bird_flying[0] = sheet.crop(0, 0, width, height);
		bird_flying[1] = sheet.crop(width, height, width, height);
		
		bird_falling[0] = sheet.crop(0, height, width, height);
		bird_falling[1] = sheet.crop(width*2, height, width, height);
		
		evil_flying[0] = sheet.crop(0, height*2, width, height);
		evil_flying[1] = sheet.crop(width, height*2, width, height);
		
		btn_start[0] = sheet.crop(width*4, 0, width*2, height);
		btn_start[1] = sheet.crop(width*6, 0, width*2, height);
		
		btn_credit[0] = sheet.crop(width*4, height, width*2, height);
		btn_credit[1] = sheet.crop(width*6, height, width*2, height);
		
		pipe = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width*2, 0, width, height);
		win = sheet.crop(width*3, 0, width, height);
		
	}

}
