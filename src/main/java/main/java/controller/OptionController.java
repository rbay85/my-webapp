package main.java.controller;

import main.java.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OptionController {

    @Autowired
    private OptionService optionService;

    @RequestMapping( value = "/manageOptionRelations", method = RequestMethod.GET )
    public String manageOptionRelations ( @RequestParam( value = "optionId1", required = false ) String optionId1,
                                          @RequestParam( value = "optionId2", required = false ) String optionId2,
                                          @RequestParam( value = "action", required = false ) String action,
                                          Model model ){

        try{
            model.addAttribute( "message", optionService.setOptionRelations( optionId1, optionId2, action ));
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", " Choose an action, please! " );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " ");
        }
        return "manageOptionRelations";
    }
}