package service;

import websocket.ESPWebSocketServer;

public class ActionService {

    public static boolean Action1() {
        // return ESPCommunicationService.sendCommand("TURN_ON");
        boolean result = ESPWebSocketServer.broadcast("TURN_ON");
        return result;
    }

    public static boolean Action2() {
        // return ESPCommunicationService.sendCommand("TURN_OFF");
        boolean result = ESPWebSocketServer.broadcast("TURN_OFF");
        return result;
    }
    
    public static boolean Action3() {
        boolean result = ESPWebSocketServer.broadcast("Action A");
        return result;
    }

    public static boolean Action4() {
        boolean result = ESPWebSocketServer.broadcast("Action B");
        return result;
    }

    public static boolean Action5() {
        boolean result = ESPWebSocketServer.broadcast("Action C");
        return result;
    }

    public static boolean Action6() {
        boolean result = ESPWebSocketServer.broadcast("Action D");
        return result;
    }

    public static boolean Action7() {
        boolean result = ESPWebSocketServer.broadcast("Action E");
        return result;
    }

}
