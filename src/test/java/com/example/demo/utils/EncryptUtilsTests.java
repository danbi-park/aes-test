package com.example.demo.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EncryptUtilsTests {
    @Autowired
    private EncryptUtils encryptUtils;

    @Autowired
    private Hash hash;

    @Test
    void encryptAES256() {

        String encryptedAES256 = "";
        String decryptedAES256 = "";

        try {
            encryptedAES256 = encryptUtils.encryptAES256("danbi");

            System.out.println("AES256 암호화: " + encryptedAES256);

            decryptedAES256 = encryptUtils.decryptAES256(encryptedAES256);

            System.out.println("AES256 복호화: " + decryptedAES256);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    void encryptAES128() {

        String encryptedAES128 = "";
        String decryptedAES128 = "";
        String key = "aijinetaijinetaijinetaijinet2022"; //32 byte

        try {
            encryptedAES128 = EncryptUtils.encryptAES128("19970121201071570271", key.getBytes());

            System.out.println("AES128 암호화: " + encryptedAES128);

            decryptedAES128 = EncryptUtils.decryptAES128(encryptedAES128, key.getBytes());

            System.out.println("AES128 복호화: " + decryptedAES128);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void testt(){
        hash.test();
    }

}