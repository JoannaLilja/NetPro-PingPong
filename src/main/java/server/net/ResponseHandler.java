package server.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.websocket.Session;

import shared.GameStateDTO;
import shared.LobbyState;

public class ResponseHandler implements server.model.ResponseHandler {
	private Session client;
	
	public ResponseHandler(Session session) {
		client = session;
	}
	
	@Override
	public void sendGameState(GameStateDTO state) {
		client.getAsyncRemote().sendText(convertStateToString(state));
	}

	@Override
	public void sendLobbyState(LobbyState state) {
		client.getAsyncRemote().sendText(state.toString());
	}
	
	private String convertStateToString(GameStateDTO state) {
		String result;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(state);
			
			result = Base64.getEncoder().encodeToString(bos.toByteArray());
			bos.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			result = null;
		}
		
		return result;
	}
}
