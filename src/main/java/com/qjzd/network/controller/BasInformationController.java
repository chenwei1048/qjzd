package com.qjzd.network.controller;

import com.qjzd.network.result.Result;
import com.qjzd.network.service.BasInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class BasInformationController {

    @Autowired
    private BasInformationService informationService;

//    @RequestMapping("/update")
//    public Result<String> update(String context){
//        return  Result.error()
//    }
}
