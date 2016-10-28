package main.java.controller;

import main.java.dao.ClientDao;
import main.java.entity.Client;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AddClient extends HttpServlet {
    private static Logger logger = Logger.getLogger(AddClient.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        String firstName = req.getParameter( "firstName" );
        String lastName = req.getParameter( "lastName" );
        String birthDay = req.getParameter( "birthDay" );
        String passport = req.getParameter( "passport" );
        String address = req.getParameter( "address" );
        String email = req.getParameter( "email" );
        String password = req.getParameter( "password" );


        if ( firstName == null ) {
            req.setAttribute( "message", "" );
            req.getRequestDispatcher( "/AddClient.jsp" ).forward( req, resp );
        } else if ( firstName.equals( "" ) || lastName.equals( "" ) || passport.equals( "" ) || birthDay.equals( "" ) || password.equals( "" ) ){
            req.setAttribute( "error", "error: Fill in all * fields, please !" );
            req.getRequestDispatcher( "/AddClient.jsp" ).forward( req, resp );
        }
        else {
            try{
                ClientDao clientDao = new ClientDao();
                Client client = new Client();

                client.setFirstName( firstName );
                client.setLastName( lastName );
                try{
                    String yyS = birthDay.substring( 2,4 );
                    String mmS = birthDay.substring( 5,7 );
                    String ddS = birthDay.substring( 8,10 );
                    client.setBirthDay( new Date( Integer.parseInt(yyS),Integer.parseInt(mmS) - 1, Integer.parseInt(ddS) ));
                } catch (NumberFormatException e){
                    req.setAttribute( "error", "error: Please, input valid date in YYYY-MM-DD format" );
                    req.getRequestDispatcher( "/AddClient.jsp" ).forward( req, resp );
                    logger.error("NumberFormatException: wrong date !");
                } catch (NullPointerException e){
                    req.setAttribute( "error", "error: Please, input valid date in YYYY-MM-DD format" );
                    req.getRequestDispatcher( "/AddClient.jsp" ).forward( req, resp );
                    logger.error("NumberFormatException: wrong date !");
                } catch ( StringIndexOutOfBoundsException e ){
                    req.setAttribute( "error", "error: Please, input valid date in YYYY-MM-DD format" );
                    req.getRequestDispatcher( "/AddClient.jsp" ).forward( req, resp );
                    logger.error("NumberFormatException: wrong date !");
                }
                client.setPassNo( passport );
                client.setAddress( address );
                client.setEmail( email );
                client.setPassWord( password );

                clientDao.add( client );

                req.setAttribute( "message", "New client was successfully added" );
                req.setAttribute( "error", "" );
                //закидываем в .jsp
                req.getRequestDispatcher( "/AddClient.jsp" ).forward( req, resp );
                logger.info(" everything is OK ");

                // ловим возможные ошибки
            } catch ( NumberFormatException e) {
                req.setAttribute( "error", "error: fill in all * fields, please !" );
                req.getRequestDispatcher( "/AddClient.jsp" ).forward( req, resp );
                logger.error("NumberFormatException: the ID field is empty !");
            } catch ( NullPointerException e) {
                req.setAttribute( "error", "client not found" );
                req.getRequestDispatcher( "/AddClient.jsp" ).forward( req, resp );
                logger.error("NullPointerException: client not found !");
            }
        }
    }

}
