package com.android.huminskiy1325.creptography1.Cryptography;

import com.android.huminskiy1325.creptography1.Cryptography.Cryptography;

/**
 * Created by cubru on 18.03.2017.
 */

public class CryptographyTritemius implements Cryptography {
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюяҐІЇґії',.!?:()-`; ";
    private static int key;
    private static KeyChooser keyChooser;
    private static int key_A;
    private static int key_B;
    private static int key_C;
    private static String key_Phrase;

    public enum KeyChooser {
        NotLinear_Choose,
        Phrase_Choose
    }

    public void setKey(int A_value, int B_value, int C_value, String keyPhrase_value) {
        if (keyChooser == KeyChooser.NotLinear_Choose) {
            key_A = A_value;
            key_B = B_value;
            key_C = C_value;
        } else {
            key_Phrase = keyPhrase_value;
        }
    }

    public CryptographyTritemius(KeyChooser keyChooser) {
        this.keyChooser = keyChooser;
    }


    @Override
    public String encrypt(String input) {
        StringBuilder encryptResult = new StringBuilder();
        if (keyChooser == KeyChooser.Phrase_Choose) {
            for (int i = 0; i < input.length(); i++)
                encryptResult.append(alphabet.charAt((mod(((alphabet.indexOf(input.charAt(i)) +
                        alphabet.charAt(key_Phrase.indexOf(key_Phrase.charAt(i % key_Phrase.length())))) % alphabet.length()), alphabet.length()))));

        } else {
            for (int i = 0; i < input.length(); i++) {
                key = key_A * mod(alphabet.charAt(input.indexOf(input.charAt(i))), input.length()) * mod(alphabet.charAt(input.indexOf(input.charAt(i))), input.length()) +
                        key_B * mod(alphabet.charAt(input.indexOf(input.charAt(i))), input.length()) + key_C;
                encryptResult.append(alphabet.charAt((mod(((alphabet.indexOf(input.charAt(i))
                        + key) % alphabet.length()), alphabet.length()))));
            }

        }
        return encryptResult.toString();
    }

    @Override
    public String decrypt(String input) {
        StringBuilder dencryptResult = new StringBuilder();
        if (keyChooser == KeyChooser.Phrase_Choose) {
            for (int i = 0; i < input.length(); i++)
                dencryptResult.append(alphabet.charAt((mod(((alphabet.indexOf(input.charAt(i)) -
                        alphabet.charAt(key_Phrase.indexOf(key_Phrase.charAt(i % key_Phrase.length()))))
                        % alphabet.length()), alphabet.length()))));
        } else {
            for (int i = 0; i < input.length(); i++) {
                key = key_A * mod(alphabet.charAt(input.indexOf(input.charAt(i))), input.length()) * mod(alphabet.charAt(input.indexOf(input.charAt(i))), input.length()) +
                        key_B * mod(alphabet.charAt(input.indexOf(input.charAt(i))), input.length()) + key_C;
                dencryptResult.append(alphabet.charAt((mod(((alphabet.indexOf(input.charAt(i))
                        - key) % alphabet.length()), alphabet.length()))));
            }
        }
        return dencryptResult.toString();
    }

    private int mod(int x, int y) {
        int result = x % y;
        return result < 0 ? result + y : result;
    }
}
