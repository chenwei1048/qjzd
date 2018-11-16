package com.qjzd.network.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:
 * @Description:
 * @Date Create on 15:18 2018/11/16
 * @MOdifyBy:
 * @parameter
 */
@RequestMapping("/index")
@Controller
public class IndexController {

    @RequestMapping
    public String index(){
        return "/pages/qt/index";
    }


    @RequestMapping("/header")
    public String header(){
        return "/pages/qt/public/header";
    }

    @RequestMapping("/footer")
    public String footer(){
        return "/pages/qt/public/footer";
    }
}
