package cn.tech.mvc.core.impl;/**
 * Created by Administrator on 2018/11/28.
 */

import cn.tech.mvc.core.IOCInvoke;
import cn.tech.mvc.annotation.Autowired;
import cn.tech.mvc.annotation.Id;
import cn.tech.mvc.util.WordUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name IOCExecutor
 * @Date 2018/11/28 9:47
 */
public class DefaultIOCInvoker implements IOCInvoke {

    @Override
    public void invoke(Map<String, Object> beans) {

        for (Map.Entry en : beans.entrySet()){

            Field[] fields = en.getValue().getClass().getDeclaredFields();
            for (Field f : fields){

                String beanName = null;

                if (f.isAnnotationPresent(Autowired.class)) {
                    beanName = f.getType().getSimpleName();
                }

                if (f.isAnnotationPresent(Id.class)) {
                    beanName = f.getAnnotation(Id.class).value();
                }

                if (null == beanName)
                    continue;

                try {
                    Object bean = beans.get(beanName);
                    if (null == bean)
                        bean = beans.get(WordUtil.firstWordToSupperCase(beanName));
                    if (null == bean){
                        continue;
                    }
                    f.setAccessible(true); // 设置可操作private属性
                    f.set(en.getValue(), bean);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
