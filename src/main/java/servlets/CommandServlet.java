package servlets;

import models.Authentification.AuthManager;
import models.Authentification.AuthUser;
import models.Item;
import models.OrderLine;
import models.ServiceOrder;
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
import javax.xml.ws.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static database.Entity.ENTITY;

/**
 * Servlet s'occupant d'afficher la liste des articles de la commande
 */
@WebServlet(name = "CommandServlet", urlPatterns = {"/command"})
public class CommandServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenChecker.checkConnection(request,response);
        if(!response.isCommitted()) {
            EntityManager em = ENTITY.getEntity();

            //Récuperation du token
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ServiceOrder> c = cb.createQuery(ServiceOrder.class);
            Root<ServiceOrder> e = c.from(ServiceOrder.class);
            String authSession = (String) request.getSession().getAttribute("authToken");

            // Récupération de l'objet ServiceOrder avec numéro d'utilisateur
            c.select(e).where(cb.equal(e.get("orderId"), AuthManager.getUID(authSession)));
            Query query = em.createQuery(c);
            List<ServiceOrder> list = (List<ServiceOrder>) query.getResultList();
            ServiceOrder order = list.get(0);

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
