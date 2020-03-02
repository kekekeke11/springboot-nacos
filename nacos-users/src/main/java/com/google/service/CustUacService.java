package com.google.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wk
 * @Description:
 * @date 2020/3/2 20:40
 **/
@RestController
public class CustUacService {

    @Value("${server.port}")
    private String serverPort;


    @GetMapping(value = "/getCustUacById")
    public String getCustUacById() {
        return "夜雨声烦黄少天"+serverPort;
    }
}
