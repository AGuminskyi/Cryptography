package com.android.huminskiy1325.creptography1.Cryptography;

import
        android.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by Dima on 24.05.2017.
 */
public class CryptographyRSA implements Cryptography {
    private Cipher encryptCipher;
    private Cipher decryptCipher;


    public CryptographyRSA(String secretPhrase) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {
        encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");


        KeyGenerator keyGenerator = KeyGenerator.getInstance(secretPhrase);
        keyGenerator.init(128);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.genKeyPair();


        encryptCipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        decryptCipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
    }

    @Override
    public String encrypt(String input) throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        byte[] inputBytes = input.getBytes("UTF-8");
        byte[] cipherText = encryptCipher.doFinal(inputBytes);
        return  Base64.encodeToString(cipherText, Base64.DEFAULT);
    }


    @Override
    public String decrypt(String input) throws BadPaddingException, IllegalBlockSizeException, IOException {
        byte[] inputBytes =  Base64.decode(input.getBytes(), Base64.DEFAULT);
        byte[] decryptedKeyBytes = decryptCipher.doFinal(inputBytes);
        return new String(decryptedKeyBytes,"UTF8");
    }
}

