package main.java.dao;

import main.java.entity.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;


@Repository
public class ClientDao {

    @PersistenceContext
    private EntityManager em;


    // добавляем клиента
    public void add( Client client ){
        em.persist( client );
    }

    // удаляем клиента
    public void delete( int id ){
        Client client = em.find( Client.class, id );
        em.remove( client );
    }

    // редактируем клиента
    public void update( Client client ){
        em.merge( client );
    }

    // ищем клиента
    public Client get( int id ){
        return em.find( Client.class, id );
    }

    // ищем контракт по номеру
    public Client getByPassNo( String passNo ) {
        Query query = em.createQuery( "SELECT c FROM Client c WHERE c.passNo = :passNo" ).setParameter( "passNo", passNo );
        return (Client) query.getSingleResult() ;
    }

    // выводим всех
    @SuppressWarnings( "unchecked" )
    public List<Client> getAll(){
        Query query = em.createQuery( "SELECT c FROM Client c" );
        return query.getResultList();
    }
}
