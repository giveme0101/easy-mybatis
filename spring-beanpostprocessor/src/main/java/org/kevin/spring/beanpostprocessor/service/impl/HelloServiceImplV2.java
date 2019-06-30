package org.kevin.spring.beanpostprocessor.service.impl;/**
 * Created by xiaju on 2019/6/30.
 */

import org.kevin.spring.beanpostprocessor.service.IHelloService;
import org.springframework.stereotype.Service;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name HelloServiceV2
 * @Date 2019/06/30 20:57
 */
@Service
public class HelloServiceImplV2 implements IHelloService {

    @Override
    public void sayHello() {

        System.out.println("hello - V2");

    }

    @Override
    public void sayHi() {

        System.out.println("hi - V2");

    }

    @Override
    public void sayHaha() {

        System.out.println("haha - V2");

    }
}
