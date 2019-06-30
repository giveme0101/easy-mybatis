package proxy;
/**
 * Created by Administrator on 2018/12/6.
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用cglib实现代理
 *
 *
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name ProxyFactory1
 * @Date 2018/12/06 10:29
 */
public class ProxyFactory1 {

    // 要增强的对象
    private Person original;

    private BeforeAdvance beforeAdvance;
    private AfterAdvance afterAdvance;

    private ProxyFactory1(){}

    public Object createProxy(){

        // enhance 与 jdk的proxy不同之处在于，enhance既可以代理接口又可以代理类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(original.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                Object result = null;

                if (null != beforeAdvance){
                    beforeAdvance.before();
                }

                // 必须使用methodProxy.invokeSuper 否则会无限递归
                result = methodProxy.invokeSuper(o, args);

                if (null != afterAdvance){
                    afterAdvance.after();
                }

                return result;
            }
        });
        enhancer.setClassLoader(original.getClass().getClassLoader());
        return enhancer.create();
    }

    public static class Builder {

        ProxyFactory1 proxyFactory1 = new ProxyFactory1();

        public Builder(){}

        public Builder setTarget(Person o){

            proxyFactory1.original = o;
            return this;
        }

        public Builder setBeforeAdvance(BeforeAdvance beforeAdvance){

            proxyFactory1.beforeAdvance = beforeAdvance;
            return this;
        }

        public Builder setAfterAdvance(AfterAdvance afterAdvance) {

            proxyFactory1.afterAdvance = afterAdvance;
            return this;
        }

        public ProxyFactory1 build () {

            return proxyFactory1;
        }
    }
}
