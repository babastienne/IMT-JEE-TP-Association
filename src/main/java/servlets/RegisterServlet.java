package servlets;

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

import static database.Entity.ENTITY;

/**
 * Created by SELIMFIXE on 03/11/2017.
 */

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("email");
        String password =  request.getParameter("password");
        AuthUser authUser = new AuthUser(id, password);
        ENTITY.create(authUser);
        Cookie monCookie = new Cookie( "authToken", authUser.getToken());
        String firstname = request.getParameter("name");
        String lastname = request.getParameter("family-name");
        String address = request.getParameter("address");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String identifiant = request.getParameter("email");

        ServiceUser user = new ServiceUser(firstname, lastname, identifiant, address, zip, city);
        ENTITY.create(user);
        user.setAuthUser(authUser);
        ENTITY.update(user);
        response.addCookie(monCookie);
        request.setAttribute("isConnected", "true");
        RequestDispatcher rd = request.getRequestDispatcher("/hello");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
        rd.forward(request, response);
    }
}
