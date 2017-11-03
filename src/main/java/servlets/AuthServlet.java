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

@WebServlet(name = "AuthServlet")
public class AuthServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean toRegister = Boolean.parseBoolean(request.getParameter("register"));
        if(toRegister){
            doRegister(request, response);
        }else{
            doAuthenticate(request, response);
        }
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password =  request.getParameter("password");
        AuthUser authUser = new AuthUser(id, password);
        EntityManager em = ENTITY.getEntity();
        em.merge(authUser);
        em.getTransaction().commit();
        Cookie monCookie = new Cookie( "authToken", authUser.getToken().toString() );
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String identifiant = request.getParameter("identifiant");

        ServiceUser user = new ServiceUser(firstname, lastname, identifiant, address, zip, city, authUser);

        em.merge(user);
        em.getTransaction().commit();
        response.addCookie(monCookie);
    }


    private void doAuthenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
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
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/authentification/auth_page.jsp");
        rd.forward(request, response);
    }
}
