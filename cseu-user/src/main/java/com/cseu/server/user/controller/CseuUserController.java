package com.cseu.server.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CseuUserController {


    private static final Logger l= LoggerFactory.getLogger(CseuUserController.class);


    @GetMapping("/{no}")
    @ResponseBody
    public Object findUser(@PathVariable(value = "no") int no){
//        CseuGuest user=CseuGuest.builder().nickName("三五").id(1L).build();
//        l.info("登陆请求参数{}",no);
        return null;
    }


}
