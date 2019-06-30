package cn.tech.dao;/**
 * Created by Administrator on 2018/11/28.
 */

import cn.tech.mvc.annotation.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name UserDao
 * @Date 2018/11/28 10:07
 */
@Component
public class UserDao {

    public Map<String, Object> getUserByName(final String name){

        return new HashMap<String, Object>(3){{
            put("name", name);
            put("pwd", "1234");
            put("gender", "W");
        }};
    }

}
