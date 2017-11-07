package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Singleton d'EntityManager
 */
public class Entity {

    public static Entity ENTITY = new Entity();


    private EntityManager entityManager;

    public EntityManager getEntity(){
        if(this.entityManager != null){  // i don't know if java use lazy valuation (avoid null pointer exception)
            if(this.entityManager.isOpen()){
                return this.entityManager;
            }
        }else{
            EntityManagerFactory emf =  Persistence.createEntityManagerFactory("association");
            EntityManager em = emf.createEntityManager();
            this.entityManager = em;
            return em;
        }
        return null;
    }

    public void create(Object object){
        this.getEntity().getTransaction().begin();
        this.getEntity().persist(object);
        this.getEntity().getTransaction().commit();
    }

    public void update(Object object){
        this.getEntity().getTransaction().begin();
        this.getEntity().merge(object);
        this.getEntity().getTransaction().commit();

    }
}
