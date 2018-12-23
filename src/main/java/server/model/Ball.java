package server.model;

import shared.Variables;

public class Ball
{
    private final int WIDTH = Variables.BALL_WIDTH, HEIGHT = Variables.BALL_HEIGHT;
    
    private int x, y;
    private double xVel = -10, yVel = -5;

    void move()
    {
    	x += xVel;
    	y += yVel;
    }  
    
    int getX()
    {
    	return x;
    }
    
    int getY()
    {
    	return y;
    }
    
    double getXVel()
    {
    	return xVel;
    }
    void setXVel(double xVel)
    {
    	this.xVel = xVel;
    }
    
    void resetPosition()
    {
    	x = (Variables.BOARD_WIDTH-this.WIDTH)/2;
    	y = (Variables.BOARD_HEIGHT-this.HEIGHT)/2;

    }

	public void bounceOnEdge()
	{
		
		if(y<=0)
			yVel = -yVel;
		
		else if(y+HEIGHT >= Variables.BOARD_HEIGHT)
			yVel = -yVel;
		
		if(x<0)
		{
			// TODO give points
			System.err.println("Reset position left side");
			resetPosition();
		}
		
		if(x>Variables.BOARD_WIDTH+WIDTH)
		{
			// TODO give points
			System.err.println("Reset position right side");
			resetPosition();
		}

		
	}

	public boolean outOfBounds() {
		if (x < 0 || x+WIDTH > Variables.BOARD_WIDTH || y < 0 || y+HEIGHT > (Variables.BOARD_HEIGHT))
			return true;
		
		return false;
	}
    
}
