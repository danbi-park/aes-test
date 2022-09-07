package com.example.demo.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class Hash {

    public void test(){
        AESCrypto util = null;
        String masterKey = "12345678912345678900!@#$%^&*()25";

        System.out.println("Original KEY : "+masterKey);

        System.out.println();

        for(int i=0; i<3; i++)
        {
            try {
                int keyLen = 16 + (i*8);
                util = new AESCrypto(masterKey, keyLen);

                String c = util.aesEncode("19970121201071570271");
                System.out.println(c);

                String s = util.aesDecode(c);
                System.out.println(s);

                System.out.println();

                Thread.sleep(500);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

    }
    }
}
