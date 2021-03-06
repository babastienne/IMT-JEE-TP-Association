package servlets;

import models.*;
import models.Authentification.AuthManager;
import models.Authentification.AuthUser;
import org.hibernate.annotations.Cascade;
import servlets.util.TokenChecker;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.OrderLine;
import models.ServiceOrder;
import models.Authentification.AuthManager;
import servlets.util.TokenChecker;

import java.io.IOException;
import java.util.List;

import static database.Entity.ENTITY;

/**
 * Servlet s'occupant d'afficher la liste des articles de la commande
 */
@WebServlet(name = "CommandServlet", urlPatterns = {"/command"})
public class CommandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = ENTITY.getEntity();
        String authSession = (String) request.getSession().getAttribute("authToken");
        ServiceUser user = em.find(ServiceUser.class, AuthManager.getUID(authSession));
        ServiceOrder so = user.getOrder();
        ServiceOrder order = new ServiceOrder(); // on remet un order vide
        ENTITY.create(order);
        order.setUser(user);
        ENTITY.update(order);
        user.setOrder(order);
        ENTITY.update(user);
        em.getTransaction().begin();
        em.remove(so);
        em.getTransaction().commit();


        response.sendRedirect("/command");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenChecker.checkConnection(request,response);
        if(!response.isCommitted()) {
            EntityManager em = ENTITY.getEntity();

            //Récuperation du token
            String authSession = (String) request.getSession().getAttribute("authToken");

            ServiceUser user = em.find(ServiceUser.class, AuthManager.getUID(authSession));
            ServiceOrder order = user.getOrder();

            // Récupération des ajouts d'article lié à la commande

            List<OrderLine> orderLines = order.getOrders();
            request.setAttribute("listItems", orderLines);
            request.setAttribute("orderLines", orderLines);
            String destination = "command.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(destination);
            rd.forward(request, response);
        }
    }
}
