package proxy;
/**
 * Created by Administrator on 2018/11/29.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name proxyFactory1.ProxyFactory0
 * @Date 2018/11/29 10:13
 */
public class ProxyFactory0 {

    private Person target;
    private BeforeAdvance beforeAdvance;
    private AfterAdvance afterAdvance;

    public Person createProxy(){

        return (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (null != beforeAdvance) beforeAdvance.before();

                target.say(args[0].toString());

                if (null != afterAdvance) afterAdvance.after();

                return null;
            }
        });
    }

    // 通过 builder 模式创建工厂
    public static class Builder {

        private ProxyFactory0 instance = new ProxyFactory0();

        public Builder(){}

        public Builder setTarget(Person person){
            instance.target = person;
            return this;
        }

        public Builder setBeforeAdvance(BeforeAdvance beforeAdvance){
            instance.beforeAdvance = beforeAdvance;
            return this;
        }

        public Builder setAfterAdvance(AfterAdvance afterAdvance){
            instance.afterAdvance = afterAdvance;
            return this;
        }

        public ProxyFactory0 build(){
            return instance;
        }
    }
}
