package models.Authentification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.rmi.server.UID;
import java.util.List;

import static database.Entity.ENTITY;

public class AuthManager {


    public static boolean checkAuth(String id, String password){
        EntityManager em = ENTITY.getEntity();
        AuthUser authUser = em.find(AuthUser.class, id);
        return authUser.checkPassword(password);
    }

    public static boolean checkToken(String token){
        EntityManager em = ENTITY.getEntity();


      //  String sql = "SELECT * FROM AUTHUSER WHERE AUTHUSER.TOKEN = '"+token+"'";



        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AuthUser> c = cb.createQuery(AuthUser.class);
        Root<AuthUser> authUser = c.from(AuthUser.class);
        //c.select(authUser).where( cb.equal( authUser.get("token"), token ) );
        c.select(authUser);
        Query query = em.createQuery( c ) ;
        List<AuthUser> list = (List<AuthUser>) query.getResultList();
        System.out.println(list.size());
        for(AuthUser user : list){
            if(user.getToken().equals(token)){
                return true;
            }
        }
        return true;
    }

    public static String refreshToken(String id){
        EntityManager em = ENTITY.getEntity();
        AuthUser authUser = em.find(AuthUser.class, id);
        return authUser.refreshToken();
    }
}
