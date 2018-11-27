package com.qjzd.network.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.qjzd.network.service.LeaveWordService;
import com.qjzd.network.service.LookCountService;
import com.qjzd.network.service.NewsinformationService;
import com.qjzd.network.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private NewsinformationService newsinformationService;

    @Autowired
    private LeaveWordService leaveWordService;

    @Autowired
    private LookCountService lookCountService;

    @RequestMapping("/index")
    public String index(){
        return "pages/admin/index";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model){
        model.addAttribute("proCount",productService.selectCount());
        model.addAttribute("newsCount",newsinformationService.selectCount());
        JSONObject param = new JSONObject();
        model.addAttribute("leaveWordCount",leaveWordService.selectCount(param));
        param.put("isRead","0");
        model.addAttribute("noReadleaveWordCount",leaveWordService.selectCount(param));
        return "pages/admin/welcome";
    }


}
