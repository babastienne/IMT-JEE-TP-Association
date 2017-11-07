package servlets;

import models.Item;
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
import java.io.IOException;
import java.util.List;

import static database.Entity.ENTITY;

/**
 * Servlet s'occupant d'afficher la liste des articles disponibles
 */
@WebServlet(name = "ItemlistServlet", urlPatterns = {"/itemlist"})
public class ItemlistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenChecker.checkConnection(request,response);
        EntityManager em= ENTITY.getEntity();

        // Ajout d'article dans la commande
        String code = request.getParameter("code");
        String quantity = request.getParameter("quantity");
        Item item = em.find(Item.class, Long.parseLong(code));
        item.setStock(item.getStock() - Integer.parseInt(quantity));
        ENTITY.update(item);
        response.sendRedirect("/itemlist");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenChecker.checkConnection(request,response);
        EntityManager em= ENTITY.getEntity();

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
        rd.forward(request,response);
    }
}
