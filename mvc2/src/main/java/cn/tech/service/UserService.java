package cn.tech.service;/**
 * Created by Administrator on 2018/11/28.
 */

import cn.tech.entity.User;

import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name UserService
 * @Date 2018/11/28 11:01
 */
public interface UserService {

    boolean loginByUserName(String username, String password);

    List<User> getUsers();
}
