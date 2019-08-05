package com.tsf.demo.consumer;

import com.tencent.tsf.monitor.annotation.EnableTsfMonitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.tsf.route.annotation.EnableTsfRoute;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.tsf.auth.annotation.EnableTsfAuth;
import org.springframework.tsf.ratelimit.annotation.EnableTsfRateLimit;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import com.tencent.tsf.sleuth.annotation.EnableTsfSleuth;

@SpringBootApplication
@EnableDiscoveryClient // 使用服务注册发现时请启用
@EnableFeignClients // 使用Feign微服务调用时请启用
@EnableScheduling
@EnableTsfAuth
@EnableTsfRoute
@EnableTsfRateLimit
@EnableTsfSleuth
@EnableTsfMonitor
public class ConsumerApplication {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @LoadBalanced
    @Bean
    public AsyncRestTemplate asyncRestTemplate() {
        return new AsyncRestTemplate();
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}