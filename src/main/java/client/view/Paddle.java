package client.view;
import shared.Variables;

import java.awt.Color;
import java.awt.Graphics;

class Paddle
{

	private final int WIDTH = Variables.PAD_WIDTH, HEIGHT = Variables.PAD_HEIGHT;
	
	private int id, x, y;
	
	Paddle(int id)
	{
		this.id = id;
		
		y = (Variables.BOARD_HEIGHT-HEIGHT)/2;
		
		if(id == 1)
			x = Variables.PAD_PADDING;
		else if (id == 2)
			x = Variables.BOARD_WIDTH-this.WIDTH-Variables.PAD_PADDING;
	}

	void setPosition(int y)
    {
		this.y = y;
    }

	public void draw(Graphics g) {

		g.setColor(Color.green);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}

	public void move()
	{
		/*
		
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
		*/
	}


}
