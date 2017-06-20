package com.android.huminskiy1325.creptography1;

import com.android.huminskiy1325.creptography1.Cryptography.Cryptography;
import com.android.huminskiy1325.creptography1.Cryptography.CryptographyCaesar;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by cubru on 19.02.2017.
 */

public class TestCaesarEncription {

    @Test
    public void testEncription() throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cryptography cryptography = new CryptographyCaesar(5);
        String encriptedText;// = cryptography.encrypt("Hello world");
//        assertEquals("MjqqtEБtwqi",encriptedText);
        encriptedText = cryptography.encrypt("Чу, я слышу пушек гром!");
        assertEquals("Ьш(EіEцрҐэшEфшэйпEзхус ", encriptedText);
    }
}
