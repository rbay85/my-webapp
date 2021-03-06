package main.java.service;

import main.java.dao.OptionDao;
import main.java.entity.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OptionService {

    @Autowired
    private OptionDao optionDao;

    // выводим все опции
    @Transactional
    public List<Option> getAllOptions() {

        List<Option> optionList = optionDao.getAll();
        return optionList;
    }

    // добавляем опцию
    @Transactional
    public void add( String name, String price, String cost ) {

        Option option = new Option();
        option.setName( name );
        option.setPrice( Double.parseDouble( price ));
        option.setOnCost( Double.parseDouble( cost ));

        optionDao.add( option );
    }

    // удаляем опцию по Id
    @Transactional
    public String delete( String id ) {

        optionDao.delete( Integer.parseInt( id ) );
        return "option successfully deleted";
    }

    // удаляем необходимую опцию
    @Transactional
    public String deleteReqOption( String optionId, String reqOptionId ){

        Option option = optionDao.get( Integer.parseInt( optionId ) );
        Option reqOption = optionDao.get( Integer.parseInt( reqOptionId ) );

        option.getNecessaryOptionList().remove( reqOption );

        optionDao.update( option );
        return "required option successfully deleted";
    }

    // удаляем несовместимую опцию
    @Transactional
    public String deleteIncOption( String optionId1, String optionId2 ){

        // находим две опции
        Option option1 = optionDao.get( Integer.parseInt( optionId1 ) );
        Option option2 = optionDao.get( Integer.parseInt( optionId2 ) );

        // для каждой из списка её несовместимых удаляем другую
        option1.getIncompatibleOptionList().remove( option2 );
        option2.getIncompatibleOptionList().remove( option1 );

        // апдейтим обе
        optionDao.update( option1 );
        optionDao.update( option2 );
        return " options incompatibility successfully deleted";
    }

    // устанавливаем отношениями опций
    @Transactional
    public String setOptionRelations ( String optionId1, String optionId2, String action ) {

        String message;
        int id1 = Integer.parseInt( optionId1 );
        int id2 = Integer.parseInt( optionId2 );

        //проверка айдишников
        if ( id1 == 0 || id2 == 0 ) {
            message = " error: Choose TWO options, please !";
        } else if ( id1 == id2 ) {
            message = " error: Choose two DIFFERENT options, please !";
        } else {

            // берем две опции
            Option option1 = optionDao.get( id1 );
            Option option2 = optionDao.get( id2 );

            // если надо установить как требуемую
            if ( action.equals( "required" )) {

                // проверяем, что опции не имеют уже отношений
                if ( option1.getNecessaryOptionList().contains( option2 ) || option2.getNecessaryOptionList().contains( option1 ) ) {
                    message = "options is already set as required";
                } else if (option1.getIncompatibleOptionList().contains( option2 ) || option2.getIncompatibleOptionList().contains( option1 ) ) {
                    message = "options is already set as incompatible";
                } else {
                    // устанавливаем как требуемую
                    option1.getNecessaryOptionList().add( option2 );
                    optionDao.update( option1 );
                    message = " Options requirements successfully set";
                }

            // если надо установить как несовместимые
            } else if ( action.equals( "incompatible" )) {

                // проверяем, что опции не имеют уже отношений
                if ( option1.getNecessaryOptionList().contains( option2 ) || option2.getNecessaryOptionList().contains( option1 ) ) {
                    message = "options is already set as required";
                } else if (option1.getIncompatibleOptionList().contains( option2 ) || option2.getIncompatibleOptionList().contains( option1 ) ) {
                    message = "options is already set as incompatible";
                } else {
                    // устанавливаем как требуемую
                    option1.getIncompatibleOptionList().add( option2 );
                    option2.getIncompatibleOptionList().add( option1 );
                    optionDao.update( option1 );
                    optionDao.update( option2 );
                    message = "Options incompatibility successfully set";
                }
            } else {
                message = "error: Choose an action, please!";
            }
        }
        return message;
    }
}