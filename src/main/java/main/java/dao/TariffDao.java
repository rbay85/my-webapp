package main.java.dao;

import main.java.entity.Tariff;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;


@Repository
public class TariffDao {

    @PersistenceContext
    private EntityManager em ;

    // добавляем клиента
    public void add( Tariff tariff ) {
        em.persist( tariff );
    }

    // удаляем клиента
    public void delete( int id ){
        Tariff tariff = em.find( Tariff.class, id );
        em.remove( tariff );
    }

    // редактируем клиента
    public void update( Tariff tariff ){
        em.merge( tariff );
    }

    // ищем клиента
    public Tariff get( int id ){
        return em.find( Tariff.class, id );
    }

    // выводим всех //

    public List<Tariff> getAll(){
        Query query = em.createNativeQuery( "Tariff.getAll", Tariff.class );
        return query.getResultList();
    }
}
