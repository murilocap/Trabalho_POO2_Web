package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnFactory {
    
    public static EntityManager getEntityManager(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("AcademicoPoo2PU");
        EntityManager em = factory.createEntityManager();
        
        return em;
    }
    
}
