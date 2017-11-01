package models.Authentification;

import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthUser {

    private UID token;
    private String id;
    private String hashPassword;


    private AuthUser(String id, String password){
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

}
