package main.java.controller;

import main.java.service.ClientService;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ShowAllClients extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        String url = "/ShowAllClients.jsp";

        try{
            ClientService clientService = new ClientService();

            req.setAttribute( "clientList", clientService.getAll() );

            // закидываем в .jsp
            req.getRequestDispatcher( url ).forward( req, resp );

            // ловим возможные ошибки
        } catch ( NumberFormatException e) {
            req.setAttribute( "error", "error: NumberFormatException" );
            req.getRequestDispatcher( url ).forward( req, resp );
        } catch ( NullPointerException e) {
            req.setAttribute( "error", "error: NullPointerException" );
            req.getRequestDispatcher( url ).forward( req, resp );
        } catch ( NoResultException e) {
            req.setAttribute( "error", "error: NoResultException" );
            req.getRequestDispatcher( url ).forward( req, resp );
        }
    }
}
