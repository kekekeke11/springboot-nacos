package com.google.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nacos-service-server-data")
public interface HelloWorldService {

    @GetMapping("/helloWorld")
    String helloWorld(@RequestParam("age") Integer age);
}
