package servlets;

import models.Authentification.AuthManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authCookie = "";
        Cookie[] cookies = request.getCookies();
        boolean haveToken = false;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    authCookie = cookie.getValue();
                    haveToken = true;
                }
            }
        }
        if(haveToken && checkConnexion(authCookie) ){
            this.renderConnected(request,response);
        }
        else{
            this.renderNotConnected(request,response);
        }
    }

    protected boolean checkConnexion(String authCookie){
        if(AuthManager.checkToken(authCookie)) {
            return true;
        }
    }

    protected void renderConnected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = request.getParameter("jspPage");
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        rd.forward(request, response);
    }

    protected void renderNotConnected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = "index.jsp";
        request.setAttribute("authError", "Vous devez être connecté pour accéder à cette page.");
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        response.sendRedirect(destination);
    }
}
