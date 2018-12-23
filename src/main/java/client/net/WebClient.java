package client.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URI;
import java.util.Base64;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import shared.GameCommand;
import shared.GameStateDTO;
import shared.LobbyState;

@ClientEndpoint
public class WebClient {
	private Session session;
	private UpdateHandler toView;
	
	public WebClient(URI endPoint, UpdateHandler toView) {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		try {
			this.toView = toView;
			container.connectToServer(this, endPoint);
		} catch (DeploymentException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		toView.sendViewMEssage("Connected to gameserver.");
	}
	
	@OnMessage
	public void onMessage(Session session, String message) {
		
		try {
			LobbyState command = LobbyState.valueOf(message.toUpperCase());
			System.out.println("Received status: " + command.toString());
		} catch (IllegalArgumentException e) {
			try {
				byte data[] = Base64.getDecoder().decode(message);
				ByteArrayInputStream bis = new ByteArrayInputStream(data);
				ObjectInputStream ois = new ObjectInputStream(bis);
				
				GameStateDTO gameState = (GameStateDTO) ois.readObject();
				toView.gameUpdateReceived(gameState);

				bis.close();
				ois.close();
			} catch (IOException | ClassNotFoundException ex) {
				e.printStackTrace();
			}
		}
	}
	
	@OnClose
	public void onClose(Session session, CloseReason reason) {
		System.out.println("Socket closed: " + reason.getReasonPhrase());
		this.session = null;
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("Client error: " + throwable.getMessage());
		throwable.printStackTrace();
	}
	
	public void sendCommand(GameCommand command) {
		session.getAsyncRemote().sendText(command.toString());
	}
	
}
