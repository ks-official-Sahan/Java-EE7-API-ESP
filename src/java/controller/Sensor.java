package controller;

import com.google.gson.Gson;
import dto.ResponseDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import websocket.ESPWebSocketServer;

/**
 *
 * @author ksoff
 */
@WebServlet(name = "Sensor", urlPatterns = {"/API/Sensor"})
public class Sensor extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseDTO responseDto = new ResponseDTO();
        
        Gson gson = new Gson();
        
        resp.setStatus(resp.SC_OK);
        responseDto.setData(gson.toJson(ESPWebSocketServer.getSensorDTO()));
        responseDto.setStatus(true);
        
        resp.setContentType(
                "application/json");
        resp.getWriter()
                .write(gson.toJson(responseDto));
        
    }
    
}
