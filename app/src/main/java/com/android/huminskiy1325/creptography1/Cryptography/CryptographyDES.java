package com.android.huminskiy1325.creptography1.Cryptography;

import android.util.Base64;


import javax.crypto.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by cubru on 24.05.2017.
 */
public class CryptographyDES implements Cryptography {
    private Cipher encryptCipher;
    private Cipher decryptCipher;


    public CryptographyDES(SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        this.encryptCipher = Cipher.getInstance("DES");
        this.decryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
    }

    @Override
    public String encrypt(String input) {
        try {
            byte[] inputBytes = input.getBytes("UTF-8");
            byte[] enc = encryptCipher.doFinal(inputBytes);
            return Base64.encodeToString(enc,Base64.DEFAULT);
        } catch (UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decrypt(String input) {
        try {
            byte[] dec = Base64.decode(input.getBytes(), Base64.DEFAULT);
            byte[] utf8 = decryptCipher.doFinal(dec);
            return new String(utf8,"UTF8");
        } catch (IOException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
