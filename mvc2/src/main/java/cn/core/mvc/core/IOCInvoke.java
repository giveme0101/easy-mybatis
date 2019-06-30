package cn.core.mvc.core;

import java.util.Map;

/** IOC注入器
 *
 * Created by Administrator on 2018/11/28.
 */
public interface IOCInvoke {

    public abstract void invoke(Map<String, Object> beans);
}
