package com.android.huminskiy1325.creptography1.Cryptography;

import java.util.Random;

/**
 * Created by cubru on 26.04.2017.
 */

public class CryptographyGamma implements Cryptography {

    private int keyGamma;

    public CryptographyGamma(int keyGamma) {
        this.keyGamma = keyGamma;
    }

    @Override
    public String encrypt(String input) {
        return crypt(input);
    }

    @Override
    public String decrypt(String input) {
        return crypt(input);
    }

    private String crypt(String input) {
        StringBuilder outputText = new StringBuilder();
        Random random = new Random(keyGamma);
        for (char letter : input.toCharArray()) {
            int gamma = random.nextInt() ^ (int) letter;
            outputText.append((char) gamma);
        }
        return outputText.toString();
    }
}
