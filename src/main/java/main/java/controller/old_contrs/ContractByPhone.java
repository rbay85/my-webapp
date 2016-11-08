package main.java.controller.old_contrs;


import main.java.entity.Contract;
import main.java.service.ContractService;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ContractByPhone extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        // принимаем параметр со страницы
        String phone = req.getParameter( "phone" );
        String url = "/contractByPhone.jsp";

        if ( phone == null ) {
            req.setAttribute( "contractByPhone", "" );
            req.getRequestDispatcher( url ).forward( req, resp );
        }
        else {
            try{
                ContractService contractService = new ContractService();
                Contract contract = contractService.getByPhone( phone );
                req.setAttribute( "contractByPhone_phone", contract.getPhone() );
                req.setAttribute( "contractByPhone_tariff", contract.getTariff().getName() );
                req.setAttribute( "contractByPhone_clientFN", contract.getClient().getFirstName() );
                req.setAttribute( "contractByPhone_clientLN", contract.getClient().getLastName() );
                req.setAttribute( "contractByPhone_clientBD", contract.getClient().getBirthDay() );
                req.setAttribute( "contractByPhone_optionList", contract.getOptionList() );
                req.setAttribute( "message", "lock condition:" );
                req.setAttribute( "contractByPhone_isLocked", contract.getIsLocked() );
                req.setAttribute( "error", " " );

                // закидываем в .jsp
                req.getRequestDispatcher( url ).forward( req, resp );

            // ловим возможные ошибки
            } catch ( NumberFormatException e) {
                req.setAttribute( "error", "error: Fill in the field, please !" );
                req.getRequestDispatcher( url ).forward( req, resp );
            } catch ( NullPointerException e) {
                req.setAttribute( "error", "Contract not found" );
                req.getRequestDispatcher( url ).forward( req, resp );
            } catch ( NoResultException e) {
                req.setAttribute( "error", "Contract not found" );
                req.getRequestDispatcher( url ).forward( req, resp );
            }
        }
    }
}
