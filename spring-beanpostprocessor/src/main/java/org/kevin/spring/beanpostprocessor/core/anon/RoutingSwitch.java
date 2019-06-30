package org.kevin.spring.beanpostprocessor.core.anon;/**
 * Created by xiaju on 2019/6/30.
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name RoutingSwitch
 * @Date 2019/06/30 21:02
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingSwitch {
    /**
     * 在配置系统中开关的属性名称，应用系统将会实时读取配置系统中对应开关的值来决定是调用哪个版本
     * @return
     */
    String value() default "";
}