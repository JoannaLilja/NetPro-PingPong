package server.model;

import shared.GameCommand;
import shared.GameStateDTO;

public class Game implements Runnable
{
	
	private Ball ball;
	private Paddle paddle1, paddle2;
	private Player player1, player2;
	private volatile boolean runGameLoop;

	public Game(Player player1, Player player2)
	{

		this.player1 = player1;
		this.player2 = player2;
			
		paddle1 = new Paddle(1);
		paddle2 = new Paddle(2);
			
		ball = new Ball();
		ball.resetPosition();

	}


	void receiveCommand(GameCommand command, Player player)
	{
		if(player.getId() == 1)
			paddle1.command(command);
		else if(player.getId() == 2)
			paddle2.command(command);
		else
		{
			System.err.println("Player id does not exist");
			runGameLoop = false;
		}
	}
	
	
	void startGame()
	{
		runGameLoop = true;
		new Thread(this).start();
	}
	
	public void run()
	{
				
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		player1.sendGameIsStarting();
		player2.sendGameIsStarting();
		
		while(runGameLoop)
		{
			
			paddle1.bounce(ball);
			paddle2.bounce(ball);
			
			ball.move();
			ball.bounceOnEdge();
			
			paddle1.move();
			paddle2.move();
				
			GameStateDTO state = new GameStateDTO(paddle1.getY(),paddle2.getY(),ball.getX(),ball.getY());
			player1.sendGameState(state);
			player2.sendGameState(state);
			
			try {
				Thread.sleep(75); // TODO may need to change to a more suitable amount of time
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	public void stopGame() 
	{
		runGameLoop = false;
	}


	public Player playerDisconnect(Player playerThatLeft) {
		stopGame();
		Player playerStillInGame = playerThatLeft == player1 ? player2 : player1;
		playerStillInGame.sendPlayerDisconnected();
		return playerStillInGame;
	} 
}
