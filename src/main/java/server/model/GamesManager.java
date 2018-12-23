package server.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import shared.GameCommand;

public class GamesManager {
	private static GamesManager instance = null;
	private static Player playerOne;
	
	private static Map<Player, Game> gamesMap = 
			Collections.synchronizedMap(new HashMap<Player, Game>());
	
	private static Map<String, Player> playersMap = 
			Collections.synchronizedMap(new HashMap<String, Player>());
	
	public static GamesManager getInstance() {
		if (instance == null) {
			synchronized(GamesManager.class) {
				if (instance == null) 
					instance = new GamesManager();
			}
		}
		
		return instance;
	}
	
	private GamesManager() {}

	public void addPlayer(String playerId, ResponseHandler response) {
		
		Player newPlayer = new Player(response);
		playersMap.put(playerId, newPlayer);
		if (setPlayerAsPlayerOne(newPlayer))
			return;
		
		// Player 1 already exists, start a new game and set player as player 2
		newPlayer.sendWaitingForPlayer();
		startNewGame(newPlayer);
	}
	
	private boolean setPlayerAsPlayerOne(Player player) {
		if (playerOne == null) {
			synchronized (this) {
				if (playerOne == null) {
					playerOne = player;
					player.setId(1);
					playerOne.sendWaitingForPlayer();
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void startNewGame(Player playerTwo) {
		playerTwo.setId(2);
		Game gameInstance = new Game(playerOne, playerTwo);
		gamesMap.put(playerOne, gameInstance);
		gamesMap.put(playerTwo, gameInstance);
		playerOne = null;
		gameInstance.startGame();
	}

	public void playerCommand(String playerId, GameCommand command) {
		Player player = playersMap.get(playerId);
		Game gameInstance = gamesMap.get(player);
		gameInstance.receiveCommand(command, player);
	}

	public void playerDisconnect(String playerId) {
		Player player = playersMap.get(playerId);
		Game gameInstance = gamesMap.get(player);
		Player otherPlayer = gameInstance.playerDisconnect(player);
		gamesMap.remove(player);
		gamesMap.remove(otherPlayer);
		playersMap.remove(playerId);
		
		if (setPlayerAsPlayerOne(otherPlayer))
			return;
		
		otherPlayer.sendWaitingForPlayer();
		startNewGame(otherPlayer);
	}
}
