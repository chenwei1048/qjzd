package com.qjzd.network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:
 * @Description:
 * @Date Create on 14:18 2018/9/14
 * @MOdifyBy:
 * @parameter
 */
@Controller
@RequestMapping("/home")
public class HomeController {


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "pages/welcome";
    }

}
