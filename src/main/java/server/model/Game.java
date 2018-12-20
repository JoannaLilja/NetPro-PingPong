package server.model;

public class Game
{
	
	Ball ball;
	Paddle p1, p2;
	

	void receiveCommand(String command, int clientId)
	{
		if(clientId == 1)
			p1.command(command);
		if(clientId == 2)
			p2.command(command);
	}
	
	
	public void run()
	{
		
		while(true)
		{
			
			if(!p1.bounce(ball))
				p2.bounce(ball);
			
			ball.bounceOnEdge();
			
			ball.move();
			p1.move();
			p2.move();
			
			
			try {
				this.wait(100); // TODO may need to change to a more suitable amount of time
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			// TODO send state to clients here
			
		}
		
		
	}
	

}
