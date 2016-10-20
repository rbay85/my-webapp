package main.java.controller;

import main.java.dao.ContractDao;
import main.java.entity.Contract;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ContractByPhone extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        String phone = req.getParameter( "phone" );
        if ( phone == null ) {
            req.setAttribute( "contractByPhone", "" );
            req.getRequestDispatcher( "/ContractByPhone.jsp" ).forward( req, resp );
        }
        else {
            try{
                ContractDao contractDao = new ContractDao();
                Contract contract = contractDao.getByPhone( phone );
                req.setAttribute( "contractByPhone_client", contract.getClient() );
                req.setAttribute( "error", "" );
                // закидываем в .jsp
                req.getRequestDispatcher( "/ContractByPhone.jsp" ).forward( req, resp );
                // ловим возможные ошибки
            } catch ( NumberFormatException e) {
                req.setAttribute( "error", "error: fill in the field, please !" );
                req.getRequestDispatcher( "/ContractByPhone.jsp" ).forward( req, resp );
            } catch ( NullPointerException e) {
                req.setAttribute( "error", "client not found" );
                req.getRequestDispatcher( "/ContractByPhone.jsp" ).forward( req, resp );
            } catch ( NoResultException e) {
                req.setAttribute( "error", "client with such phone number not found" );
                req.getRequestDispatcher( "/ContractByPhone.jsp" ).forward( req, resp );
            }

        }
    }
}
