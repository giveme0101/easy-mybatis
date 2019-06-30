package org.kevin.spring.beanpostprocessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kevin.spring.beanpostprocessor.core.anon.RoutingInjected;
import org.kevin.spring.beanpostprocessor.service.IHelloService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class StartApplicationTests {

    @RoutingInjected
    private IHelloService helloService;

    @Test
    public void sayHello(){

        helloService.sayHello();
        helloService.sayHi();
        helloService.sayHaha();

    }

}
