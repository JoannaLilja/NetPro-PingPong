package client.view;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import client.controller.Controller;
import shared.GameStateDTO;
import shared.Variables;

import javax.swing.*;

public class PongBoard extends Applet implements Runnable, KeyListener
{
	
	Controller contr;

	private static final int WIDTH = Variables.BOARD_WIDTH;
    private static final int HEIGHT = Variables.BOARD_HEIGHT;
    private static final int X = (int)Variables.X_POSITION;
    private static final int Y = (int)Variables.Y_POSITION;
	
	private Thread thread;
	
	private Paddle p1 = new Paddle(1);
	private Ball b = new Ball();

	JFrame parent;

	public PongBoard(JFrame parent)
    {
        this.parent = parent;
    }

	public void init()
	{
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.resize(WIDTH,HEIGHT);
        this.setLocation(X,Y);

		this.setFocusable(true);
		this.requestFocusInWindow(true);

        this.addKeyListener(this);
		
		thread = new Thread(this);
		thread.start();
    }

    updatePositions(GameStateDTO positionData)
    {
        p1.setPosition();
        p2.setPosition();

        ball.setPosition();
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
			
			//contr.transmit(0); // transmit paddle position
			

            // TODO replace the example object serverMessage with the actual serverMessage
            GameStateDTO serverMessage = new GameStateDTO(50,50,50,50);

            updatePositions(serverMessage);

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
			// TODO send up message
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
		    // TODO send up message
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			// TODO send stop message
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			// TODO send stop message
		}		
	}

}
