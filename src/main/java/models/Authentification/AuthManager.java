package models.Authentification;

import javax.persistence.EntityManager;

import static database.Entity.ENTITY;

public class AuthManager {


    public static boolean checkAuth(String id, String password){
        EntityManager em = ENTITY.getEntity();
        AuthUser authUser = em.find(AuthUser.class, id);
        return authUser.checkPassword(password);
    }
}
