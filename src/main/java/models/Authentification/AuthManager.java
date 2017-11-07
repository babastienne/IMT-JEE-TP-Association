package models.Authentification;

import models.ServiceUser;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import static database.Entity.ENTITY;

/**
 * Classe avec des méthodes statiques s'occupant à vérifier l'authenticité des paramètres de connexion
 */
public class AuthManager {

    /**
     * Vérifie si les paramètres de connexion sont bonnes
     * @param id adresse email
     * @param password mot de passe
     * @return True si c'est bon
     */
    public static boolean checkAuth(String id, String password){
        EntityManager em = ENTITY.getEntity();
        AuthUser authUser = em.find(AuthUser.class, id);

        return authUser.checkPassword(password);
    }

    /**
     * Vérifie si le token de connexion est valide
     * @param token token
     * @return True si c'est bon
     */
    public static boolean checkToken(String token){
        EntityManager em = ENTITY.getEntity();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AuthUser> c = cb.createQuery(AuthUser.class);
        Root<AuthUser> authUser = c.from(AuthUser.class);
        // Il était impossible de faire une requête en faisant un "WHERE table.token = token"
        // la requête nous renvoyait aucun résultat
        // On a alors décidé de parcourir une liste de tout les AuthUser
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

    /**
     * Regènere un token dans l'AuthUser
     * @param id email de l'utilisateur
     * @return token récemment généré
     */
    public static String refreshToken(String id){
        EntityManager em = ENTITY.getEntity();
        AuthUser authUser = em.find(AuthUser.class, id);
        return authUser.refreshToken();
    }

    /**
     * Récuperer la uuid de l'utilisateur selon son token
     * @param token token
     * @return uuid de l'utilisateur
     */
    public static long getUID(String token){
        EntityManager em = ENTITY.getEntity();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AuthUser> c = cb.createQuery(AuthUser.class);
        Root<AuthUser> authUser = c.from(AuthUser.class);
        // Il était impossible de faire une requête en faisant un "WHERE table.token = token"
        // la requête nous renvoyait aucun résultat
        // On a alors décidé de parcourir une liste de tout les AuthUser
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
        // Même problème ici
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
