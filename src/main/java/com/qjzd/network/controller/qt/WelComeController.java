package com.qjzd.network.controller.qt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:
 * @Description:
 * @Date Create on 17:02 2018/11/27
 * @MOdifyBy:
 * @parameter
 */
//@Controller
public class WelComeController {
    @RequestMapping
    public String welcome(){
        return "index";
    }
}
