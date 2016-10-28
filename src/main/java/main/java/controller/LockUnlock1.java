package main.java.controller;

import main.java.dao.ClientDao;
import main.java.dao.ContractDao;
import main.java.entity.Client;
import main.java.entity.Contract;

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

            ClientDao clientDao = new ClientDao();
            Client client = clientDao.get( id );
            ContractDao contractDao = new ContractDao();

            req.setAttribute( "client", client );
            req.getRequestDispatcher( url ).forward( req, resp );

            List<Contract> contractList = client.getContractList();

            int i = 1;
            for( Contract contract : contractList ){

                String phoneCount = req.getParameter( "phoneCount" );

                if ( i == Integer.parseInt( phoneCount )){
                    if ( condition.equals( "lock" )){
                        if ( contract.getIs_locked() != 2 ) {
                            contract.setIs_locked( 1 );
                            contractDao.update( contract );
                            req.setAttribute( "message", " Your contract was successfully locked" );
                            req.getRequestDispatcher( url ).forward( req, resp );
                        } else {
                            req.setAttribute( "message", " Your contract is already locked by our operator" );
                            req.getRequestDispatcher( url ).forward( req, resp );
                        }

                    } else if ( condition.equals( "unlock" ) ) {
                        if ( contract.getIs_locked() != 2 ) {
                            contract.setIs_locked( 0 );
                            contractDao.update( contract );
                            req.setAttribute( "message", " Your contract was successfully unlocked" );
                            req.getRequestDispatcher( url ).forward( req, resp );
                        } else {
                            req.setAttribute( "message", " Sorry, but your contract locked by our operator" );
                            req.getRequestDispatcher( url ).forward( req, resp );
                        }

                    } else {
                        req.setAttribute( "error", " Choose an action, please! " );
                        req.getRequestDispatcher( url ).forward( req, resp );
                    }
                }
                i++;
            }

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
