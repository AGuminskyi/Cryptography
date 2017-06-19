package com.android.huminskiy1325.creptography1.Cryptography;

import com.android.huminskiy1325.creptography1.Cryptography.Cryptography;

/**
 * Created by cubru on 19.02.2017.
 */

public class CryptographyCaesar implements Cryptography {
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюяҐІЇґії',.!?:()-`; ";
    private int key;

    public CryptographyCaesar(int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String input) {
        StringBuilder encryptResult = new StringBuilder();
        for (int i = 0; i < input.length(); i++){
            encryptResult.append(alphabet.charAt((mod(((alphabet.indexOf(input.charAt(i)) + key)%alphabet.length()),alphabet.length()))));
        }
        return encryptResult.toString();
    }

    @Override
    public String decrypt(String input) {
        StringBuilder decryptResult = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            decryptResult.append(alphabet.charAt(mod(((alphabet.indexOf(input.charAt(i)) + alphabet.length() - key)%alphabet.length()),alphabet.length())));
        }
        return decryptResult.toString();
    }

    private int mod(int x, int y){
        int result = x % y;
        return result < 0 ? result + y : result;
    }
}
