package main.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ContractDao {

    public EntityManager em = Persistence.createEntityManagerFactory( "myPersUnit" ).createEntityManager();
    EntityTransaction trx = em.getTransaction();

    // добавляем контракт
    public void add( Contract contract ) {
        try {
            trx.begin();
            em.persist( contract );
            trx.commit();
        }
        finally {
            if ( trx.isActive() ) trx.rollback();
        }
    }

    // удаляем контракт
    public void delete( long id ){
        try {
            trx.begin();
            Contract contract = em.find( Contract.class, id );
            em.remove( contract );
            trx.commit();
        }
        finally {
            if ( trx.isActive() ) trx.rollback();
        }
    }

    // редактируем контракт
    public void update( Contract contract ){
        try {
            trx.begin();
            em.merge( contract );
            trx.commit();
        }
        finally {
            if ( trx.isActive() ) trx.rollback();
        }
    }

    // ищем контракт
    public Contract get( long id ){
        return em.find( Contract.class, id );
    }

    // выводим все контракты
    public List<Contract> getAll(){
        TypedQuery<Contract> namedQuery = em.createNamedQuery( "Contract.getAll", Contract.class );
        return namedQuery.getResultList();
    }
}
