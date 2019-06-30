package cn.tech.mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/11/27.
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam{

    String value();
}
