package servlets;

import models.Authentification.AuthManager;
import models.Item;
import models.OrderLine;
import models.ServiceOrder;
import models.ServiceUser;
import servlets.util.TokenChecker;
import static database.Entity.ENTITY;

import java.io.IOException;
import java.util.List;


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

import models.Item;
import servlets.util.TokenChecker;

/**
 * Servlet s'occupant d'afficher la liste des articles disponibles
 */
@WebServlet(name = "ItemlistServlet", urlPatterns = {"/itemlist"})
public class ItemlistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenChecker.checkConnection(request, response);
        if (!response.isCommitted()) {
            EntityManager em = ENTITY.getEntity();

            // Ajout d'article dans la commande
            String code = request.getParameter("code");
            String quantity = request.getParameter("quantity");
            Item item = em.find(Item.class, Long.parseLong(code));
            item.setStock(item.getStock() - Integer.parseInt(quantity));
            ENTITY.update(item);

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ServiceUser> c = cb.createQuery(ServiceUser.class);
            String authSession = (String) request.getSession().getAttribute("authToken");
            ServiceUser user = em.find(ServiceUser.class, AuthManager.getUID(authSession));
            ServiceOrder serviceOrder = user.getOrder();

            OrderLine ol = null;
            boolean isAlreadyIn = false;
            for (OrderLine i : serviceOrder.getOrders()) {
                if (i.getItem().getId() == item.getId()) {
                    isAlreadyIn = true;
                    ol = i;
                    break;
                }
            }

            if (isAlreadyIn) {
                ol.setQuantityItem(ol.getQuantityItem() + Integer.parseInt(quantity));
                ENTITY.update(ol);
            } else {
                ol = new OrderLine(item, serviceOrder, Integer.parseInt(quantity));
                ENTITY.create(ol);
            }


            response.sendRedirect("/itemlist");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenChecker.checkConnection(request,response);
        if(!response.isCommitted()) {
            EntityManager em = ENTITY.getEntity();

            //Récupération de l'ensemble des articles disponibles
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Item> c = cb.createQuery(Item.class);
            Root<Item> e = c.from(Item.class);
            c.select(e);
            Query query = em.createQuery(c);
            List<Item> listItems = (List<Item>) query.getResultList();
            request.setAttribute("listItems", listItems);
            String destination = "itemlist.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(destination);
            rd.forward(request, response);
        }
    }
}
