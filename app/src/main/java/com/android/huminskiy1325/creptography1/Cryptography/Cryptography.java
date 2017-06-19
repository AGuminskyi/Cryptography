package com.android.huminskiy1325.creptography1.Cryptography;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/**
 * Created by cubru on 19.02.2017.
 */

public interface Cryptography {
    String encrypt(String input) throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException;
    String decrypt(String input) throws BadPaddingException, IllegalBlockSizeException, IOException;
}
