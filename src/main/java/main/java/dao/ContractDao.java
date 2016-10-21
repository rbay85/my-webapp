package main.java.dao;

import main.java.entity.Contract;

import javax.persistence.*;
import java.util.List;

public class ContractDao {

    private EntityManager em = Persistence.createEntityManagerFactory( "myPersUnit" ).createEntityManager();
    private EntityTransaction trx = em.getTransaction();

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
    public void delete( int id ){
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

    // ищем контракт по ID
    public Contract get( int id ){
        return em.find( Contract.class, id );
    }

    // ищем контракт по номеру
    public Contract getByPhone( String phone ) {
        Query query = em.createQuery( "SELECT co FROM Contract co WHERE co.phone = :phone" ).setParameter( "phone", phone );
        return (Contract) query.getSingleResult() ;
    }

    // выводим все контракты
    public List<Contract> getAll(){
        TypedQuery<Contract> namedQuery = em.createNamedQuery( "Contract.getAll", Contract.class );
        return namedQuery.getResultList();
    }
}
