package main.java.controller.old_contrs;

import main.java.service.ClientService;
import main.java.entity.Client;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientByID extends HttpServlet{

    private static Logger logger = Logger.getLogger( ClientByID.class );

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        String url = "/clientById.jsp";

        String parameter = req.getParameter( "id" );
        if ( parameter == null ) {
            req.setAttribute( "client", "" );
            req.getRequestDispatcher( url ).forward( req, resp );
        }
        else {
            try{
                int id = new Integer( parameter );

                ClientService clientService = new ClientService();
                Client client = clientService.getById( id );

                req.setAttribute( "clientFN", client.getFirstName() );
                req.setAttribute( "clientLN", client.getLastName() );
                req.setAttribute( "clientBD", client.getBirthDay() );
                req.setAttribute( "error", "" );

                //закидываем в .jsp
                req.getRequestDispatcher( url ).forward( req, resp );
                logger.info(" everything is OK ");
            } catch ( NumberFormatException e) {
                req.setAttribute( "error", "error: fill in the field, please !" );
                req.getRequestDispatcher( url ).forward( req, resp );
                logger.error("NumberFormatException: the ID field is empty !");
            } catch ( NullPointerException e) {
                req.setAttribute( "error", "client not found" );
                req.getRequestDispatcher( url ).forward( req, resp );
                logger.error("NullPointerException: client not found !");
            }
        }
    }
}
