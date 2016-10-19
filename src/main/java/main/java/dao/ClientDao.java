package main.java.dao;

import main.java.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientDao {

    public EntityManager em = Persistence.createEntityManagerFactory( "myPersUnit" ).createEntityManager();
    EntityTransaction trx = em.getTransaction();

    // добавляем клиента
    public void add( Client client ) {
        try {
            trx.begin();
            em.persist( client );
            trx.commit();
        }
        finally {
            if ( trx.isActive() ) trx.rollback();
        }
    }

    // удаляем клиента
    public void delete( int id ){
        try {
            trx.begin();
            Client client = em.find( Client.class, id );
            em.remove( client );
            trx.commit();
        }
        finally {
            if ( trx.isActive() ) trx.rollback();
        }
    }

    // редактируем клиента
    public void update( Client client ){
        try {
            trx.begin();
            em.merge( client );
            trx.commit();
        }
        finally {
            if ( trx.isActive() ) trx.rollback();
        }
    }

    // ищем клиента
    public Client get( int id ){
        return em.find( Client.class, id );
    }

    // выводим всех
    public List<Client> getAll(){
        TypedQuery<Client> namedQuery = em.createNamedQuery( "Client.getAll", Client.class );
        return namedQuery.getResultList();
    }
}
