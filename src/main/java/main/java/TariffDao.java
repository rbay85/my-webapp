package main.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TariffDao {

    public EntityManager em = Persistence.createEntityManagerFactory( "myPersUnit" ).createEntityManager();
    EntityTransaction trx = em.getTransaction();

    // добавляем клиента
    public void add( Tariff tariff ) {
        try {
            trx.begin();
            em.persist( tariff );
            trx.commit();
        }
        finally {
            if (trx.isActive()) trx.rollback();
        }
    }

    // удаляем клиента
    public void delete( long id ){
        try {
            trx.begin();
            Tariff tariff = em.find( Tariff.class, id );
            em.remove( tariff );
            trx.commit();
        }
        finally {
            if (trx.isActive()) trx.rollback();
        }
    }

    // редактируем клиента
    public void update( Tariff tariff ){
        try {
            trx.begin();
            em.merge( tariff );
            trx.commit();
        }
        finally {
            if (trx.isActive()) trx.rollback();
        }
    }

    // ищем клиента
    public Tariff get( long id ){
        return em.find( Tariff.class, id );
    }

    // выводим всех
    public List<Tariff> getAll(){
        TypedQuery<Tariff> namedQuery = em.createNamedQuery("Tariff.getAll", Tariff.class);
        return namedQuery.getResultList();
    }
}
