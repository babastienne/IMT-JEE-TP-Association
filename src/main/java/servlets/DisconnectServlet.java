package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author babastienne
 * 
 * Servlet implementation class DisconnectServlet
 */
@WebServlet("/disconnect")
public class DisconnectServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp"); 
        rd.forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp"); 
        rd.forward(request, response);
    }
    
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // set connection to false (to the navbar status)
        HttpSession session = request.getSession();
        session.setAttribute("isConnected", false);

        response.sendRedirect("/login");
    }
}
