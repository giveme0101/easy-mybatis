package cn.tech.service.impl;
/**
 * Created by Administrator on 2018/11/27.
 */

import cn.tech.dao.UserDao;
import cn.tech.mvc.annotation.Autowired;
import cn.tech.mvc.annotation.Service;
import cn.tech.service.UserService;

import java.util.Map;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name UserServicesImpl
 * @Date 2018/11/27 14:09
 */

@Service
public class UserServicesImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public boolean loginByUserName(String username, String password){

        Map user = userDao.getUserByName(username);

        return password.equals(user.get("pwd"));
    }

}
