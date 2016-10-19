package main.java.controller;

import main.java.dao.ClientDao;
import main.java.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientServlet extends HttpServlet{

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        String parameter = req.getParameter( "id" );
        if ( parameter == null ) {
            req.setAttribute( "client", "" );
            req.getRequestDispatcher( "/fromServlet.jsp" ).forward( req, resp );
        }
        else {
            try{
                int id = new Integer( parameter );

                ClientDao clientDao = new ClientDao();
                Client client = clientDao.get( id );
                req.setAttribute( "clientFN", client.getFirstName() );
                req.setAttribute( "clientLN", client.getLastName() );
                req.setAttribute( "error", "" );
                //закидываем в .jsp
                req.getRequestDispatcher( "/fromServlet.jsp" ).forward( req, resp );
            } catch ( NumberFormatException e) {
                req.setAttribute( "error", "error: parameter mustn't be null" );
                req.getRequestDispatcher( "/fromServlet.jsp" ).forward( req, resp );
            } catch ( NullPointerException e) {
                req.setAttribute( "error", "client not found" );
                req.getRequestDispatcher( "/fromServlet.jsp" ).forward( req, resp );
            }
        }
    }
}
