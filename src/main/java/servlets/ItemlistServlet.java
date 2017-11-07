package servlets;

import models.Authentification.AuthManager;
import models.Authentification.AuthUser;
import models.Item;
import models.OrderLine;
import models.ServiceOrder;
import models.ServiceUser;
import servlets.util.TokenChecker;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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





        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ServiceUser> c = cb.createQuery(ServiceUser.class);
        Root<ServiceUser> e = c.from(ServiceUser.class);
        String authCookie = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("authToken")) {
                authCookie = cookie.getValue();
            }
        }
        c.select(e).where(cb.equal(e.get("id"), AuthManager.getUID(authCookie)));
        Query query = em.createQuery(c);
        ServiceUser user = (ServiceUser) query.getResultList().get(0);

        CriteriaBuilder cb2 = em.getCriteriaBuilder();
        CriteriaQuery<ServiceOrder> c2 = cb2.createQuery(ServiceOrder.class);
        Root<ServiceOrder> e2 = c2.from(ServiceOrder.class);
        c2.select(e2).where(cb2.equal(e2.get("user"), user.getUid()));
        Query query2 = em.createQuery(c2);
        List<ServiceOrder> so = (List<ServiceOrder>) query2.getResultList();

        ServiceOrder serviceOrder;
        if(so != null && !so.isEmpty()){
            serviceOrder = so.get(0);
        }else{
            serviceOrder = new ServiceOrder(user);
            ENTITY.create(serviceOrder);
        }

        OrderLine ol = null;
        boolean isAlreadyIn = false;
        for(OrderLine i : serviceOrder.getOrders()){
            if(i.getItem().getId() == item.getId()){
                isAlreadyIn = true;
                ol = i;
                break;
            }
        }

        if(isAlreadyIn){
            ol.setQuantityItem(ol.getQuantityItem() + Integer.parseInt(quantity));
            ENTITY.update(ol);
        }else{
            ol = new OrderLine(item, serviceOrder, Integer.parseInt(quantity));
            ENTITY.create(ol);
        }




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
