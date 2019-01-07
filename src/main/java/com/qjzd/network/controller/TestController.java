package com.qjzd.network.controller;

import com.qjzd.network.util.MailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/um")
    public String testJsp(Model model) {
        return "pages/ue/index";
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

    @GetMapping("/mail")
    @ResponseBody
    public String sendMail(){
        MailUtil.sendMail("1048676598@qq.com","asg","dasgag");
        return null;
    }

}
