package server.net;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import server.model.GamesManager;
import shared.GameCommand;


@ServerEndpoint(value = "/pong")
public class GameEndPoint {
    private GamesManager gamesMngr = GamesManager.getInstance();
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
    	//System.out.println("Server received command: " + command.toString());
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        gamesMngr.playerDisconnect(session.getId());
    	System.out.println("Session closed: " + reason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Server::onError: '" + throwable.getMessage() + "'");
        throwable.printStackTrace();
    }
}
