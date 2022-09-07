package com.example.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

@Controller
@RequestMapping("/")
@Log4j2
public class TestController {

    public static String getBase64EncodeString(String content){
        return Base64Utils.encodeToString(content.getBytes()); //TODO Base64 암호화된 문자열로 반환
    }

    @GetMapping("/index")
    public String home(Model model){
        String str_data = "danbi";
        String strBase64Encode = getBase64EncodeString(str_data);
        System.out.println(strBase64Encode);

        String str_result = new String(Base64Utils.decode(strBase64Encode.getBytes()));

        model.addAttribute("strr", strBase64Encode);
        model.addAttribute("str_result", str_result);

        return "index";
    }




}
