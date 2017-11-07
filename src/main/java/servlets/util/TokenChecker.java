package servlets.util;

import models.Authentification.AuthManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SELIMFIXE on 03/11/2017.
 */

/**
 * Classe qui gère la vérification du token
 */
public class TokenChecker {

    private static void renderNotConnected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = "index.jsp";
        response.sendRedirect(destination);
    }


    public static void checkConnection(HttpServletRequest request, HttpServletResponse response){
        String authSession = (String) request.getSession().getAttribute("authToken");
        try {
            if(authSession == null || !AuthManager.checkToken(authSession)){
                TokenChecker.renderNotConnected(request, response);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
