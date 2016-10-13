package main.java;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientServlet extends HttpServlet{

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        long id = new Long(req.getParameter( "id" ));

        ClientDao client4 = new ClientDao();
        req.setAttribute( "client", client4.get( id ) );

        req.getRequestDispatcher( "/fromServlet.jsp" ).forward( req, resp );
    }
}
