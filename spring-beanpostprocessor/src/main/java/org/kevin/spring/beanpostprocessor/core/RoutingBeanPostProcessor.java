package org.kevin.spring.beanpostprocessor.core;/**
 * Created by xiaju on 2019/6/30.
 */

import org.kevin.spring.beanpostprocessor.core.anon.RoutingInjected;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name RoutingBeanPostProcessor
 * @Date 2019/06/30 21:07
 */
@Component
public class RoutingBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(RoutingInjected.class)) {
                if (!f.getType().isInterface()) {
                    throw new BeanCreationException("RoutingInjected field must be declared as an interface:" + f.getName()
                            + " @Class " + clazz.getName());
                }
                try {
                    this.handleRoutingInjected(f, bean, f.getType());
                } catch (IllegalAccessException e) {
                    throw new BeanCreationException("Exception thrown when handleAutowiredRouting", e);
                }
            }
        }
        return bean;
    }

    private void handleRoutingInjected(Field field, Object bean, Class type) throws IllegalAccessException {

        field.setAccessible(true);
        Map<String, Object> candidates = this.applicationContext.getBeansOfType(type);

        if (candidates.size() == 1) {
            field.set(bean, candidates.values().iterator().next());
            return;
        }

        field.set(bean, RoutingBeanProxyFactory.createProxy(type, candidates));
    }
}