package cn.core.mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/11/28.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id {

    String value() default "";
}
