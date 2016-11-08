package main.java.dao;

import main.java.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;



@Repository
public class UserDao{

    @PersistenceContext
    private EntityManager em;


    // добавляем юзера
    public void add( User user ){
        em.persist( user );
    }

    // удаляем юзера
    public void delete( int id ){
        User user = em.find( User.class, id );
        em.remove( user );
    }

    // редактируем юзера
    public void update( User user ){
        em.merge( user );
    }

    // ищем клиента
    public User get( int id ){
        return em.find( User.class, id );
    }

    // выводим всех
    @SuppressWarnings( "unchecked" )
    public List<User> getAll(){
        Query query = em.createQuery( "SELECT u FROM User u" );
        return query.getResultList();
    }
}


