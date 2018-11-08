package com.qjzd.network.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:
 * @Description:
 * @Date Create on 9:49 2018/11/8
 * @MOdifyBy:
 * @parameter
 */
@Controller
@RequestMapping("/bas")
public class BasController {

    @RequestMapping("/introduce")
    public String introduce(){
        return "pages/bas/add";
    }
}
