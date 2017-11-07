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

@WebServlet(name = "CommandServlet", urlPatterns = {"/command"})
public class CommandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenChecker.checkConnection(request,response);
        EntityManager em= ENTITY.getEntity();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ServiceOrder> c = cb.createQuery(ServiceOrder.class);
        Root<ServiceOrder> e = c.from(ServiceOrder.class);
        String authCookie = "";
        Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authToken")) {
                    authCookie = cookie.getValue();
                }
            }
        c.select(e).where(cb.equal(e.get("orderId"), AuthManager.getUID(authCookie)));
        Query query = em.createQuery(c);
        List<ServiceOrder> list = (List<ServiceOrder>) query.getResultList();
        ServiceOrder order = list.get(0);
        List<OrderLine> orderLines = order.getOrders();
        request.setAttribute("listItems", orderLines);



        //HashMap<Item, Integer> items = obj.getItems();
        //List<OrderLine> listOrder = new ArrayList<OrderLine>();

        //for(Item item : items.keySet()){
        //    items.get(item);
        //    listOrder.add(item.getId(), item.getName(), item.getPrice(), (Integer) items.get(item));
        //}
        request.setAttribute("listOrder", orderLines);
        String destination = "command.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        rd.forward(request,response);
    }
}
