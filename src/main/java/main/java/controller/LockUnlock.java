package main.java.controller;

import main.java.dao.ContractDao;
import main.java.entity.Contract;

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

        if ( phone == null ) {
            req.setAttribute( "message", "" );
            req.getRequestDispatcher( "/LockUnlock.jsp" ).forward( req, resp );
        }
        else {
            try{

                ContractDao contractDao = new ContractDao();
                Contract contract = contractDao.getByPhone( phone );

                if ( condition.equals( "lock" )){
                    contract.setIs_locked( 2 );
                    contractDao.update( contract );
                    req.setAttribute( "message", " contract was successfully locked" );
                    req.getRequestDispatcher( "/LockUnlock.jsp" ).forward( req, resp );
                } else if ( condition.equals( "unlock" ) ) {
                    contract.setIs_locked( 0 );
                    contractDao.update( contract );
                    req.setAttribute( "message", " contract was successfully unlocked" );
                    req.getRequestDispatcher( "/LockUnlock.jsp" ).forward( req, resp );
                } else {
                    req.setAttribute( "error", " choose an action, please! " );
                    req.getRequestDispatcher( "/LockUnlock.jsp" ).forward( req, resp );
                }

                // ловим возможные ошибки
            } catch ( NumberFormatException e) {
                req.setAttribute( "error", "Sorry, NumberFormatException " );
                req.getRequestDispatcher( "/LockUnlock.jsp" ).forward( req, resp );
            } catch ( NullPointerException e) {
                req.setAttribute( "error", " choose an action, please! " );
                req.getRequestDispatcher( "/LockUnlock.jsp" ).forward( req, resp );
            } catch ( NoResultException e) {
                req.setAttribute( "error", " fill in the field, please!" );
                req.getRequestDispatcher( "/LockUnlock.jsp" ).forward( req, resp );
            }
        }
    }
}
