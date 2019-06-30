package org.kevin.spring.beanpostprocessor.service;

import org.kevin.spring.beanpostprocessor.core.anon.RoutingSwitch;

/**
 * Created by xiaju on 2019/6/30.
 */
@RoutingSwitch("V1")
public interface IHelloService {

    @RoutingSwitch("V2")
    void sayHello();

    void sayHi();

    @RoutingSwitch("V3")
    void sayHaha();

}
