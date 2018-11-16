package com.qjzd.network.controller;

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
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "pages/welcome";
    }

    @RequestMapping("/product/type")
    public String productType(){
        return "pages/product/type";
    }

}
