package cn.tech.mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/11/27.
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    String[] value() default {};

}
