package com.qjzd.network.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:
 * @Description:
 * @Date Create on 9:06 2018/11/21
 * @MOdifyBy:
 * @parameter
 */
//@Controller
public class WelController {
    @RequestMapping
    public String index(){
        return "redirect:/login";
    }
}
