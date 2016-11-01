package main.java.controller;

import main.java.dao.ClientDao;
import main.java.dao.ContractDao;
import main.java.entity.Client;
import main.java.entity.Contract;
import main.java.service.ClientService;
import main.java.service.ContractService;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class LockUnlock1 extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        // на примере Анны Смитт
        int id = 7;

        // принимаем параметр со страницы
        String condition = req.getParameter( "condition" );
        String url = "/LockUnlock1.jsp";

        try{

            ClientService clientService = new ClientService();
            Client client = clientService.getById( id );

            req.setAttribute( "client", client );
            req.getRequestDispatcher( url ).forward( req, resp );

            int contractId = Integer.parseInt( req.getParameter( "contractId" ) );

            ContractService contractService = new ContractService();
            contractService.clientLock( contractId, condition );


            // ловим возможные ошибки
        } catch ( NumberFormatException e) {
            req.setAttribute( "error", "Sorry, NumberFormatException " );
            req.getRequestDispatcher( url ).forward( req, resp );
        } catch ( NullPointerException e) {
            req.setAttribute( "error", "Sorry, NullPointerException " );
            req.getRequestDispatcher( url ).forward( req, resp );
        } catch ( NoResultException e) {
            req.setAttribute( "error", "Sorry, NoResultException" );
            req.getRequestDispatcher( url ).forward( req, resp );
        }
    }
}
