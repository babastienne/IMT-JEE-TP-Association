package servlets.util;

import models.Authentification.AuthManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SELIMFIXE on 03/11/2017.
 */
public class TokenChecker {


    private static void renderConnected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = request.getParameter("jspPage");
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        rd.forward(request, response);
    }

    private static void renderNotConnected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = "index.jsp";
        request.setAttribute("authError", "Vous devez être connecté pour accéder à cette page.");
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        response.sendRedirect(destination);
    }


    public static void checkConnection(HttpServletRequest request, HttpServletResponse response){
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
        try {
            if (haveToken && AuthManager.checkToken(authCookie)) {
                TokenChecker.renderConnected(request, response);
            } else {
                TokenChecker.renderNotConnected(request, response);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
