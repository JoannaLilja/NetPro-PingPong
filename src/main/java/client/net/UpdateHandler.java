package client.net;

import shared.GameStateDTO;
import shared.LobbyState;

public interface UpdateHandler {
	public void gameUpdateReceived(GameStateDTO state);
	
	public void lobbyUpdateRecieved(LobbyState state);
	
	public void sendViewMEssage(String message);
}
