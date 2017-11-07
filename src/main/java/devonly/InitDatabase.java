package devonly;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class InitDatabase {

    /**
     * Cette méthode consiste à utiliser une persistence qui s'occupe de créer la base de donnée en fonction
     * de nos classes modèles
     *
     */
    public static void main(String[] args){
        //Persistence.generateSchema("association", null);
        EntityManager em = Persistence.createEntityManagerFactory("association_create").createEntityManager();
        em.close();
        System.out.println("Database created");
    }
}
