package com.example.dubbo_server;

import com.alibaba.dubbo.config.annotation.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboServerApplicationTests {
    @Autowired
    ApplicationContext applicationContext;
    @Test
    public void contextLoads() {
        Map<String, Object> beanMap = this.applicationContext.getBeansWithAnnotation(Service.class);
        System.out.println(beanMap);
    }

}
