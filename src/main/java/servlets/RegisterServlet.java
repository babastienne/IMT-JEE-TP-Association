package servlets;

import static database.Entity.ENTITY;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ServiceUser;
import models.Authentification.AuthUser;

/**
 * Created by SELIMFIXE on 03/11/2017.
 */

/**
 * Servlet s'occupant de la partie inscription
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("email");
        String password =  request.getParameter("password");
        AuthUser authUser = new AuthUser(id, password); // Création de l'AuthUser
        ENTITY.create(authUser);
        String firstname = request.getParameter("name");
        String lastname = request.getParameter("family-name");
        String address = request.getParameter("adress");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String identifiant = request.getParameter("email");

        ServiceUser user = new ServiceUser(firstname, lastname, identifiant, address, zip, city);
        ENTITY.create(user); //Création du ServiceUser
        user.setAuthUser(authUser);
        ENTITY.update(user);
        authUser.setServiceUser(user); // Lien entre ServiceUser et AuthUser
        ENTITY.update(authUser);
        response.sendRedirect("/login.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
        rd.forward(request, response);
    }
}
