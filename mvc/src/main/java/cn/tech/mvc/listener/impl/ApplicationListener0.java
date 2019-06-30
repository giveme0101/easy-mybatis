package cn.tech.mvc.listener.impl;
/**
 * Created by Administrator on 2018/11/28.
 */

import cn.tech.mvc.listener.ApplicationListener;
import cn.tech.mvc.DispatcherServlet;
import cn.tech.mvc.annotation.Bean;

import java.util.logging.Logger;

/**
 * 监听器，@bean将在bean创建成功后自动添加到被观察者中
 *
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name ApplicationListener0
 * @Date 2018/11/28 13:15
 */
@Bean
public class ApplicationListener0 implements ApplicationListener {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void afterStartup() {

        logger.info(String.format("Server startup at %dms", (System.currentTimeMillis() - DispatcherServlet.THREAD_LOCAL.get())));
    }
}
