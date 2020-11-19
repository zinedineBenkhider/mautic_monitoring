package com.example.mautic_mobile.utils;

import android.util.Base64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
    private static String SALT="5823719632011845";
    public static String encrypt(String value)  {

        if(value == null){
            return value;
        }
        Key key = new SecretKeySpec(SALT.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherText = cipher.doFinal(value.getBytes("UTF-8"));
            return Base64.encodeToString(cipherText, Base64.NO_WRAP);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static String decrypt(String value) {

        if(value == null){
            return value;
        }
        Key key = new SecretKeySpec(SALT.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decode = Base64.decode(value, Base64.NO_WRAP);
            return new String(cipher.doFinal(decode), "UTF-8");
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
