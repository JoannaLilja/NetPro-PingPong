package client.view;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import client.net.WebClient;
import shared.GameCommand;
import shared.GameStateDTO;
import shared.Variables;

public class PongBoard extends Applet implements KeyListener
{
	
	private WebClient toServer;
	
	private static final int WIDTH = Variables.BOARD_WIDTH;
    private static final int HEIGHT = Variables.BOARD_HEIGHT;
    private static final int X = (int)Variables.X_POSITION;
    private static final int Y = (int)Variables.Y_POSITION;
		
	private Paddle p1 = new Paddle(1), p2 = new Paddle(2);

	private Ball ball = new Ball();

	public void init(WebClient toServer)
	{

        this.resize(WIDTH,HEIGHT);
        this.setLocation(X,Y);

		this.setFocusable(true);
		this.requestFocusInWindow(true);

        this.addKeyListener(this);
        this.toServer = toServer;
    }

    void updatePositions(GameStateDTO positionData)
    {
        p1.setPosition(positionData.getPaddleOnePos());
        p2.setPosition(positionData.getPaddleTwoPos());

        ball.setPosition(positionData.getBallX(), positionData.getBallY());
        
        repaint();
    }
	
	public void paint(Graphics g)
	{

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		p1.draw(g);
		p2.draw(g);
		ball.draw(g);
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}

	public void keyTyped(KeyEvent e)
	{
		
	}

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_UP)
			toServer.sendCommand(GameCommand.UP);
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			toServer.sendCommand(GameCommand.DOWN);
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
			toServer.sendCommand(GameCommand.STOP);
	}

	public void close() {
		if (toServer != null)
			toServer.sendCommand(GameCommand.QUIT);
	}
	
	public Paddle getPaddle()
	{
		return p1;
	}
	
	public Ball getBall()
	{
		return ball;
	}
}
