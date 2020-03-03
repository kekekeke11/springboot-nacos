package com.google.util;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    ServiceInstance getSingleAddres(List<ServiceInstance> serviceInstances);
}
