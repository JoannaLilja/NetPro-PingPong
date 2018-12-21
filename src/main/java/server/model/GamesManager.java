package server.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import shared.LobbyState;

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
		
		if (playerOne == null) {
			synchronized (this) {
				if (playerOne == null) {
					playerOne = new Player(playerId, response);
					playersMap.put(playerId, playerOne);
					playerOne.sendWaitingForPlayer();
					return;
				}
			}
		}
		
		if (!canStartGame()) {
			System.out.println("Can't start game for some reason.");
			return;
		}
		
		startNewGame(playerId, response);
	}
	
	private boolean canStartGame() {
		return playerOne != null;
	}
	
	private void startNewGame(String playerTwoId, ResponseHandler response) {
		Player playerTwo = new Player(playerTwoId, response);
		playerTwo.sendWaitingForPlayer();
		playersMap.put(playerTwoId, playerTwo);
		Game gameInstance = new Game(playerOne, playerTwo);
		gamesMap.put(playerOne, gameInstance);
		gamesMap.put(playerTwo, gameInstance);
		playerOne = null;
	}
}
