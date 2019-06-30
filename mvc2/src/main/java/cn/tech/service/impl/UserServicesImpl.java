package cn.tech.service.impl;
/**
 * Created by Administrator on 2018/11/27.
 */

import cn.core.ibaits.core.SqlSession;
import cn.core.mvc.annotation.Autowired;
import cn.core.mvc.annotation.Service;
import cn.tech.dao.UserDao;
import cn.tech.entity.User;
import cn.tech.service.UserService;

import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name UserServicesImpl
 * @Date 2018/11/27 14:09
 */

@Service
public class UserServicesImpl implements UserService {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private UserDao userDao;

    public boolean loginByUserName(String username, String password){

        UserDao userDao1 = sqlSession.getMapper(UserDao.class);
        User user = userDao1.getById(username);
        if (null != user)
            return password.equals(user.getName());

        return Boolean.FALSE;
    }

    @Override
    public List<User> getUsers() {

        List<User> users = userDao.findList();

        return users;
    }
}
