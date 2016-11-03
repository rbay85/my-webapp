package main.java.service;

import main.java.dao.OptionDao;
import main.java.entity.Option;

import java.util.List;

public class OptionService {

    public String setOptionRelations ( int id1, int id2, String action ) {

        String message = "";

        if ( id1 == 0 || id2 == 0 ) {
            message = " error: Choose TWO options, please !";

        } else if ( id1 == id2 ) {
            message = " error: Choose two DIFFERENT options, please !";

        } else {

            OptionDao optionDao = new OptionDao();

            Option option1 = optionDao.get( id1 );
            Option option2 = optionDao.get( id2 );

            if ( action.equals( "required" )) {
                List<Option> option1NecessaryList = option1.getNecessaryOptionList();
                option1NecessaryList.add( option2 );
                option1.setNecessaryOptionList( option1NecessaryList ); // ДОБАВИТЬ ПРОВЕРКУ ( option.areListsContradict() ) Где добавить??
                optionDao.update( option1 );

                message = " Options requirements successfully set";

            } else if ( action.equals( "incompatible" )) {
                List<Option> option1IncompatibleList = option1.getIncompatibleOptionList();
                option1IncompatibleList.add( option2 );
                option1.setIncompatibleOptionList( option1IncompatibleList );
                optionDao.update( option1 );

                List<Option> option2IncompatibleList = option2.getIncompatibleOptionList();
                option2IncompatibleList.add( option1 );
                option2.setIncompatibleOptionList( option2IncompatibleList );
                optionDao.update( option2 );

                message = " Options incompatibility successfully set";

            } else {
                message = " error: Choose an action, please!";

            }
        }

        return message;
    }
}
