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

@WebServlet(name = "AuthServlet", urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("email");
        String password =  request.getParameter("password");
        boolean isConnected = AuthManager.checkAuth(id, password);
        if(isConnected){
            UID newToken = AuthManager.refreshToken(id);
            Cookie token = new Cookie("authToken", newToken.toString());
            response.addCookie(token);
        }else{
            //TODO redirection vers une page d'erreur de connexion
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp"); // TODO TO CHANGE
        rd.forward(request, response);
    }
}
