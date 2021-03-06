package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.util.TokenChecker;

/**
 * Servlet renvoyant vers la page d'accueil
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/index"})
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenChecker.checkConnection(request, response);
        if(!response.isCommitted()) {
            String destination = "index.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(destination);
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenChecker.checkConnection(request, response);
        if(!response.isCommitted()) {
            RequestDispatcher rd = request.getRequestDispatcher("/itemlist");
            rd.forward(request, response);
        }

    }

}
