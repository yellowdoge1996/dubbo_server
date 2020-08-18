package com.example.dubbo_server.server.impl;

import com.example.dubbo_server.api.HelloWorldService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Service
@DubboService(version = "1.0.0")
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String name) {
        return "Hello: " + name;
    }
}
