package com.example.pwmanager;

import android.util.Base64;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherUtils {
    static String iv = "ca4cb565b6b04229";
    static IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());

    static public String encrypt(String plain, String keyString) throws Exception {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(256);
        SecretKey key = makeSecretKey(keyString);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParamSpec);
        byte[] ciphertext = cipher.doFinal(plain.getBytes());
        byte[] iv = cipher.getIV();
        String encryptPassword = Base64.encodeToString(ciphertext, Base64.NO_PADDING);
        return encryptPassword;
    }

    static public String decrypt(String encString, String keyString) throws Exception {
        byte[] enc = Base64.decode(encString, Base64.NO_PADDING);
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(256);
        SecretKey key = makeSecretKey(keyString);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, key, ivParamSpec);
        byte[] plainText = cipher.doFinal(enc);
        return new String(plainText);
    }

    static public SecretKey makeSecretKey(String text) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(text.toCharArray(), text.getBytes(), 4096, 256);
        SecretKey originalKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return originalKey;
    }
}
