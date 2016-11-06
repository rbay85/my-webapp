package main.java.controller.old_contrs;

import main.java.service.ContractService;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LockUnlock extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        // принимаем параметр со страницы
        String phone = req.getParameter( "phone" );
        String condition = req.getParameter( "condition" );
        String url = "/lockUnlock.jsp";

        if ( phone == null ) {
            req.setAttribute( "message", "" );
            req.getRequestDispatcher( url ).forward( req, resp );
        }
        else {
            try{
                ContractService contractService = new ContractService();
                String message = contractService.adminLock( phone, condition );

                req.setAttribute( "message", message );
                req.getRequestDispatcher( url ).forward( req, resp );

                // ловим возможные ошибки
            } catch ( NullPointerException e) {
                req.setAttribute( "error", " Choose an action, please! " );
                req.getRequestDispatcher( url ).forward( req, resp );
            } catch ( NoResultException e) {
                req.setAttribute( "error", " Fill in the field properly, please!" );
                req.getRequestDispatcher( url ).forward( req, resp );
            }
        }
    }
}
