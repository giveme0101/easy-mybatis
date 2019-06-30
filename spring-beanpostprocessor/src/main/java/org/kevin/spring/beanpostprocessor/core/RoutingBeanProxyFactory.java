package org.kevin.spring.beanpostprocessor.core;/**
 * Created by xiaju on 2019/6/30.
 */

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.kevin.spring.beanpostprocessor.core.anon.RoutingSwitch;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name RoutingBeanProxyFactory
 * @Date 2019/06/30 21:08
 */
public class RoutingBeanProxyFactory {

    public static Object createProxy(Class targetClass, Map<String, Object> beans) {

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(targetClass);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(targetClass, beans));

        return proxyFactory.getProxy();
    }

    static class VersionRoutingMethodInterceptor implements MethodInterceptor {

        private String interfaceName;
        private String classSwitch;
        private Map<String, Object> beans;

        public VersionRoutingMethodInterceptor(Class targetClass, Map<String, Object> beans) {

            String interfaceSimpleName = targetClass.getSimpleName().startsWith("I") ? targetClass.getSimpleName().substring(1) : targetClass.getSimpleName();
            this.interfaceName = StringUtils.uncapitalize(interfaceSimpleName);

            this.beans = beans;

            if(targetClass.isAnnotationPresent(RoutingSwitch.class)){
                classSwitch = ((RoutingSwitch) targetClass.getAnnotation(RoutingSwitch.class)).value();
            }
        }


        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {

            Method method = invocation.getMethod();

            // 优先使用方法的注解
            String methodClassSwitch = classSwitch;

            if (method.isAnnotationPresent(RoutingSwitch.class)) {
                methodClassSwitch = method.getAnnotation(RoutingSwitch.class).value();
            }

            if (StringUtils.isEmpty(methodClassSwitch)) {
                throw new IllegalStateException("RoutingSwitch's value is blank, method:" + method.getName());
            }

            return invocation.getMethod().invoke(getTargetBean(methodClassSwitch), invocation.getArguments());
        }

        public Object getTargetBean(String switchName) {

                return beans.get(interfaceName + "Impl" + switchName);
        }
    }
}

