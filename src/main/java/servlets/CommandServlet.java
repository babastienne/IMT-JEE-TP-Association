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

/**
 * Servlet s'occupant d'afficher la liste des articles de la commande
 */
@WebServlet(name = "CommandServlet", urlPatterns = {"/command"})
public class CommandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = ENTITY.getEntity();
        String authCookie = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("authToken")) {
                authCookie = cookie.getValue();
            }
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ServiceOrder> c = cb.createQuery(ServiceOrder.class);
        Root<ServiceOrder> e = c.from(ServiceOrder.class);
        c.select(e).where(cb.equal(e.get("user"), AuthManager.getUID(authCookie) ));
        Query query = em.createQuery(c);
        ServiceOrder so = (ServiceOrder) query.getResultList().get(0);
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
