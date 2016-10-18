package main.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class OptionDao {

    public EntityManager em = Persistence.createEntityManagerFactory( "myPersUnit" ).createEntityManager();
    EntityTransaction trx = em.getTransaction();

    // добавляем опцию
    public void add( Option option ) {
        try {
            trx.begin();
            em.persist( option );
            trx.commit();
        }
        finally {
            if ( trx.isActive() ) trx.rollback();
        }
    }

    // удаляем опцию
    public void delete( int id ){
        try {
            trx.begin();
            Option option = em.find( Option.class, id );
            em.remove( option );
            trx.commit();
        }
        finally {
            if ( trx.isActive() ) trx.rollback();
        }
    }

    // редактируем опцию
    public void update( Option option ){
        try {
            em.getTransaction().begin();
            em.merge( option );
            em.getTransaction().commit();
        }
        finally {
            if ( trx.isActive() ) trx.rollback();
        }
    }

    // ищем опцию
    public Option get( int id ){
        return em.find( Option.class, id );
    }

    // выводим все опции
    public List<Option> getAll(){
        TypedQuery<Option> namedQuery = em.createNamedQuery( "Option.getAll", Option.class );
        return namedQuery.getResultList();
    }
}
