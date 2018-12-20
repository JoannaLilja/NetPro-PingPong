package server.net;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint(value = "/pong")
public class GameEndPoint {
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;

        // To do: Design an object to maintain and start a new game for each two pairs of clients.
        System.out.println("Client added to lobby waiting for game to start.");

        // To do: Encapsulate this as an object.
        session.getAsyncRemote().sendText("Connected to server. Waiting for players");
    }

    @OnMessage
    public void onMessage(Session seesion, String message) {
    	System.out.println("Server received message: " + message);
    }

    @OnClose
    public void onClose(Session session) {
        // Clean up.
        // Send message to other player
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Server::onError: '" + throwable.getMessage() + "'");
    }
}
