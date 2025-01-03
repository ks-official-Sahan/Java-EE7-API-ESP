package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ksoff
 */
@WebServlet(name = "Test", urlPatterns = {"/Test"})
public class Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        resp.getWriter().write((name != null && !name.isEmpty()) ? "Hello " + name : "Hello Sahan");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // String name = req.getParameter("name");

        Gson gson = new Gson();
        
        // JsonObject requestObject = gson.fromJson(req.getReader(), JsonObject.class);
        
        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("x", "10");
        responseObject.addProperty("y", "20");
        
        resp.setStatus(resp.SC_OK);
        
        //resp.getWriter().write((name != null && !name.isEmpty()) ? "Hello " + name : "Hello Sahan");
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(responseObject));
    }

}
