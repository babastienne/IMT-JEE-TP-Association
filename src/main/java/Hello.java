import servlets.util.TokenChecker;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class Hello extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        TokenChecker.checkConnection(request, response);
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        TokenChecker.checkConnection(request, response);
        process(request, response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello world ! </h1>");
        out.println("<h1>getServletName() : " +
                getServletName() + "</h1>");

        out.println("<h1>URI : " +

                request.getRequestURI() + "</h1>");
        out.println("<h1> Connection:"+request.getAttribute("isConnected")+ " </h1>");

    }
}