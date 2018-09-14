package com.qjzd.network.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:
 * @Description:
 * @Date Create on 16:00 2018/9/14
 * @MOdifyBy:
 * @parameter
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    @RequestMapping("/to_upPass")
    public String to_upPass(){
        return "pages/user/update-pass";
    }
}
