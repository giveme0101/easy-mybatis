package cn.tech.mvc.listener;/**
 * Created by Administrator on 2018/11/28.
 */

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name EventSubject
 * @Date 2018/11/28 14:50
 */
public interface EventSubject {

    void addListener(ApplicationListener listener);

    void delListener(ApplicationListener listener);

    void notifyListener();
}
