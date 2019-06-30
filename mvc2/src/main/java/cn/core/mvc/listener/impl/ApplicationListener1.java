package cn.core.mvc.listener.impl;
/**
 * Created by Administrator on 2018/11/28.
 */

import cn.core.mvc.ApplicationContext;
import cn.core.mvc.annotation.Bean;
import cn.core.mvc.listener.ApplicationListener;
import cn.tech.service.UserService;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name ApplicationListener0
 * @Date 2018/11/28 13:15
 */
@Bean
public class ApplicationListener1 implements ApplicationListener {

    @Override
    public void afterStartup() {

        System.out.println("启动定时任务。。");
        System.out.println(ApplicationContext.getBean(UserService.class));
        System.out.println(ApplicationContext.getBean("userService", UserService.class));
        System.out.println(ApplicationContext.getProperty("basePackage", ""));

    }
}
