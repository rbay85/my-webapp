package main.java.controller;

import main.java.dao.ClientDao;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ShowAllClients extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        try{
            ClientDao clientDao = new ClientDao();

            req.setAttribute( "clientList", clientDao.getAll() );

            // закидываем в .jsp
            req.getRequestDispatcher( "/ShowAllClients.jsp" ).forward( req, resp );

            // ловим возможные ошибки
        } catch ( NumberFormatException e) {
            req.setAttribute( "error", "error: fill in the field, please !" );
            req.getRequestDispatcher( "/ShowAllClients.jsp" ).forward( req, resp );
        } catch ( NullPointerException e) {
            req.setAttribute( "error", "client not found" );
            req.getRequestDispatcher( "/ShowAllClients.jsp" ).forward( req, resp );
        } catch ( NoResultException e) {
            req.setAttribute( "error", "client with such phone number not found" );
            req.getRequestDispatcher( "/ShowAllClients.jsp" ).forward( req, resp );
        }
    }
}
