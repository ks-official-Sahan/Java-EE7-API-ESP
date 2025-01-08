package websocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.SensorDTO;
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

    private static double temperature = 0;
    private static double humidity = 0;
    private static SensorDTO sensorDTO = new SensorDTO();
    
    // Set to store all active WebSocket sessions
    private static final CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();
    
    private Gson gson = new Gson();

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
        } else if (message.contains("temperature") && message.contains("humidity")) {
            System.out.println("temperature & humidity");

            SensorDTO sensorDTO = gson.fromJson(message, SensorDTO.class);
            System.out.println(sensorDTO.getHumidity());
            System.out.println(sensorDTO.getTemperature());

            JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
            System.out.println(jsonObject.get("humidity").getAsDouble());
            System.out.println(jsonObject.get("temperature").getAsDouble());
            
            ESPWebSocketServer.setSensorDTO(sensorDTO);
            ESPWebSocketServer.setTemperature(sensorDTO.getTemperature());
            ESPWebSocketServer.setHumidity(sensorDTO.getHumidity());
            
            System.out.println(message);
        } else if (message.contains("temperature")) {
            System.out.println("temperature");
            System.out.println(message);
        } else if (message.contains("humidity")) {
            System.out.println("humidity");
            System.out.println(message);
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

    public static double getTemperature() {
        return temperature;
    }

    public static void setTemperature(double aTemperature) {
        temperature = aTemperature;
    }

    public static double getHumidity() {
        return humidity;
    }

    public static void setHumidity(double aHumidity) {
        humidity = aHumidity;
    }

    public static SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public static void setSensorDTO(SensorDTO aSensorDTO) {
        sensorDTO = aSensorDTO;
    }
}
