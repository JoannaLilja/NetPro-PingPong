import java.awt.Color;
import java.awt.Graphics;

public class Paddle
{

	private final int WIDTH = 20, HEIGHT = 100;
	
	private double y, yVel;
	private boolean up, down;
	private int id, x;
	
	public Paddle(int id)
	{
		this.id = id;
		
		up = false; down = false;
		y = 210; yVel = 0;
		
		if(id == 1)
			x = 20;
		else if (id == 2)
			x = PongBoard.WIDTH-this.WIDTH-20;
	}
	public void draw(Graphics g) {

		g.setColor(Color.green);
		g.fillRect(x, (int)y, WIDTH, HEIGHT);
	}

	public void move()
	{
		
		
		if(up)
		{
			yVel = -5;
		}
		else if(down)
		{
			yVel = 5;

		}
		else if(!up && !down)
		{
			yVel = 0;
		}
		
		
		y += yVel;
		if (y > PongBoard.HEIGHT-this.HEIGHT)
			y = PongBoard.HEIGHT-this.HEIGHT;
		else if (y < 0)
			y = 0;
		
	}
	
	public void setUp()
	{
		up = true;
		down = false;
	}
	public void setDown()
	{
		down = true;
		up = false;
	}

	public void stop()
	{
		up = false; down = false;
	}
	public int getY() {
		return (int)y;
	}
	

}
