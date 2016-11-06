package main.java.controller.old_contrs;

import main.java.service.OptionService;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ManageOptionRelations extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        // принимаем параметр со страницы
        String optionId1 = req.getParameter( "optionId1" );
        String optionId2 = req.getParameter( "optionId2" );
        String action = req.getParameter( "action" );
        String url = "/manageOptionRelations.jsp";

        if ( optionId1 == null ) {
            req.setAttribute( "error", "" );
            req.getRequestDispatcher( url ).forward( req, resp );
        }

        try{
            OptionService optionService = new OptionService();
            String message = optionService.setOptionRelations( optionId1, optionId2, action);

            req.setAttribute( "message", message );
            req.getRequestDispatcher( url ).forward( req, resp );

            // ловим возможные ошибки
        } catch ( NullPointerException e) {
            req.setAttribute( "error", " error: Choose an action, please!" );
            req.getRequestDispatcher( url ).forward( req, resp );
        } catch ( RollbackException e ) {
            req.setAttribute( "error", " error: RollbackException: Error while committing the transaction" );
            req.getRequestDispatcher( url ).forward( req, resp );
        } catch ( PersistenceException e ) {
            req.setAttribute( "error", " error: PersistenceException: org.hibernate.HibernateException: " +
                                        "Found shared references to a collection: " +
                                        "main.java.entity.Option.necessaryOptionList" );
            req.getRequestDispatcher( url ).forward( req, resp );
        }
    }
}
