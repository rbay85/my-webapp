package main.java.controller.old_contrs;

import main.java.service.ClientService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

public class AddClient extends HttpServlet {
    private static Logger logger = Logger.getLogger(AddClient.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        // принимаем данные со страницы
        String firstName = req.getParameter( "firstName" );
        String lastName = req.getParameter( "lastName" );
        String birthDay = req.getParameter( "birthDay" );
        String passport = req.getParameter( "passport" );
        String address = req.getParameter( "address" );
        String email = req.getParameter( "email" );
        String password = req.getParameter( "password" );
        String role = req.getParameter( "role" );

        String url = "/addClient.jsp";


        if ( firstName == null ) {
            req.setAttribute( "message", "" );
            req.getRequestDispatcher( "/addClient.jsp" ).forward( req, resp );

        // проверяем чтоб поля не были пустыми
        } else if ( firstName.equals( "" ) || lastName.equals( "" ) || passport.equals( "" ) || birthDay.equals( "" ) || password.equals( "" ) ){
            req.setAttribute( "error", "error: Fill in all * fields, please !" );
            req.getRequestDispatcher( url ).forward( req, resp );
        } else {
            try{

                ClientService clientService = new ClientService();
                clientService.addClient(
                        firstName,
                        lastName,
                        birthDay,
                        passport,
                        address);

                req.setAttribute( "message", "New client was successfully added" );
                req.setAttribute( "error", "" );

                //закидываем в .jsp
                req.getRequestDispatcher( url ).forward( req, resp );
                logger.info(" everything is OK ");

                // ловим возможные ошибки
            } catch ( NumberFormatException e ){
                req.setAttribute( "error", "error: Please, input valid date in YYYY-MM-DD format" );
                req.getRequestDispatcher( url ).forward( req, resp );
                logger.error("NumberFormatException: wrong date !");
            } catch ( NullPointerException e ){
                req.setAttribute( "error", "error: Please, input valid date in YYYY-MM-DD format" );
                req.getRequestDispatcher( url ).forward( req, resp );
                logger.error( "NullPointerException: wrong date !" );
            } catch ( StringIndexOutOfBoundsException e ){
                req.setAttribute( "error", "error: Please, input valid date in YYYY-MM-DD format" );
                req.getRequestDispatcher( url ).forward( req, resp );
                logger.error( "StringIndexOutOfBoundsException: wrong date !" );
            } catch ( ConstraintViolationException e ){
                req.setAttribute( "error", "error: Please, input valid e-mail !" );
                req.getRequestDispatcher( url ).forward( req, resp );
                logger.error( "ConstraintViolationException: wrong e-mail !" );
            }
        }
    }
}
