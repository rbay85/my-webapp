package main.java.controller;

import main.java.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.PersistenceException;


@Controller
public class OptionController {

    @Autowired
    private OptionService optionService;

    // вывод списка опций
    @RequestMapping( value = "option", method = RequestMethod.GET )
    public String showAllOptions( Model model ){

        model.addAttribute( "optionList", optionService.getAllOptions() );
        return "option";
    }

    // добавление опции
    @RequestMapping( value = "/addOption", method = RequestMethod.GET )
    public String addOption ( @RequestParam( value = "name",  required = false ) String name,
                              @RequestParam( value = "price", required = false ) String price,
                              @RequestParam( value = "cost",  required = false ) String cost,
                              Model model ){

        try{
            if ( name != null ){
                optionService.add( name, price, cost );
                model.addAttribute( "message", " new option successfully added" );
            } else {
                model.addAttribute( "message", " option name must not be null!" );
            }
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " price & cost must be a number!" );
        } catch ( PersistenceException e ) {
            model.addAttribute( "error", " option with the same name already exists!" );
        }
        return "redirect:/option";
    }

    // удаление опции
    @RequestMapping( value = "/deleteOption", method = RequestMethod.GET )
    public String delete ( @RequestParam( value = "id", required = false ) String id,
                           Model model ){

        try{
            model.addAttribute( "message", optionService.delete( id ) );
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", " NullPointerException " );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " NumberFormatException " );
        }
        return "redirect:/option";
    }

    // удаление необходимой опции
    @RequestMapping( value = "/deleteReqOption", method = RequestMethod.GET )
    public String deleteReqOption ( @RequestParam( value = "optionId",    required = false ) String optionId,
                                    @RequestParam( value = "reqOptionId", required = false ) String reqOptionId,
                                    Model model ){

        try{
            model.addAttribute( "message", optionService.deleteReqOption( optionId, reqOptionId ) );
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", " NullPointerException " );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " NumberFormatException " );
        }
        return "redirect:/option";
    }

    // удаление несовместимой опции
    @RequestMapping( value = "/deleteIncOption", method = RequestMethod.GET )
    public String deleteIncOption ( @RequestParam( value = "optionId1", required = false ) String optionId1,
                                    @RequestParam( value = "optionId2", required = false ) String optionId2,
                                    Model model ){

        try{
            model.addAttribute( "message", optionService.deleteIncOption( optionId1, optionId2 ) );
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", " NullPointerException " );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " NumberFormatException " );
        }
        return "redirect:/option";
    }

    //управление отношениями опций
    @RequestMapping( value = "/manageOptionRelations", method = RequestMethod.GET )
    public String manageOptionRelations ( @RequestParam( value = "optionId1", required = false ) String optionId1,
                                          @RequestParam( value = "optionId2", required = false ) String optionId2,
                                          @RequestParam( value = "action",    required = false ) String action,
                                          Model model ){

        try{
            model.addAttribute( "message", optionService.setOptionRelations( optionId1, optionId2, action ));
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", " Choose an action, please! " );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " ");
        }
        return "redirect:/option";
    }
}