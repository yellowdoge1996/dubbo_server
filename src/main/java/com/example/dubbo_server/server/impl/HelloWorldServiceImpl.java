package com.example.dubbo_server.server.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo_server.api.HelloWorldService;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = HelloWorldService.class,
interfaceName = "helloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String name) {
        return "Hello"+name;
    }
}
