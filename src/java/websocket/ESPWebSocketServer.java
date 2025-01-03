package websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author ksoff
 */
@ServerEndpoint("/esp")
public class ESPWebSocketServer {

    // Set to store all active WebSocket sessions
    private static final CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("New connection opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message received from ESP32: " + message);

        // Handle received messages
        if ("TURN_ON".equalsIgnoreCase(message)) {
            System.out.println("ESP32 reported: TURN_ON command received");
        } else if ("TURN_OFF".equalsIgnoreCase(message)) {
            System.out.println("ESP32 reported: TURN_OFF command received");
        } else {
            System.out.println("Unknown message: " + message);
        }

        // Optionally respond to the ESP32 client
        try {
            session.getBasicRemote().sendText("Message received: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        sessions.remove(session);
        System.out.println("Connection closed: " + session.getId() + " Reason: " + reason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("Error in session " + session.getId() + ": " + throwable.getMessage());
    }

    // Method to send a message to all connected clients (ESP32 and others)
    public static boolean broadcast(String message) {
        boolean isSuccess = false;
        System.out.println("No.Sessions " + sessions.size());
        if (sessions.isEmpty()) {
            return isSuccess;
        }
        for (Session session : sessions) {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                    isSuccess = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
        return isSuccess;
    }
}
