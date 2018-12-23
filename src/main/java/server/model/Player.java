package server.model;

import shared.GameStateDTO;
import shared.LobbyState;

public final class Player {
	private final String playerId;
	private final ResponseHandler toClient;
	
	public Player(String playerId, ResponseHandler toclient) {
		this.playerId = playerId;
		this.toClient = toclient;
	}

	public void sendWaitingForPlayer() {
		toClient.sendLobbyState(LobbyState.WAITING);
	}

	public void sendGameIsStarting() {
		toClient.sendLobbyState(LobbyState.STARTING);
	}

	public void sendGameState(GameStateDTO state) {
		toClient.sendGameState(state);
	}
}
