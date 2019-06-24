package com.cseu.server.gateway.base.filter;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 薛亚龙
 * @title: 格式化API返回的数据
 * @projectName gataway
 * @description: 格式化API返回的数据
 */
//@Component
@Slf4j
public class WrapperResponseFilter implements GlobalFilter, Ordered {
    @Override
    public int getOrder() {
        return -2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        //释放掉内存
                        JSONObject jsonObject = new JSONObject();
                        String s = null;
                        try {
                            s = new String(content,"utf-8");
                            jsonObject.put("code", originalResponse.getStatusCode().value());
                            jsonObject.put("data", JSON.toJSON(s, SerializeConfig.globalInstance));
                            jsonObject.put("message", "请求成功");
                        } catch (UnsupportedEncodingException e) {
                            log.error("服务出现异常，异常信息是:{}",e);
                        }finally {
                            DataBufferUtils.release(dataBuffer);
                        }
                        return bufferFactory.wrap(jsonObject.toString().getBytes());

                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }
}