package cn.core.ibaits.core;
/**
 * Created by Administrator on 2018/11/30.
 */

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name Executeable
 * @Date 2018/11/30 11:17
 */
public interface Executeable {

    <T> T query(String sql, Object param, Class rtn);
}
