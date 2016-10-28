package main.java.controller;

import main.java.dao.OptionDao;
import main.java.entity.Option;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManageOptionRelations extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        // принимаем параметр со страницы
        String optionId1 = req.getParameter( "optionId1" );
        String optionId2 = req.getParameter( "optionId2" );
        String action = req.getParameter( "action" );
        String url = "/ManageOptionRelations.jsp";

        if ( optionId1 == null ) {
            req.setAttribute( "error", "" );
            req.getRequestDispatcher( url ).forward( req, resp );
        }
        int id1 = Integer.parseInt( optionId1 );
        int id2 = Integer.parseInt( optionId2 );

        try{
            if ( id1 == 0 || id2 == 0 ) {
                req.setAttribute("error", " error: Choose TWO options, please !");
                req.getRequestDispatcher(url).forward(req, resp);

            } else if ( id1 == id2 ) {
                req.setAttribute("error", " error: Choose two DIFFERENT options, please !");
                req.getRequestDispatcher(url).forward(req, resp);

            } else {

                OptionDao optionDao = new OptionDao();

                Option option1 = optionDao.get( id1 );
                Option option2 = optionDao.get( id2 );

                if ( action.equals( "required" )) {
                    List<Option> option1NecessaryList = option1.getNecessaryOptionList();
                    option1NecessaryList.add( option2 );
                    option1.setNecessaryOptionList( option1NecessaryList ); // ДОБАВИТЬ ПРОВЕРКУ ( option.areListsContradict() ) Где добавить??
                    optionDao.update( option1 );

                    req.setAttribute( "message", " Options requirements successfully set" );
                    req.getRequestDispatcher( url ).forward( req, resp );

                } else if ( action.equals( "incompatible" )) {
                    List<Option> option1IncompatibleList = option1.getIncompatibleOptionList();
                    option1IncompatibleList.add( option2 );
                    option1.setIncompatibleOptionList( option1IncompatibleList );
                    optionDao.update( option1 );

                    List<Option> option2IncompatibleList = option2.getIncompatibleOptionList();
                    option2IncompatibleList.add( option1 );
                    option2.setIncompatibleOptionList( option2IncompatibleList );
                    optionDao.update( option2 );

                    req.setAttribute( "message", " Options incompatibility successfully set" );
                    req.getRequestDispatcher( url ).forward( req, resp );

                } else {
                    req.setAttribute( "error", " error: Choose an action, please!" );
                    req.getRequestDispatcher( url ).forward( req, resp );

                }
            }
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
