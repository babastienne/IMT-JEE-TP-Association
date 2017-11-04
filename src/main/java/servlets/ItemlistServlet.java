package servlets;

import models.Item;

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

@WebServlet(name = "ItemlistServlet", urlPatterns = {"/itemlist"})
public class ItemlistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em= ENTITY.getEntity();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> c = cb.createQuery(Item.class);
        Root<Item> item = c.from(Item.class);
        c.select(item);
        Query query = em.createQuery(c);
        List<Item> listItems = (List<Item>) query.getResultList();
        request.setAttribute("listItems", listItems);
        String destination = "itemlist.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        rd.forward(request,response);
        for(Item i : listItems){
            System.out.println("Name : " + i.getName() + "\n"+
                                "Price : " + i.getPrice() + "\n"+
                                "Stock : " + i.getStock() + "\n \n");
        }
    }
}
