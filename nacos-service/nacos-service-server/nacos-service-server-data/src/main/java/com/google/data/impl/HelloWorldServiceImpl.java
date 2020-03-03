package com.google.data.impl;

import com.google.api.HelloWorldService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wk
 * @Description:
 * @date 2020/3/3 13:12
 **/
@RestController
public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public String helloWorld(Integer age) {
        return "hello world!" + " " + age;
    }
}
