package cn.kiven.ibatis;/**
 * Created by xiaju on 2018/11/30.
 */

import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name SqlSession
 * @Date 2018/11/30 22:13
 */
public interface SqlSession {

    /**
     * 查询单条数据
     *
     * @param statement 类全名（namespace） + sql Id
     * @param args
     * @param <T>
     * @return
     */
    <T> T selectOne(String statement, Object args);

    /**
     * 查询单条数据
     *
     * @param statement 类全名（namespace） + sql Id
     * @param args
     * @param <E>
     * @return
     */
    <E> List<E> selectList(String statement, Object args);

    /**
     *  根据接口类创建mapper代理对象
     *
     * @param clz mapper接口类
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> clz);

}
