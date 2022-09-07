package com.example.demo.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class AESCrypto {
    private String iv;
    private Key keySpec;

    public static int KEYSIZE_AES_128 = 16;
    public static int KEYSIZE_AES_192 = 24;
    public static int KEYSIZE_AES_256 = 32;

    public AESCrypto(final String key, final int keySize) throws Exception {

        byte[] keyBytes=null;
        byte[] b = key.getBytes(StandardCharsets.UTF_8);
        String strCipher=null;

        switch (keySize)
        {
            case 16:	// 128 bit
                strCipher = "AES-128";
                keyBytes = new byte[keySize];
                break;
            case 24:	// 192 bit
                strCipher = "AES-192";
                keyBytes = new byte[keySize];
                break;
            case 32:	// 256 bit
                strCipher = "AES-256";
                keyBytes = new byte[keySize];
                break;
        }
        if (keyBytes == null) {
            throw new Exception("암호화 방식이 올바르지 않습니다.");
        }
        System.out.println("Encrypt Type : "+strCipher);

        int inputKeyLength = key.length();
        if (inputKeyLength > keySize) {
            // 입력 받은 key string 길이가 실제 암호화 할 대상 key 길이보다 큰 경우, 암호화 키 길이에 맞게 조정
            System.arraycopy(b, 0, keyBytes, 0, keySize);
        }
        else if (inputKeyLength < keySize) {
            throw new Exception("Key 길이가 올바르지 않습니다.");
        }
        else {
            // 입력 받은 key string 길이가, 실제 암호화 할 대상 key 길이와 동일한 경우
            System.arraycopy(b, 0, keyBytes, 0, keySize);
        }

        // AES 암호화는 IV 값으로 16 byte 사용
        this.iv = key.substring(0, 16);

        // KeySpec 생성 시 입력하는 키 길이에 따라 AES-128,192,256 방식으로 자동 설정 됨
        this.keySpec = new SecretKeySpec(keyBytes, "AES");

        System.out.println("KEY : "+new String(keyBytes, "UTF-8"));
        System.out.println("IV : "+this.iv);
    }

    public String aesEncode(String str) throws Exception {
        System.out.println("INPUT : "+str);

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, this.keySpec, new IvParameterSpec(this.iv.getBytes(StandardCharsets.UTF_8)));
        byte[] encrypted = c.doFinal(str.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(encrypted));
    }

    public String aesDecode(String str) throws Exception {
        System.out.println("INPUT : "+str);

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, this.keySpec, new IvParameterSpec(this.iv.getBytes(StandardCharsets.UTF_8)));
        byte[] decrypted = c.doFinal(Base64.getDecoder().decode(str));
        return new String(decrypted, "UTF-8");
    }
}
