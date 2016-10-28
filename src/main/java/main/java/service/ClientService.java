package main.java.service;

import main.java.dao.ClientDao;
import main.java.entity.Client;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

public class ClientService {

    // подключаем логгер
    private static Logger logger = Logger.getLogger(ClientService.class);

    // возвращаем клиента по id
    public Client getById ( int id ){

        ClientDao clientDao = new ClientDao();
        Client client = clientDao.get( id );
        return client;
    }

    // возвращаем всех клиентов
    public List<Client> getAll() {

        ClientDao clientDao = new ClientDao();
        List<Client> clientList = clientDao.getAll();
        return clientList;
    }

    // создаем нового клиента
    public void addClient ( String firstName,
                            String lastName,
                            String birthDay,
                            String passport,
                            String address,
                            String email,
                            String password ) {

        ClientDao clientDao = new ClientDao();
        Client client = new Client();

        client.setFirstName( firstName );
        client.setLastName( lastName );
        String yyS = birthDay.substring( 2,4 );
        String mmS = birthDay.substring( 5,7 );
        String ddS = birthDay.substring( 8,10 );
        client.setBirthDay( new Date( Integer.parseInt(yyS),Integer.parseInt(mmS) - 1, Integer.parseInt(ddS) ));
        client.setPassNo( passport );
        client.setAddress( address );
        client.setEmail( email );
        client.setPassWord( password );

        clientDao.add( client );
    }
}
