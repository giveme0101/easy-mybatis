package cn.kiven.ibatis.impl;/**
 * Created by xiaju on 2018/11/30.
 */

import cn.kiven.ibatis.Configuration;
import cn.kiven.ibatis.Executor;
import cn.kiven.ibatis.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name DefaultSqlSession
 * @Date 2018/11/30 22:25
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration config = null;

    private Executor executor = null;

    public DefaultSqlSession(Configuration config){
        this.config = config;
        executor = new DefaultExecutor(config);
    }

    public <T> T selectOne(String statement, Object args) {

        List<T> t = selectList(statement, args);
        if (null == t || t.size() == 0) return null;
        if (t.size() > 1) throw new RuntimeException("too many results!");
        return t.get(0);
    }

    public <E> List<E> selectList(String statement, Object args) {
        return executor.query(statement, args);
    }

    public <T> T getMapper(Class<T> clz) {
        return (T) Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Class returnType = method.getReturnType();
                if (Collection.class.isAssignableFrom(returnType)){
                    return selectList(method.getDeclaringClass().getName() + "." + method.getName(), args == null ? null : args[0]);
                }

                return selectOne(method.getDeclaringClass().getName() + "." + method.getName(), args == null ? null : args[0]);
            }
        });
    }
}
