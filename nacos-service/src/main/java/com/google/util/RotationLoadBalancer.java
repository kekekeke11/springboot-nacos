package com.google.util;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wk
 * @Description:本地负载均衡算法
 * @date 2020/3/2 22:06
 **/
@Component
public class RotationLoadBalancer implements LoadBalancer {

    //原子类
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance getSingleAddres(List<ServiceInstance> serviceInstances) {
        int index = atomicInteger.incrementAndGet() % 2;
        ServiceInstance serviceInstance = serviceInstances.get(index);
        return serviceInstance;
    }

}
