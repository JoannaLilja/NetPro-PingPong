package server.model;

import shared.Variables;

public class Ball
{
    private final int WIDTH = Variables.BALL_WIDTH, HEIGHT = Variables.BALL_HEIGHT;
    
    private int x, y;
    private double xVel = -10, yVel = 0;

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

	public void bounceOnEdge()
	{
		
		if(y<=0)
			yVel = -yVel;
		
		else if(y+HEIGHT>=Variables.BOARD_HEIGHT)
			yVel = -yVel;
		
	}
    
}
