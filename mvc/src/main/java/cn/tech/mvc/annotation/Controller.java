package cn.tech.mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/11/27.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {

    String value() default "";

}
