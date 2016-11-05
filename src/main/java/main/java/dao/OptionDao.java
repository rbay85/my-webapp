package main.java.dao;

import main.java.entity.Option;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;


@Repository
public class OptionDao {

    @PersistenceContext
    private EntityManager em;

    // добавляем опцию
    public void add( Option option ) {
        em.persist( option );
    }

    // удаляем опцию
    public void delete( int id ){
        Option option = em.find( Option.class, id );
        em.remove( option );
    }

    // редактируем опцию
    public void update( Option option ){
        em.merge( option );
    }

    // ищем опцию
    public Option get( int id ){
        return em.find( Option.class, id );
    }

    // выводим все опции
    @SuppressWarnings( "unchecked" )
    public List<Option> getAll(){
        Query query = em.createNativeQuery( "Option.getAll", Option.class );
        return query.getResultList();
    }
}
