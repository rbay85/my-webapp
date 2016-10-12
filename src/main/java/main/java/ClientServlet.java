package main.java;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientServlet extends HttpServlet{

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        resp.setContentType("text/html");

        String userName = "Roman";
        req.setAttribute( "name", userName );

        req.getRequestDispatcher( "/fromServlet.jsp" ).forward( req, resp );

        //resp.getWriter().print( "
        // <h1>
        //      Hello from Servlet
        // </h1>
        // " );
    }

    /*
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        super.doGet( req, resp );
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        super.doPost( req, resp );
    }
    */
}
