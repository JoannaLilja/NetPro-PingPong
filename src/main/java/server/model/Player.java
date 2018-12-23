package server.model;

import shared.GameStateDTO;
import shared.LobbyState;

public final class Player {
	private final int playerId;
	private final ResponseHandler toClient;
	
	public Player(int playerId, ResponseHandler toclient) {
		this.playerId = playerId;
		this.toClient = toclient;
	}
	
	public int getId() {
		return playerId;
	}

	void sendWaitingForPlayer() {
		toClient.sendLobbyState(LobbyState.WAITING);
	}

	public void sendGameIsStarting() {
		toClient.sendLobbyState(LobbyState.STARTING);
	}

	public void sendGameState(GameStateDTO state) {
		toClient.sendGameState(state);
	}

	public void sendPlayerDisconnected() {
		toClient.sendLobbyState(LobbyState.DISCONNECTED);
	}

}
