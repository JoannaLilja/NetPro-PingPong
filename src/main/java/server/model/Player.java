package server.model;

import shared.GameStateDTO;
import shared.LobbyState;

final class Player {
	private int playerId;
	private final ResponseHandler toClient;
	
	Player(ResponseHandler toclient) {
		this.toClient = toclient;
	}
	
	int getId() {
		return playerId;
	}
	
	void setId(int id) {
		this.playerId = id;
	}

	void sendWaitingForPlayer() {		
		toClient.sendLobbyState(LobbyState.WAITING);
	}

	void sendGameIsStarting() {
		toClient.sendLobbyState(LobbyState.STARTING);
	}

	void sendGameState(GameStateDTO state) {
		toClient.sendGameState(state);
	}

	void sendPlayerDisconnected() {	
		toClient.sendLobbyState(LobbyState.DISCONNECTED);
	}

}
