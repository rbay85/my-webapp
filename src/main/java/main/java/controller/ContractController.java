package main.java.controller;

import main.java.service.ClientService;
import main.java.service.ContractService;
import main.java.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.validation.ValidationException;


@Controller
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TariffService tariffService;

    // вывод списка контрактов
    @RequestMapping( value = "contract", method = RequestMethod.GET )
    public String showAllContracts( Model model ){

        model.addAttribute( "contractList", contractService.getAllContracts() );
        model.addAttribute( "clientList", clientService.getAllClients() );
        model.addAttribute( "tariffList", tariffService.getAllTariffs() );

        return "contract";
    }

    // добавляем новый контракт
    @RequestMapping( value = "/addContract", method = RequestMethod.GET )
    public String addContract ( @RequestParam( value = "phone",     required = false ) String phone,
                                @RequestParam( value = "clientId",  required = false ) String clientId,
                                @RequestParam( value = "tariffId",  required = false ) String tariffId,
                                Model model ){

        try{
            if ( phone != null ){
                contractService.add( phone, clientId, tariffId );
                model.addAttribute( "message", "new contract successfully added" );
            } else {
                model.addAttribute( "message", "phone number must not be null" );
            }
        } catch ( PersistenceException e ) {
            model.addAttribute( "error", "this phone number already exists in database" );
        } catch ( ValidationException e ) {
            model.addAttribute( "error", "input phone number properly, please");
        }
        return "redirect:/contract";
    }

    // удаление контракта
    @RequestMapping( value = "/deleteContract", method = RequestMethod.GET )
    public String deleteContract ( @RequestParam( value = "id", required = false ) String id,
                                   Model model ){

        try{
            String message = contractService.delete( id );
            model.addAttribute( "message", message );
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", "NullPointerException" );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", "NumberFormatException" );
        }
        return "redirect:/contract";
    }

    // смена тариффа в контракте
    @RequestMapping( value = "/changeTariffInContract", method = RequestMethod.GET )
    public String changeTariffInContract ( @RequestParam( value = "contractId",  required = false ) String contractId,
                                           @RequestParam( value = "tariffId", required = false ) String tariffId,
                                           Model model ){

        try{
            String message = contractService.changeTariffInContract( contractId, tariffId );
            model.addAttribute( "message", message );
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", "NullPointerException" );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", "Choose a contract, please" );
        }
        return "redirect:/contract";
    }

    // добавление опции в контракт
    @RequestMapping( value = "/addOptionInContract", method = RequestMethod.GET )
    public String addOptionInContract ( @RequestParam( value = "optionId",   required = false ) String optionId,
                                        @RequestParam( value = "contractId", required = false ) String contractId,
                                        Model model ){

        try{
            String message = contractService.addOptionInContract( optionId, contractId );
            model.addAttribute( "message", message );
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", "NullPointerException" );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", "NumberFormatException" );
        }
        return "redirect:/contract";
    }

    // удаелние опции из контракта
    @RequestMapping( value = "/deleteOptionFromContract", method = RequestMethod.GET )
    public String deleteOptionFromContract ( @RequestParam( value = "contractId", required = false ) String contractId,
                                             @RequestParam( value = "optionId",   required = false ) String optionId,
                                             Model model ){

        try{
            String message = contractService.deleteOptionFromContract( contractId, optionId );
            model.addAttribute( "message", message );
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", "NullPointerException" );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", "NumberFormatException" );
        }
        return "redirect:/contract";
    }

    // поиск контракта по телефону
    @RequestMapping( value = "/contractByPhone", method = RequestMethod.GET )
    public String contractByPhone( @RequestParam( value = "phone", required = false ) String phone, Model model ){

        try {
            model.addAttribute( contractService.getByPhone( phone ));
        } catch ( NoResultException e) {
            model.addAttribute( "error", " contract not found" );
        }
        return "contractByPhone";
    }

    // блокировка/разблокировка админом
    @RequestMapping( value = "/adminLockContract", method = RequestMethod.GET )
    public String adminLockContract ( @RequestParam( value = "id",        required = false ) String id,
                                      @RequestParam( value = "condition", required = false ) String condition,
                                      Model model ){

        try{
            model.addAttribute( "message", contractService.adminLock( id, condition ));
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", "Choose an action, please!" );
        } catch ( NoResultException e ) {
            model.addAttribute( "error", "Fill in the field properly, please!" );
        }
        return "redirect:/contract";
    }

//--------------------- ТО ЧТО ДАЛЕЕ ДЕЛАЕТ КЛИЕНТ В ЛИЧНОМ КАБИНЕТЕ -------------------------------------------------------------------------------------------

    // блокировка/разблокировка клиентом
    @RequestMapping( value = "/personalArea", method = RequestMethod.GET )
    public String personalArea ( Model model ){

        // вынимаем из Spring Security залогиненного юзера и его email
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = currentUser.getUsername();
        // получаем по email юзера id соответствующего клиента
        int clientId = clientService.getClientIdByUserEmail( email );

        try {
            // выводим на страницу клиента и список тарифов
            model.addAttribute( "client", clientService.getById( clientId ));
            model.addAttribute( "tariffList", tariffService.getAllTariffs());
        } catch ( NumberFormatException e ){
            model.addAttribute( "error", "NumberFormatException" );
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", "choose an action, please!" );
        } catch ( NoResultException e ) {
            model.addAttribute( "error", "NoResultException" );
        }
        return "personalArea";
    }

    // блокировка/разблокировка юзером
    @RequestMapping( value = "/userLockContract", method = RequestMethod.GET )
    public String userLockContract ( @RequestParam( value = "id",        required = false ) String id,
                                     @RequestParam( value = "condition", required = false ) String condition,
                                     Model model ){

        try{

            model.addAttribute( "message", contractService.userLock( id, condition ));

        } catch ( NullPointerException e ) {
            model.addAttribute( "error", "NullPointerException" );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", "NumberFormatException" );
        }
        return "redirect:/personalArea";
    }






}