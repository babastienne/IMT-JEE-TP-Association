package models.Authentification;

import models.ServiceUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static database.Entity.ENTITY;

@Entity
public class AuthUser {

    @Id
    private String id;
    private String token;
    private String hashPassword;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ServiceUser serviceUser;

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

    public boolean checkPassword(String password){
        boolean checked = false;
        try{
            checked = this.hashPassword.equals(this.toHash(password));
        }catch(NoSuchAlgorithmException e){
            System.err.println("Erreur dans la generation du hash MD5 du mot de passe");
            e.printStackTrace();
        }
        System.out.println("password good or not: "+Boolean.toString(checked));
        return checked;
    }

    public String refreshToken(){
        this.token =  UUID.randomUUID().toString().replaceAll("-", "");
        ENTITY.update(this);
        return this.token;
    }


}
