package controller;

import com.google.gson.Gson;
import dto.ActionDTO;
import dto.ResponseDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ActionService;

/**
 *
 * @author ksoff
 */
@WebServlet(name = "Action", urlPatterns = {"/API/Action"})
public class Action extends HttpServlet {
    
    static int ActionCount = 7;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseDTO responseDto = new ResponseDTO();
        
        Gson gson = new Gson();
        
        ActionDTO actionDTO = gson.fromJson(req.getReader(), ActionDTO.class);
        
        int action = actionDTO.getAction();
        
        if (action > 0 && ActionCount >= action) {
            boolean result = handleAction(action, responseDto);
            if (!result) {
                responseDto.setMessage("Receiver is Disconnected. Please Try again in few minutes or Check the receiver.");
            }
            
            resp.setStatus(resp.SC_OK);
            responseDto.setStatus(result);
        } else {
            responseDto.setMessage("Invalid Action");
        }
        
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(responseDto));
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseDTO responseDto = new ResponseDTO();
        
        Gson gson = new Gson();
        
        if (req.getParameter("action") != null) {
            int action = Integer.parseInt(req.getParameter("action"));
            
            if (action > 0 && ActionCount >= action) {
                boolean result = handleAction(action, responseDto);
                
                resp.setStatus(resp.SC_OK);
                responseDto.setStatus(result);
            } else {
                responseDto.setMessage("Invalid Action");
            }
        }
        
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(responseDto));
    }
    
    private boolean handleAction(int action, ResponseDTO responseDto) {
        boolean result = false;
        switch (action) {
            case 1:
                result = ActionService.Action1();
                responseDto.setMessage("success");
                break;
            case 2:
                result = ActionService.Action2();
                responseDto.setMessage("success");
                break;
            case 3:
                result = ActionService.Action3();
                responseDto.setMessage("success");
                break;
            case 4:
                result = ActionService.Action4();
                responseDto.setMessage("success");
                break;
            case 5:
                result = ActionService.Action5();
                responseDto.setMessage("success");
                break;
            case 6:
                result = ActionService.Action6();
                responseDto.setMessage("success");
                break;
            case 7:
                result = ActionService.Action7();
                responseDto.setMessage("success");
                break;
            default:
                System.out.println(action);
                responseDto.setMessage("Action success");
                break;
        }
        return result;
    }
}
