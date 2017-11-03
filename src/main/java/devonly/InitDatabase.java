package devonly;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class InitDatabase {

    /**
     * This method should be only eexecuted by developper. it consist to generate a schema from our models.
     * It will drop the previous database so it will drop all data too.
     */
    public static void main(String[] args){
        //Persistence.generateSchema("association", null);
        EntityManager em = Persistence.createEntityManagerFactory("association").createEntityManager();
        em.close();
        System.out.println("Database created");
    }
}
