package org.kevin.spring.beanpostprocessor.controller;

import org.kevin.spring.beanpostprocessor.core.anon.RoutingInjected;
import org.kevin.spring.beanpostprocessor.service.IHelloService;
import org.springframework.stereotype.Controller;

/**
 * Created by xiaju on 2019/6/30.
 */
@Controller
public class HelloController {

    @RoutingInjected
    private IHelloService helloService;

    public void sayHello(){

        helloService.sayHello();

    }

    public void sayHi(){

        helloService.sayHi();

    }

}
