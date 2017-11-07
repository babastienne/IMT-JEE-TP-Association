package servlets;

import database.Entity;
import models.Authentification.AuthManager;
import models.Authentification.AuthUser;
import models.ServiceUser;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.UID;

import static database.Entity.ENTITY;

@WebServlet(name = "AuthServlet", urlPatterns = {"/login"})
public class AuthServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("email");
        String password =  request.getParameter("password");
        boolean isConnected = AuthManager.checkAuth(id, password);
        if(isConnected){
            String newToken = AuthManager.refreshToken(id);
            Cookie token = new Cookie("authToken", newToken);
            response.addCookie(token);
            request.setAttribute("isConnected", "true");
            response.sendRedirect("/itemlist");

        }else{
            //TODO redirection vers une page d'erreur de connexion
            request.setAttribute("isConnected", "false");
        }
        response.sendRedirect("/login");

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp"); // TODO TO CHANGE
        rd.forward(request, response);
    }
}
