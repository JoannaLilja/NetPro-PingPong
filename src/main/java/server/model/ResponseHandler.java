package server.model;

import shared.GameStateDTO;
import shared.LobbyState;

/**
 * An interface the view will implement to handle sending gamestate back to the clients
 */
public interface ResponseHandler {
	
	public void sendGameState(GameStateDTO state);
	public void sendLobbyState(LobbyState state);
}
