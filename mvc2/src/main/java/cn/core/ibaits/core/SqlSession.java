package cn.core.ibaits.core;
/**
 * Created by Administrator on 2018/11/30.
 */

import cn.core.ibaits.core.entity.Function;
import cn.core.ibaits.core.entity.MapperBean;
import cn.core.ibaits.core.impl.Executor;
import cn.core.mvc.annotation.Bean;

import java.lang.reflect.*;
import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name SqlSession
 * @Date 2018/11/30 11:31
 */
@Bean
public class SqlSession {

    Executor executor = new Executor();

    public <T> T selectOne(String sql, Object param, Class rtn){

        List rs = (List<T>) executor.query(sql, param, rtn);
        return (T) (null == rs || rs.size() < 1 ? null : rs .get(0));
    }

    public <T> List<T> findList(String sql, Object param, Class rtn){

        return (List<T>) executor.query(sql, param, rtn);
    }

    public <T> T getMapper (Class<T> clz){

        return (T) Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                String dao = method.getDeclaringClass().getSimpleName();
                MapperBean mapperBean = Config.readMapper("/mapper/" + dao + ".xml");
                List<Function> functionList = mapperBean.getFunctionList();

                for (Function function : functionList){
                    if (function.getFunName().equals(method.getName())){

                        if ("select".equals(function.getSqlType())){
                           return select(function, method, args);
                        }

                    }
                }

                return null;
            }
        });
    }

    private <T> T select (Function function, Method method, Object args){

        Object rtnType = function.getResultType();
        if (rtnType instanceof List){
            // 通过反射获取集合中元素类型
            Type type = method.getGenericReturnType();
            Class<T> entityClass = (Class<T>)((ParameterizedType) type).getActualTypeArguments()[0];
            return (T) findList(function.getSql(), args, entityClass);
        } else {
            return selectOne(function.getSql(), args, rtnType.getClass());
        }
    }
}
