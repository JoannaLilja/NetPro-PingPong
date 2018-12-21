package client.view;

import client.net.UpdateHandler;
import shared.GameStateDTO;
import shared.LobbyState;

public class ViewUpdateHandler implements UpdateHandler {
	
	private final PongBoard pongBoard;
	
	public ViewUpdateHandler(PongBoard pongBoard) {
		this.pongBoard = pongBoard;
	}
	
	@Override
	public void gameUpdateReceived(GameStateDTO state) {
		pongBoard.updatePositions(state);
	}

	@Override
	public void lobbyUpdateRecieved(LobbyState state) {
		if (state == LobbyState.STARTING)
			sendViewMEssage("Game is starting soon. Get ready.");
		 else if (state == LobbyState.WAITING)
			sendViewMEssage("Waiting to find another player...");
	}

	@Override
	public void sendViewMEssage(String message) {
		System.out.println(message);
	}

}
