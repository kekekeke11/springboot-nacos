package com.google.service;

import com.google.api.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wk
 * @Description:
 * @date 2020/3/3 13:21
 **/
@RestController
public class HelloService {

    @Autowired
    private HelloWorldService helloWorldService;

    /**
     * 调用feign服务
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        String s = helloWorldService.helloWorld(99);
        return "feignclient调用 " + s;
    }
}
