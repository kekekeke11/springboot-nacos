package com.google;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wk
 * @Description:
 * @date 2020/3/2 20:49
 **/
@SpringBootApplication
@EnableFeignClients
public class NacosCustUacApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosCustUacApplication.class, args);
    }
}
