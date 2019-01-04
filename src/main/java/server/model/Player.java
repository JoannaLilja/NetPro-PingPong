package server.model;

import shared.GameStateDTO;
import shared.LobbyState;

public final class Player {
	private int playerId;
	private final ResponseHandler toClient;
	
	public Player(ResponseHandler toclient) {
		this.toClient = toclient;
	}
	
	public int getId() {
		return playerId;
	}
	
	public void setId(int id) {
		this.playerId = id;
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
