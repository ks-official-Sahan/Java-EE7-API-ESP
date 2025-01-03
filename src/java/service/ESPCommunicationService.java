package service;

import javax.websocket.*;
import java.net.URI;
import java.util.concurrent.locks.ReentrantLock;

public class ESPCommunicationService {
    private static final String ESP32_URI = "ws://abcd1234.ngrok.io/"; // Replace with ngrok public URL
    private static Session espWebSocketSession;
    private static final ReentrantLock lock = new ReentrantLock();

    static {
        initializeWebSocket();
    }

    public static void initializeWebSocket() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(new ESPWebSocketEndpoint(), new URI(ESP32_URI));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean sendCommand(String command) {
        lock.lock();
        try {
            if (espWebSocketSession != null && espWebSocketSession.isOpen()) {
                espWebSocketSession.getBasicRemote().sendText(command);
                return true;
            }
            System.out.println("WebSocket session is not open. Reconnecting...");
            initializeWebSocket();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            lock.unlock();
        }
    }

    @ClientEndpoint
    public static class ESPWebSocketEndpoint {
        @OnOpen
        public void onOpen(Session session) {
            espWebSocketSession = session;
            System.out.println("WebSocket connected to ESP32");
        }

        @OnMessage
        public void onMessage(String message) {
            System.out.println("Message from ESP32: " + message);
        }

        @OnClose
        public void onClose(Session session, CloseReason reason) {
            System.out.println("WebSocket closed: " + reason);
        }

        @OnError
        public void onError(Session session, Throwable throwable) {
            System.err.println("WebSocket error: " + throwable.getMessage());
        }
    }
}
