package com.cseu.server.gateway.config;




import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @title: ReteLimiterConfig
 * @projectName gateway
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-0820:59
 */

@Component
//使用配置文件的方式进行初始化
@ConfigurationProperties(prefix = "ratelimiter-conf")
public class ReteLimiterConfig {

    //处理速度
    private static final String DEFAULT_REPLENISHRATE="default.replenishRate";
    //容量
    private static final String DEFAULT_BURSTCAPACITY="default.burstCapacity";

    private Map<String , Integer> rateLimitMap = new ConcurrentHashMap<String , Integer>(){
        {
            put(DEFAULT_REPLENISHRATE , 10);
            put(DEFAULT_BURSTCAPACITY , 100);
        }
    };

    public Map<String, Integer> getRateLimitMap() {
        return rateLimitMap;
    }

    public void setRateLimitMap(Map<String, Integer> rateLimitMap) {
        this.rateLimitMap = rateLimitMap;
    }


}
