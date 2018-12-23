package server.model;


import shared.GameCommand;
import shared.Variables;

public class Paddle
{
    private final int WIDTH = Variables.PAD_WIDTH, HEIGHT = Variables.PAD_HEIGHT;
    private int id, x, y;
    //private double xVel, yVel;
    private boolean up, down;

    public Paddle(int id)
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

    public int getY()
    {
        return y;
    }
    
    public int getX() { return x;}

	
    
    
    
	public void move()
	{
		
		if(up)
		{
			y += -5;
			if (y < 0)
				y = 0;
		}
		else if(down)
		{
			y += 5;
			if (y > Variables.BOARD_HEIGHT-this.HEIGHT)
				y = Variables.BOARD_HEIGHT-this.HEIGHT;
		}
		
	}
	
	boolean bounce(Ball b)
	{
		
		// TODO do the fancy thing with the thing
		if(this.overlapsWith(b))
		{
			b.setXVel(-b.getXVel());
			return true;
		}
		
		return false;
		
	}

	private boolean overlapsWith(Ball b)
	{
		//if(id == 1){
			if((x+WIDTH) >= b.getX() && (x) <= b.getX()+Variables.BALL_WIDTH) 
				if((y) <= b.getY() && (y+HEIGHT) >= b.getY()+Variables.BALL_WIDTH) 
					return true;
		/*}
		else if (id == 2) 
		{
			if((x+WIDTH) <= b.getX() && (x)>= b.getX()+Variables.BALL_WIDTH) 
				if((y) >= b.getY() && (y+HEIGHT) <= b.getX()+Variables.BALL_WIDTH) 
					return true;
		}*/
		return false;
	}

	public void command(GameCommand command)
	{
		
		if(command == GameCommand.STOP)
		{
			stop();
			return;
		}
		if(command == GameCommand.UP)
		{
			up();
			return;
		}
		if(command == GameCommand.DOWN)
		{
			down();
			return;
		}
		
		System.err.println("Unrecognized command");
		
	}
	
	private void up()
	{
		this.up = true;
		this.down = false;
	}
	
	private void down()
	{
		this.down = true;
		this.up = false;
	}
	
	private void stop()
	{
		this.up = false;
		this.down = false;
	}

}
