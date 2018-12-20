package server.net;

import com.sun.xml.internal.ws.api.message.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/pong")
public class GameEndPoint {
    private Session session;
    private static Set<GameEndPoint> clients = new CopyOnWriteArraySet<GameEndPoint>();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        clients.add(this);
    }

    @OnMessage
    public void onMessage(Session seesion, Message message) {

    }

    @OnClose
    public void onClose(Session session) {

    }

    @OnError
    public void onError(Session session, Throwable throwable) {

    }
}
