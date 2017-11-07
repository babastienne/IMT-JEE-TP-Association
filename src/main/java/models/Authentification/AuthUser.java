package models.Authentification;

import models.ServiceUser;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static database.Entity.ENTITY;

/**
 * Classe gérant la partie authentification de l'utilisateur
 */
@Entity
public class AuthUser {

    /**
     * Email de l'utilisateur. Clé primaire dans la DB
     */
    @Id
    private String id;
    /**
     * Token d'authentification. Pour éviter de se reconnecter à chaque fois
     */
    private String token;
    /**
     * Mot de passe hashé en md5
     */
    private String hashPassword;
    /**
     * Lien vers le ServiceUser (modèle métier de l'utilisateur) correspondant à l'AuthUser
     */
    @OneToOne(fetch = FetchType.LAZY)
    private ServiceUser serviceUser;


    public AuthUser(){}

    /**
     * Constructeur
     * @param id email
     * @param password mot de passe
     */
    public AuthUser(String id, String password){
        this.token = UUID.randomUUID().toString().replaceAll("-", "");
        this.id = id;
        try {
            this.hashPassword = toHash(password);
        }catch(NoSuchAlgorithmException e){
            System.err.println("Erreur dans la generation du hash MD5 du mot de passe");
            e.printStackTrace();
        }
    }


    public ServiceUser getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    /**
     * Fonction permettant de hasher une chaine de charactère via md5
     * @param str chaine de charactère
     * @return valeur hashée
     * @throws NoSuchAlgorithmException
     */
    private String toHash(String str) throws NoSuchAlgorithmException {
        StringBuffer hexString = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest();

        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0"
                        + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }


    /**
     * Vérifie si le mot de passe soumis correspond au mot de passe de l'utilisateur
     * @param password mot de passe
     * @return True si c'est bon
     */
    public boolean checkPassword(String password){
        boolean checked = false;
        try{
            checked = this.hashPassword.equals(this.toHash(password));
        }catch(NoSuchAlgorithmException e){
            System.err.println("Erreur dans la generation du hash MD5 du mot de passe");
            e.printStackTrace();
        }
        return checked;
    }

    /**
     * Regénère un token
     * @return nouveau token
     */
    public String refreshToken(){
        this.token =  UUID.randomUUID().toString().replaceAll("-", "");
        ENTITY.update(this);
        return this.token;
    }


}
