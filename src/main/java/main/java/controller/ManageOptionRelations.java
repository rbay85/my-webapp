package main.java.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageOptionRelations extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        // принимаем параметр со страницы
        String option1 = req.getParameter( "option1" );
        String option2 = req.getParameter( "option2" );
        String action = req.getParameter( "action" );


    }
}
