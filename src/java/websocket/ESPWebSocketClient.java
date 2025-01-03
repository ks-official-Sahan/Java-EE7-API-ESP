//package websocket;
//
//import java.net.URI;
//import javax.websocket.*;
//
//@ClientEndpoint
//public class ESPWebSocketClient {
//    private Session session;
//
//    public ESPWebSocketClient(String serverUri) {
//        try {
//            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
//            container.connectToServer(this, new URI(serverUri));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @OnOpen
//    public void onOpen(Session session) {
//        this.session = session;
//        System.out.println("Connected to ESP32 WebSocket server");
//    }
//
//    @OnMessage
//    public void onMessage(String message) {
//        System.out.println("Message from ESP32: " + message);
//    }
//
//    @OnClose
//    public void onClose(Session session, CloseReason closeReason) {
//        System.out.println("Connection closed: " + closeReason);
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        throwable.printStackTrace();
//    }
//
//    public void sendMessage(String message) {
//        try {
//            session.getBasicRemote().sendText(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}