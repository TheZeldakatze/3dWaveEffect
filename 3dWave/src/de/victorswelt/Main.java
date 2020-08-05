package de.victorswelt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Main implements Runnable {
	BufferedImage screen;
	Graphics2D g, frame_graphics;
	JFrame frame;
	World world;
	
	public static void main(String main[]) {
		new Main();
	}
	
	public Main() {
		frame = new JFrame("3D Wave");
		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame_graphics = (Graphics2D) frame.getGraphics();
		
		screen = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
		g = screen.createGraphics();
		world = new World();
		
		// create a new thread
		new Thread(this).start();
	}

	public void run() {
		while(true) {
			
			// draw a black background
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
			
			// render the world
			world.render(g, screen.getWidth(), screen.getHeight());
			
			// draw the buffer
			frame_graphics.drawImage(screen, 0, 0, screen.getWidth(), screen.getHeight(), frame);
			
			// sleep for 20 milliseconds
			try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
