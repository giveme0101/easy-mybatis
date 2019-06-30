package org.kevin.spring.beanpostprocessor.service.impl;/**
 * Created by xiaju on 2019/6/30.
 */

import org.kevin.spring.beanpostprocessor.service.IHelloService;
import org.springframework.stereotype.Service;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name HelloServiceV1
 * @Date 2019/06/30 20:56
 */
@Service
public class HelloServiceImplV1 implements IHelloService{

    @Override
    public void sayHello() {

        System.out.println("hello - V1");

    }

    @Override
    public void sayHi() {

        System.out.println("hi - V1");

    }

    @Override
    public void sayHaha() {

        System.out.println("haha - V1");

    }
}
