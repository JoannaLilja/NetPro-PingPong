package server.model;

import shared.GameCommand;
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
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	void receiveCommand(GameCommand command, Player player)
	{
		if(player.getId() == 1)
			p1.command(command);
		if(player.getId() == 2)
			p2.command(command);
		System.out.println("Player " + player.getId() + " made command: " + command.toString());
	}
	
	
	private void startGame()
	{
		runGameLoop = true;
		new Thread(this).start();
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
			
			GameStateDTO state = new GameStateDTO(p1.getY(),p2.getY(),ball.getX(),ball.getY());
			player1.sendGameState(state);
			player2.sendGameState(state);
			
			try {
				Thread.sleep(100); // TODO may need to change to a more suitable amount of time
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	public void stopGame() { runGameLoop = false; } 
	

}
