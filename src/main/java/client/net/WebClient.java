package client.net;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebClient {
	private Session session;
	
	public WebClient(URI endPoint) {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		try {
			container.connectToServer(this, endPoint);
		} catch (DeploymentException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Connected to socket.");
		this.session = session;
	}
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println("Received message: " + message);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason reason) {
		System.out.println("Socket closed: " + reason.getReasonPhrase());
		this.session = null;
	}
	
	public void sendMessage(String message) {
		session.getAsyncRemote().sendText(message);
	}
	
}
