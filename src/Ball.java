import java.awt.Color;
import java.awt.Graphics;

public class Ball
{

	private final int WIDTH = 20, HEIGHT = 20;
	private double xVel, yVel, x, y;
	
	public Ball()
	{
		x = PongBoard.WIDTH/2-this.WIDTH/2;
		y = PongBoard.HEIGHT/2-this.HEIGHT/2;
	}
	
	public int getX()
	{
		return (int)x;
	}
	
	public int getY()
	{
		return (int)y;
	}
	
	public void move()
	{
		//TODO if the ball hits the top of the paddle it will bounce in the upward direction and vice versa. 
		x += xVel;
		y += yVel;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
	
	
}
