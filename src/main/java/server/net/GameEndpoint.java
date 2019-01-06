package server.net;

import java.io.EOFException;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import server.model.GameManager;
import shared.GameCommand;


@ServerEndpoint(value = "/pong")
public class GameEndpoint {
    private GameManager gamesMngr = GameManager.getInstance();
    private ResponseHandler toClient;
    
    @OnOpen
    public void onOpen(Session session) {
    	toClient = new ResponseHandler(session);
        gamesMngr.addPlayer(session.getId(), toClient);       
    }

    @OnMessage
    public void onMessage(Session session, String message) {
    	GameCommand command = GameCommand.valueOf(message.toUpperCase());
    	gamesMngr.playerCommand(session.getId(), command);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        gamesMngr.playerDisconnect(session.getId());
    	System.out.println("Session closed: " + reason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
    	if (getRootCause(throwable) instanceof EOFException) {
    		// Connection with client has closed. Do nothing,
    		return;
    	}
    	
    	System.out.println("Server::onError: '" + throwable.getMessage() + "'\n");
        throwable.printStackTrace();
    }
    
    private Throwable getRootCause(Throwable e) {
        Throwable cause = null; 
        Throwable result = e;

        while(null != (cause = result.getCause())  && (result != cause) ) {
            result = cause;
        }
        return result;
    }
}
