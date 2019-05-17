package com.qjzd.network.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qjzd.network.domain.Leaveword;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.LeaveWordService;
import com.qjzd.network.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
/**
 * @Author:
 * @Description:
 * @Date Create on 14:09 2018/11/19
 * @MOdifyBy:
 * @parameter
 */
@Controller
@RequestMapping("/admin/leaveWord")
public class LeaveWordController {

    @Autowired
    private LeaveWordService leaveWordService;

    @ResponseBody
    @RequestMapping("/list")
    public Result selectList(@RequestParam(value = "page",defaultValue = "1",required = false)int page,
                             @RequestParam(value = "pagesize",defaultValue = "10",required = false)int pagesize,
                             String orgName, String contacts,String contactNumber,String mobilePhone,
                             String email,String address,String isRead){
        JSONObject param = new JSONObject();
        if(!CommonUtils.isNull(orgName)){
            param.put("orgName",orgName);
        }
        if(!CommonUtils.isNull(contacts)){
            param.put("contacts",contacts);
        }
        if(!CommonUtils.isNull(contactNumber)){
            param.put("contactNumber",contactNumber);
        }
        if(!CommonUtils.isNull(mobilePhone)){
            param.put("mobilePhone",mobilePhone);
        }
        if(!CommonUtils.isNull(email)){
            param.put("email",email);
        }
        if(!CommonUtils.isNull(address)){
            param.put("address",address);
        }
        if(!CommonUtils.isNull(isRead)){
            param.put("isRead",isRead);
        }
        List<Leaveword> list = leaveWordService.selectList(param,page,pagesize);
        if(CommonUtils.isNull(list)){
            return Result.error(CodeMsg.NODATA);
        }else{
            PageInfo pageInfo = new PageInfo(list);
            return Result.success(pageInfo);
        }
    }

    @ResponseBody
    @RequestMapping("/status")
    public Result status(@RequestParam("ids[]")Long[] ids,String isRead){
        if(ids==null ||ids.length<1){
            return Result.error(CodeMsg.BIND_ERROR);
        }
        Leaveword leaveword;
        for(Long id:ids){
            leaveword= new Leaveword();
            leaveword.setId(id);
            leaveword.setIsRead(isRead);
            leaveWordService.updateByPrimaryKeySelective(leaveword);
        }
        return Result.success(null);

    }

    @ResponseBody
    @RequestMapping("/del")
    public Result del(@RequestParam("ids[]")Long[] ids){
        if(CommonUtils.isNull(ids)){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        for(Long id:ids){
            leaveWordService.delete(id);
        }
        return Result.success(null);
    }

    @RequestMapping("/view")
    public String view(){
        return "pages/admin/leaveWord/list";
    }

    @RequestMapping("/content")
    public String content(){
        return "pages/admin/leaveWord/content";
    }
}
