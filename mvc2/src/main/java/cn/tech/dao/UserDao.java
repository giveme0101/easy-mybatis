package cn.tech.dao;
/**
 * Created by Administrator on 2018/11/28.
 */

import cn.core.ibaits.annotation.Dao;
import cn.tech.entity.User;

import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name UserDao
 * @Date 2018/11/28 10:07
 */
@Dao
public interface UserDao {

        User getById(String id);
        List<User> findList();

}
