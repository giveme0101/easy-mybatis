package cn.kiven.ibatis;/**
 * Created by xiaju on 2018/11/30.
 */

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name Executor
 * @Date 2018/11/30 23:33
 */
public interface Executor {

    /**
     * 执行查询
     *
     * @param statement mybatis 的 namespace + id
     * @param args
     * @param <T>
     * @return
     */
    <T> T query(String statement, Object args);
}
