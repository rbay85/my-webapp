package main.java.service;

import main.java.dao.ClientDao;
import main.java.dao.UserDao;
import main.java.entity.Client;

import main.java.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private UserDao userDao;

    // возвращаем клиента по id
    @Transactional
    public Client getById ( int id ){

        Client client = clientDao.get( id );
        return client;
    }

    // возвращаем всех клиентов
    @Transactional
    public List<Client> getAllClients() {

        List<Client> clientList = clientDao.getAll();
        return clientList;
    }

    // создаем нового клиента
    @Transactional
    public String addClient ( String firstName,
                              String lastName,
                              String birthDay,
                              String passNo,
                              String address) {
        // создеам клиента
        Client client = new Client();

        // заполняем поля клиента
        client.setFirstName( firstName );
        client.setLastName( lastName );
        String yyS = birthDay.substring( 2,4 );
        String mmS = birthDay.substring( 5,7 );
        String ddS = birthDay.substring( 8,10 );
        client.setBirthDay( new Date( Integer.parseInt( yyS ),
                                      Integer.parseInt( mmS ) - 1,
                                      Integer.parseInt( ddS )));
        client.setPassNo( passNo );
        client.setAddress( address );

        // пихеам клиента в БД
        clientDao.add( client );
        return "client successfully added";
    }

    // добавляем юзера в клиент
    @Transactional
    public String addUserInClient ( String clientId,
                                    String email,
                                    String role) {

        String message;

        Client client = clientDao.get( Integer.parseInt( clientId ));
        User user = new User();

        if ( role.equals( "admin" )){
            user.setEmail( email );
            user.setPassWord( "aaaa" );
            user.setRole( "ROLE_ADMIN" );
            user.setClient( client );

            userDao.add( user );
            message = "user successfully added, client marked as admin";
        } else {
            user.setEmail( email );
            user.setPassWord( "uuuu" );
            user.setRole( "ROLE_USER" );
            user.setClient( client );

            userDao.add( user );
            message = "user successfully added, client marked as plain user";
        }
        return message;
    }

    // ищем id клиета по email юзера
    @Transactional
    public int getClientIdByUserEmail ( String email ){

        return userDao.getByEmail( email ).getClient().getId();
    }
}
