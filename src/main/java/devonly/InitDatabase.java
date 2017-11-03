package devonly;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class InitDatabase {

    /**
     * This method should be only eexecuted by developper. it consist to generate a schema from our models.
     * It will drop the previous database so it will drop all data too.
     * @throws 
     */
    public static void main(String[] args) {
        //Persistence.generateSchema("association", null);
        EntityManager em = Persistence.createEntityManagerFactory("association").createEntityManager();
//		try {
//			@SuppressWarnings("resource")
//			Scanner input = new Scanner(new File("src\\main\\resources\\init.sql"));
//	        Query q = em.createNativeQuery("BEGIN " + input.nextLine() + " END;");
//	        q.executeUpdate();
//		} catch (FileNotFoundException e) {
//			System.err.println("Erreur lors de l'ouverture du fichier sql");
//			e.printStackTrace();
//		}
        em.close();
        
        System.out.println("Database created");
    }
}
