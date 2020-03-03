package com.google;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author wk
 * @Description:
 * @date 2020/3/2 16:06
 **/
@SpringBootApplication
public class DataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataServiceApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 本地负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplateRibbon() {
        return new RestTemplate();
    }
}
