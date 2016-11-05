package main.java.dao;

import main.java.entity.Contract;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;


@Repository
public class ContractDao {

    @PersistenceContext
    private EntityManager em;

    // добавляем контракт
    public void add( Contract contract ) {
        em.persist( contract );
    }

    // удаляем контракт
    public void delete( int id ){
        Contract contract = em.find( Contract.class, id );
        em.remove( contract );
    }

    // редактируем контракт
    public void update( Contract contract ){
        em.merge( contract );
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
    @SuppressWarnings( "unchecked" )
    public List<Contract> getAll(){
        Query query = em.createNativeQuery( "Contract.getAll", Contract.class );
        return query.getResultList();
    }
}
