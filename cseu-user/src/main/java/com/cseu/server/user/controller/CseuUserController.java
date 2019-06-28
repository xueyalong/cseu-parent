package com.cseu.server.user.controller;

import com.cseu.server.user.bean.CseuGuest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/u",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CseuUserController {


    private static final Logger l= LoggerFactory.getLogger(CseuUserController.class);


    @GetMapping("/1")
    @ResponseBody
    public Object findUser(@RequestParam String no){
        CseuGuest user= CseuGuest.builder().nickName("三五").id(1L).build();
        l.info("登陆请求参数{}",no);
        return user;
    }


}
