package com.qjzd.network.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:
 * @Description:
 * @Date Create on 15:38 2018/11/15
 * @MOdifyBy:
 * @parameter
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/testJsp")
    public String testJsp(Model model) {
        model.addAttribute("message", "this is index jsp page");
        return "test";
    }
    @GetMapping("/testThemleaf")
    public String testThemleaf(Model model) {
        model.addAttribute("message", "this is index jsp page");
        return "thymeleaf/test";
    }
    @GetMapping("/testVue")
    public String testVue(Model model) {
        model.addAttribute("message", "this is index jsp page");
        return "vue/testVue";
    }
}
