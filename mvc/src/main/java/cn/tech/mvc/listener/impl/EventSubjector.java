package cn.tech.mvc.listener.impl;/**
 * Created by Administrator on 2018/11/28.
 */

import cn.tech.mvc.listener.ApplicationListener;
import cn.tech.mvc.listener.EventSubject;

import java.util.ArrayList;
import java.util.List;

/** 被观察者
 *
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name EventSubjector
 * @Date 2018/11/28 14:50
 */
public class EventSubjector implements EventSubject {

    private static final EventSubjector instance = new EventSubjector();

    private void EventSubject(){}

    // 观察者队列
    private List<ApplicationListener> listenerList = new ArrayList<>();

    public static EventSubjector getInstance(){
        return instance;
    }

    @Override
    public void addListener(ApplicationListener listener) {

        listenerList.add(listener);
    }

    @Override
    public void delListener(ApplicationListener listener) {
        int i = listenerList.indexOf(listener);
        if (i > 0){
            listenerList.remove(listener);
        }
    }

    @Override
    public void notifyListener() {

        for (ApplicationListener listener : listenerList){
            listener.afterStartup();
        }
    }

}
