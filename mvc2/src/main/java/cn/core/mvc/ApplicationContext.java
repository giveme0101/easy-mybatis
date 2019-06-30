package cn.core.mvc;/**
 * Created by Administrator on 2018/11/28.
 */

import cn.core.mvc.util.WordUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/** 上下文
 *
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name ApplicationContext
 * @Date 2018/11/28 16:03
 */
public class ApplicationContext {

    // bean
    private static Map<String, Object> BEANS = new HashMap<>();
    // properties
    private static Properties properties = null;

    private static void setBeans(Map beans){
        BEANS.putAll(beans);
    }

    private static void putBean(String name, Object bean){
        BEANS.put(name, bean);
    }

    private static void setProperties(Properties prop){
        properties = prop;
    }

    /**
     * 获取配置
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key, String defaultValue){

        return properties.getProperty(key, defaultValue);
    }

    /**
     * 根据class 获取bean
     *
     * @param clz
     * @return
     */
    public static <T> T getBean(Class<T> clz){

        Iterator<Map.Entry<String, Object>> it = BEANS.entrySet().iterator();
        while (it.hasNext()){

            Map.Entry<String, Object> entry = it.next();

            if (entry.getValue().getClass().getSimpleName().equals(clz.getSimpleName())){
                return (T) entry.getValue();
            }

            Class[] interfaces = entry.getValue().getClass().getInterfaces();
            for (Class i : interfaces){
                if (i.getSimpleName().equals(clz.getSimpleName())){
                    return (T) entry.getValue();
                }
            }
        }

        return null;
    }

    /**
     * 根据name获取bean
     *
     * @param name
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clz){

       T t =  (T) BEANS.get(name);
       if (null == t){
           t = (T) BEANS.get(WordUtil.firstWordToSupperCase(name));
       }

       return t;
    }
}
