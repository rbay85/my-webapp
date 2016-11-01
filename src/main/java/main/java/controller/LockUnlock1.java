package main.java.controller;


import main.java.entity.Client;
import main.java.service.ClientService;
import main.java.service.ContractService;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class LockUnlock1 extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        // на примере Анны Смитт
        int id = 6;

        String url = "/LockUnlock1.jsp";

        try{

            ClientService clientService = new ClientService();
            Client client = clientService.getById( id );

            req.setAttribute( "client", client );
            req.getRequestDispatcher( url ).forward( req, resp );

            int contractId = Integer.parseInt( req.getParameter( "contractId" ) );
            String condition = req.getParameter( "condition" );

            ContractService contractService = new ContractService();
            String message = contractService.clientLock( contractId, condition );

            req.setAttribute( "message", message );
            req.getRequestDispatcher( url ).forward( req, resp );

            // ловим возможные ошибки
        } catch ( NumberFormatException e) {
            req.setAttribute( "message", "Sorry, NumberFormatException " );
            req.getRequestDispatcher( url ).forward( req, resp );
        } catch ( NullPointerException e) {
            req.setAttribute( "message", "Sorry, NullPointerException " );
            req.getRequestDispatcher( url ).forward( req, resp );
        } catch ( NoResultException e) {
            req.setAttribute( "message", "Sorry, NoResultException" );
            req.getRequestDispatcher( url ).forward( req, resp );
        }
    }
}
