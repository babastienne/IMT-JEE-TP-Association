package models.Authentification;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
public class AuthUser {

    @Id
    private String id;
    private UID token;
    private String hashPassword;


    public AuthUser(){}

    private AuthUser(String id, String password){
        this();
        this.token = new UID();
        this.id = id;
        try {
            this.hashPassword = toHash(password);
        }catch(NoSuchAlgorithmException e){
            System.err.println("Erreur dans la generation du hash MD5 du mot de passe");
            e.printStackTrace();
        }
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
        return checked;
    }

}
