package models.Authentification;

import database.Entity;
import models.ServiceUser;

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

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<AuthUser> c = cb.createQuery(AuthUser.class);
//        Root<AuthUser> authUserRoot = c.from(AuthUser.class);
//        c.select(authUserRoot).where(cb.equal(authUserRoot.get()))
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

    public static long getUID(String token){
        EntityManager em = ENTITY.getEntity();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AuthUser> c = cb.createQuery(AuthUser.class);
        Root<AuthUser> authUser = c.from(AuthUser.class);
        //c.select(authUser).where( cb.equal( authUser.get("token"), token ) );
        c.select(authUser);
        Query query = em.createQuery( c ) ;
        List<AuthUser> list = (List<AuthUser>) query.getResultList();
        AuthUser selectedAuthUser = null;
        for(AuthUser user : list){
            if(user.getToken().equals(token)){
                selectedAuthUser=user;
            }
        }


        cb = em.getCriteriaBuilder();
        CriteriaBuilder cb2 = em.getCriteriaBuilder();
        CriteriaQuery<ServiceUser> c2 = cb2.createQuery(ServiceUser.class);
        Root<ServiceUser> userRoot = c2.from(ServiceUser.class);
       // c2.select(userRoot).where(cb2.equal(userRoot.get("authUser"), selectedAuthUser));
        c2.select(userRoot);
        Query query2 = em.createQuery(c2);
        List<ServiceUser> list2 = (List<ServiceUser>) query2.getResultList();
        for(ServiceUser user : list2){
            if(user.getAuthUser().getId().equals(selectedAuthUser.getId())){
                return user.getUid();
            }
        }
        return 0;

    }
}
