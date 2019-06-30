package cn.kiven.dao;
/**
 * Created by xiaju on 2018/11/30.
 */

import cn.kiven.entity.User;

import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name UserMapper
 * @Date 2018/11/30 22:49
 */
public interface UserMapper {

    User getById(String id);
    List<User> findAllList();

}
