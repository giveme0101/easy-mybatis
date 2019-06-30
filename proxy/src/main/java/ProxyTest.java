/**
 * Created by Administrator on 2018/11/29.
 */

import org.junit.Test;
import proxy.*;
import proxy.impl.Teacher;

/**
 * 模拟AOP
 *
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name AOPRouter
 * @Date 2018/11/29 10:05
 */
public class ProxyTest {

    @Test
    public void test0() throws Exception {

        // 通过构造器创建代理工厂，代理teacher这个对象，对teacher的say（）做前后增强
        ProxyFactory0 proxyFactory0 = new ProxyFactory0.Builder()
                .setTarget(new Teacher())
                .setBeforeAdvance(new BeforeAdvance() {
                    @Override
                    public void before() {
                        System.out.println("before");
                    }
                }).setAfterAdvance(new AfterAdvance() {
                    @Override
                    public void after() {
                        System.out.println("after");
                    }
                }).build();

        Person master = proxyFactory0.createProxy();
        master.say(" ABCDE... ");
    }

    @Test
    public void test1(){

        ProxyFactory1 proxyFactory1 = new ProxyFactory1.Builder()
                .setTarget(new Teacher())
                .setBeforeAdvance(new BeforeAdvance() {
                    @Override
                    public void before() {
                        System.out.println("before");
                    }
                }).setAfterAdvance(new AfterAdvance() {
                    @Override
                    public void after() {
                        System.out.println("after");
                    }
                }).build();

        Teacher master = (Teacher) proxyFactory1.createProxy();
        master.say(" ABCDE... ");
    }

}
