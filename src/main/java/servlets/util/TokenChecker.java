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


    public static boolean checkConnection(HttpServletRequest request, HttpServletResponse response){
        String authCookie = "";
        Cookie[] cookies = request.getCookies();
        boolean haveToken = false;
        boolean isGood = false;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authToken")) {
                    authCookie = cookie.getValue();
                    haveToken = true;
                }
            }
        }
        try {
            if((!haveToken) || !AuthManager.checkToken(authCookie)){
                TokenChecker.renderNotConnected(request, response);
            }else{
                isGood = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return isGood;
    }
}
