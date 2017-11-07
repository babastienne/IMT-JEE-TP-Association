package servlets;

import models.Authentification.AuthManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;


/**
 * Servlet s'occupant de la partie authentification
 */
@WebServlet(name = "AuthServlet", urlPatterns = {"/login"})
public class AuthServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("email");
        String password =  request.getParameter("password");
        
        HttpSession session = request.getSession();
        session.setAttribute("errorLogin", false);
        session.setAttribute("errorUnknown", false);
        
        try {
        	boolean isConnected = AuthManager.checkAuth(id, password); //vérification des paramètres de connexion
        	
            if(isConnected){
                String newToken = AuthManager.refreshToken(id); //mise en place d'un token dans les cookies
                Cookie token = new Cookie("authToken", newToken);
                response.addCookie(token);
                
                session.setAttribute("isConnected",  true);
                
                response.sendRedirect("/itemlist");

            }else{
                response.sendRedirect("/login");
            }
        } catch(NullPointerException e) {
        	session.setAttribute("errorLogin", true);
        	response.sendRedirect("/login");
        } catch(Exception e) {
            session.setAttribute("errorUnknown", true);
            response.sendRedirect("/login");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
    }
}
