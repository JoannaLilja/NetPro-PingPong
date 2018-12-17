package client.view;
import java.awt.Color;
import java.awt.Graphics;

import shared.Variables;

public class Ball
{

	private final int WIDTH = Variables.BALL_WIDTH, HEIGHT = Variables.BALL_HEIGHT;
	private double x, y;
	
	public Ball()
	{
		x = (PongBoard.WIDTH-this.WIDTH)/2;
		y = (PongBoard.HEIGHT-this.HEIGHT)/2;
	}
	

	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
	
	
}
