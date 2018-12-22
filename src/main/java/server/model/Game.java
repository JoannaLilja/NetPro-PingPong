package server.model;

import shared.GameStateDTO;

public class Game implements Runnable
{
	
	private Ball ball;
	private Paddle p1, p2;
	private Player player1, player2;
	private volatile boolean runGameLoop;

	public Game(Player player1, Player player2) {
		try {
			player1.sendGameIsStarting();
			player2.sendGameIsStarting();
			Thread.sleep(5000);
			this.player1 = player1;
			this.player2 = player2;
			
			p1 = new Paddle(1);
			p2 = new Paddle(2);
			
			ball = new Ball();
			runGameLoop = true;
			
			GameStateDTO state = new GameStateDTO(p1.getY(), p2.getY(), ball.getX(), ball.getY());
			player1.sendGameStarted(state);
			player2.sendGameStarted(state);
			
			new Thread(this).start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	void receiveCommand(String command, int clientId)
	{
		if(clientId == 1)
			p1.command(command);
		if(clientId == 2)
			p2.command(command);
	}
	
	
	public void run()
	{
		
		while(runGameLoop)
		{
			
			if(!p1.bounce(ball))
				p2.bounce(ball);
			
			ball.bounceOnEdge();
			
			ball.move();
			p1.move();
			p2.move();
			
			
			try {
				Thread.sleep(100); // TODO may need to change to a more suitable amount of time
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			// TODO send state to clients here
			
		}
		
		
	}
	
	public void stopGame() { runGameLoop = false; } 
	

}
