package main.java.dao;


import com.sun.jersey.spi.inject.Inject;
import main.java.entity.Tariff;
import org.springframework.stereotype.Repository;

import javax.persistence.*;


//@Repository
public class RestTariffDao {

//    @PersistenceContext
//    private EntityManager em ;

//    @Inject
//    private EntityManager em ;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersUnit");
    private EntityManager em = emf.createEntityManager();


    // ищем клиента
    public Tariff get( int id ){

        return em.find( Tariff.class, id );
    }

}
