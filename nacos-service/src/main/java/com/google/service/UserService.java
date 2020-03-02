package com.google.service;

import com.google.util.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author wk
 * @Description:
 * @date 2020/3/2 16:07
 **/
@RestController
@Slf4j
public class UserService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplate restTemplateRibbon;

    /**
     * 手写本地负载均衡（轮询）
     */
    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/getUsers")
    public String getUsers() {
        log.info("调用生产者服务UserService-getUsers");
        //1.根据服务名称从注册中心获取集群列表地址
        //2.列表任意选择一个 实现本地rpc调用
        List<ServiceInstance> discoveryClientInstances = discoveryClient.getInstances("nacos-users");
        ServiceInstance serviceInstance = discoveryClientInstances.get(0);
        URI serviceInstanceUri = serviceInstance.getUri();
        String forObject = restTemplate.getForObject(serviceInstanceUri + "/getCustUacById", String.class);
        return "nacos-service: " + forObject;
    }

    @GetMapping("/getUserName")
    public String getUserName() {
        log.info("本地负载均衡算法-调用生产者服务UserService-getUserName");
        //1.根据服务名称从注册中心获取集群列表地址
        List<ServiceInstance> discoveryClientInstances = discoveryClient.getInstances("nacos-users");
        //2.列表任意选一个，实现本地RPC调用。采用本地负载均衡算法。
        //(需要考虑下服务是否可用，重新获取 递归？)
        ServiceInstance serviceInstance = loadBalancer.getSingleAddres(discoveryClientInstances);
        URI serviceInstanceUri = serviceInstance.getUri();
        String forObject = restTemplate.getForObject(serviceInstanceUri + "/getCustUacById", String.class);
        return "nacos-service: " + forObject + " " + serviceInstanceUri;
    }

    /**
     * @return
     * @LoadBalanced 结合restTemplateRibbon
     */
    @GetMapping("/getUserNameForRibbon")
    public String getUserNameForRibbon() {
        log.info("本地负载均衡算法Ribbon-调用生产者服务UserService-getUserName");
        String forObject = restTemplateRibbon.getForObject("http://nacos-users/getCustUacById", String.class);
        return "nacos-service: " + forObject;
    }

    /**
     * 返回nacos-service: RibbonServer{serviceId='nacos-users', server=192.168.10.161:8091, secure=false, metadata={}}
     *
     * @return
     */
    @GetMapping("/getLoadBalancerClient")
    public Object getLoadBalancerClient() {
        ServiceInstance instance = loadBalancerClient.choose("nacos-users");
        return "nacos-service: \n" + instance;
    }


}
