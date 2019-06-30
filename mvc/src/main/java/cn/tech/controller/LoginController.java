package cn.tech.controller;
/**
 * Created by Administrator on 2018/11/27.
 */

import cn.tech.mvc.annotation.*;
import cn.tech.service.UserService;

import java.util.HashMap;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name LoginController
 * @Date 2018/11/27 14:08
 */

@Controller
@RequestMapping(value = {"/auth"})
public class LoginController {

    @Id("userService")
    private UserService userServices;

    @ResponseBody
    @RequestMapping(value = {"", "/login"})
    public Object login(@RequestParam(value = "name") final String username,
                        @RequestParam(value = "pwd") final String password){

        final Boolean loginResult = userServices.loginByUserName(username, password);

        return new HashMap<String, Object>(2){{
            put("username", username);
            put("result", loginResult);
        }};
    }
}
