package com.cseu.server.gateway.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认降级处理
 */
@RestController
@Slf4j
public class DefaultHystrixController {
    @RequestMapping("/timeout")
    public Map<String,Object> defaultfallback(){
        log.info("降级操作...");
        Map<String,Object> map = new HashMap<>();
        map.put("code",500);
        map.put("data",new String[]{});
        map.put("message","服务繁忙，请稍后再试");
        log.info(map.toString());
        return map;
    }

}
