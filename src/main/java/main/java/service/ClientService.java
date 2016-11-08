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
    public List<Client> getAll() {

        List<Client> clientList = clientDao.getAll();
        return clientList;
    }

    // создаем нового клиента
    @Transactional
    public void addClient ( String firstName,
                            String lastName,
                            String birthDay,
                            String passport,
                            String address,
                            String email,
                            String password,
                            String role) {
        // создеам клиента
        Client client = new Client();

        // заполняем поля клиента
        client.setFirstName( firstName );
        client.setLastName( lastName );
        String yyS = birthDay.substring( 2,4 );
        String mmS = birthDay.substring( 5,7 );
        String ddS = birthDay.substring( 8,10 );
        client.setBirthDay( new Date( Integer.parseInt( yyS ),Integer.parseInt( mmS ) - 1, Integer.parseInt( ddS )));
        client.setPassNo( passport );
        client.setAddress( address );

        // пихеам клиента в БД
        clientDao.add( client );

        // создаем юзера
        User user = new User();

        // заполняем поля юзера
        user.setEmail( email );
        user.setPassWord( password );
        user.setRole( role );

        // получаем по уникальному номеру паспорта клиента, только что засунутого в БД уже с присвоенным ID
        Client client1 = clientDao.getByPassNo( passport );

        // связывваем клиента с юзером внешним ключем
        user.setClient( client1 );

        // пихеам клиента в БД
        userDao.add( user );
    }
}
