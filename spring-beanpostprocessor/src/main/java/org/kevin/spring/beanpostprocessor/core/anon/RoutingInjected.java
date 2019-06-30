package org.kevin.spring.beanpostprocessor.core.anon;/**
 * Created by xiaju on 2019/6/30.
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name RoutingInject
 * @Date 2019/06/30 21:01
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingInjected {


}
