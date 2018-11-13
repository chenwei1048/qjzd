package com.qjzd.network.controller;

import com.alibaba.fastjson.JSONObject;
import com.qjzd.network.domain.BasInformation;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.BasInformationService;
import com.qjzd.network.util.CommonUtils;
import com.qjzd.network.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
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

    @Autowired
    private BasInformationService basInformationService;

    //关于我们
    @RequestMapping("/introduce")
    public String introduce(Model model){
        JSONObject param = new JSONObject();
        param.put("type", Constant.GYWM.getCode());
        BasInformation information = basInformationService.selectByExample(param);
        if(information==null){
            model.addAttribute("code", CodeMsg.NODATA.getCode());
            model.addAttribute("data","");
        }else{
            model.addAttribute("code", CodeMsg.SUCCESS.getCode());
            model.addAttribute("data",information.getContext());
        }
        model.addAttribute("type",Constant.GYWM.getCode());
        return "pages/bas/about";
    }



    @RequestMapping("/to_update")
    public String to_update(Long type,Model model){
        JSONObject param = new JSONObject();
        param.put("type", type);
        BasInformation information = basInformationService.selectByExample(param);
        if(information==null){
            model.addAttribute("code", CodeMsg.NODATA.getCode());
            model.addAttribute("data","");
        }else{
            model.addAttribute("data",information.getContext());
        }
        model.addAttribute("type",type);
        return "pages/bas/update";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(long type,String context){
        if(CommonUtils.isNull(type)){
            return Result.error(CodeMsg.TYPE_ERROR);
        }
        if(CommonUtils.isNull(context)||context.length()<20){
            return Result.error(CodeMsg.CONTEXT_ERROR);
        }
        JSONObject param = new JSONObject();
        param.put("type", type);
        BasInformation information = basInformationService.selectByExample(param);
        int res = -1;
        if(CommonUtils.isNull(information)){
            information = new BasInformation();
            if(!Constant.type.containsKey(type)){
                return Result.error(CodeMsg.TYPE_ERROR);
            }
            information.setTitle(Constant.type.get(type));
            information.setType(type);
            information.setContext(context);
            information.setCreatetime(new Date());
            res = basInformationService.add(information);
        }else {
            information.setContext(context);
            information.setCreatetime(new Date());
            res = basInformationService.update(information);
        }
        if(res>0){
            return Result.success(null);
        }else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }

    }
}
