package com.android.huminskiy1325.creptography1.Cryptography;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by cubru on 18.05.2017.
 */

public class CryptographyStirl implements Cryptography {
    private HashMap<Character, LinkedList<String>> hashMap = new HashMap<>();
    private String inputKey;

    private void setHashMap() {
        char[] charKey = inputKey.toCharArray();

        String position = null;
        int CC = 0, SS = 0;
        for (char key : charKey) {
            if(CC == 30)
                CC = 0;
            if (key == '\n') {
                SS++;
            }
            if (hashMap.containsKey(key)) {
                hashMap.get(key).add(setPosition(CC, SS));
            } else {
                position = setPosition(CC, SS);
                LinkedList<String> list = new LinkedList<>();
                list.add(position);
                hashMap.put(key, list);
            }
            CC++;
        }
    }

    private String setPosition(int x, int y) {
        String cc, ss;
        if (x < 10) {
            cc = "0" + x;
        } else {
            cc = "" + x;
        }
        if (y < 10) {
            ss = "0" + y;
        } else {
            ss = "" + y;
        }
        return ss + cc;
    }


    public void setInputKey(String inputKey) {
        this.inputKey = inputKey;
        setHashMap();
    }

    @Override
    public String encrypt(String input) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < input.length(); i++) {
            if (hashMap.containsKey(input.charAt(i))) {
                result.append(hashMap.get(input.charAt(i)).get(random.nextInt(hashMap.get(input.charAt(i)).size())) + " ");
            }
            else{
                 result = new StringBuilder();
                return result.append(input.charAt(i)).toString();
            }

        }
        return result.toString();
    }

    @Override
    public String decrypt(String input) {
        StringBuilder result = new StringBuilder();
        int x, y;
        for(int i = 0; i < input.length(); i++)
        {
            x = Integer.parseInt(input.substring(i, i + 2));
            y = Integer.parseInt(input.substring(i + 2, i + 4));
            if(x > 0)
                x = x *30;
            char charSymbol =  inputKey.charAt(x + y);
            result.append(charSymbol);
            i += 4;
        }
        return result.toString();
    }
}
