package cn.tech.controller;
/**
 * Created by Administrator on 2018/11/30.
 */

import cn.tech.mvc.annotation.Autowired;
import cn.tech.mvc.annotation.Controller;
import cn.tech.mvc.annotation.RequestMapping;
import cn.tech.service.UserService;

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

    @RequestMapping(value = "")
    public String index(){


        return "hello";
    }
}
