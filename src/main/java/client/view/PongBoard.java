package client.view;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import client.controller.Controller;
import shared.Variables;

public class PongBoard extends Applet implements Runnable, KeyListener
{
	
	Controller contr;

	static final int WIDTH = Variables.BALL_WIDTH;
	static final int HEIGHT = Variables.BALL_HEIGHT;
	
	private Thread thread;
	
	private Paddle p1 = new Paddle(1);
	private Ball b = new Ball();
	

	public void init()
	{
		this.resize(WIDTH,HEIGHT);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (screenSize.width-WIDTH)/2;
		int y = (screenSize.height-HEIGHT)/8;
		
		this.setFocusable(true);
		this.requestFocusInWindow(true);
		
		this.getParent().getParent().setLocation(x, y);

		this.addKeyListener(this);	
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		p1.draw(g);
		b.draw(g);
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public void run()
	{
		
		while(true)
		{
			p1.move();
			
			//contr.transmit(); // transmit paddle position
			
			repaint();
			try
			{
				Thread.sleep(10);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}

	public void keyTyped(KeyEvent e)
	{
		
	}

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			p1.setUp();
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			p1.setDown();
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			p1.stop();
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			p1.stop();
		}		
	}
	
	

}
