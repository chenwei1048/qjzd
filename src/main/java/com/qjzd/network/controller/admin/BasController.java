package com.qjzd.network.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.qjzd.network.domain.BasInformation;
import com.qjzd.network.domain.BasOrganization;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.BasInformationService;
import com.qjzd.network.service.BasOrganizationService;
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
@RequestMapping("/admin/bas")
public class BasController {

    @Autowired
    private BasInformationService basInformationService;

    @Autowired
    private BasOrganizationService organizationService;

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
        return "pages/admin/bas/about";
    }

    //联系我们
    @RequestMapping("/contact")
    public String contacts(Model model){
        model.addAttribute("data",organizationService.selectById(new Long(Constant.QJZD.getCode())));
        return "pages/admin/bas/contacts";

    }

    //修改联系我们
    @ResponseBody
    @RequestMapping("/update_contact")
    public Result update_contact(BasOrganization basOrganization){
        if(CommonUtils.isNull(basOrganization)){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        if(CommonUtils.isNull(basOrganization.getTitle())){
            return Result.error(CodeMsg.TITLE_ERROR);
        }
        if(CommonUtils.isNull(basOrganization.getContacts())){
            return Result.error(CodeMsg.CONTACTS_ERROR);
        }if(CommonUtils.isNull(basOrganization.getPhone())){
            return Result.error(CodeMsg.PHONE_ERROR);
        }
        if(CommonUtils.isNull(basOrganization.getAddress())){
            return Result.error(CodeMsg.ADDRESS_ERROR);
        }

        basOrganization.setId(new Long(Constant.QJZD.getCode()));
        int res = organizationService.updateById(basOrganization);
        return res>0?Result.success(null):Result.error(CodeMsg.SERVER_ERROR);

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
        return "pages/admin/bas/update";
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
