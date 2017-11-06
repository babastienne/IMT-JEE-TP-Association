package servlets;

import models.Authentification.AuthManager;
import models.Authentification.AuthUser;
import models.Item;
import models.OrderLine;
import servlets.util.TokenChecker;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static database.Entity.ENTITY;

@WebServlet(name = "CommandServlet", urlPatterns = {"/command"})
public class CommandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenChecker.checkConnection(request,response);
        EntityManager em= ENTITY.getEntity();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ServiceOrder> c = cb.createQuery(OrderLine.class);
        Root<OrderLine> e = c.from(OrderLine.class);
        String authCookie = "";
        Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authToken")) {
                    authCookie = cookie.getValue();
                }
            }
        c.select(e).where(cb.equal(e.get("user_id"), AuthManager.getUID(authCookie)));
        Query query = em.createQuery(c);


        List<OrderLine> listItems = (List<Item>) query.getResultList();
        request.setAttribute("listItems", listItems);
        String destination = "itemlist.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        rd.forward(request,response);
        response.sendRedirect("/command.jsp");
    }
}
