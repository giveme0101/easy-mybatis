package cn.tech.controller;
/**
 * Created by Administrator on 2018/11/30.
 */

import cn.core.mvc.annotation.Autowired;
import cn.core.mvc.annotation.Controller;
import cn.core.mvc.annotation.RequestMapping;
import cn.core.mvc.annotation.ResponseBody;
import cn.tech.entity.User;
import cn.tech.service.UserService;

import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name HomePageController
 * @Date 2018/11/30 9:12
 */
@Controller
@RequestMapping(value = "/home")
public class HomePageController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "")
    public Object index(){

        List<User> userList =  userService.getUsers();
        return userList;
    }
}
