package com.qjzd.network.controller;

import com.qjzd.network.domain.SysUser;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.SysUserService;
import com.qjzd.network.util.MD5Util;
import com.qjzd.network.vo.PassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author:
 * @Description:
 * @Date Create on 16:00 2018/9/14
 * @MOdifyBy:
 * @parameter
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/to_upPass")
    public String to_upPass(){
        return "pages/user/update-pass";
    }



    @RequestMapping("/upPass")
    @ResponseBody
    public Result<String> uppass(HttpSession session, @Valid PassVo passVo){
        if(passVo==null){
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
        String oldpass = passVo.getOldpass();
        String pass = passVo.getPass();
        String repass = passVo.getRepass();
        oldpass = MD5Util.inputPassToDbPass(oldpass,MD5Util.salt);
        SysUser user = (SysUser)session.getAttribute("user");
        if(!oldpass.equals(user.getPassword())){
           return Result.error(CodeMsg.OLDPASS_ERROR);
        }
        if(!pass.equals(repass)){
            return Result.error(CodeMsg.REPASS_ERROR);
        }
        repass = MD5Util.inputPassToDbPass(repass,MD5Util.salt);
        if(repass.equals(oldpass)){
            return Result.error(CodeMsg.TOWPASS_ERROR);
        }
        user.setPassword(repass);
        int res = sysUserService.update(user);
        if(res>0){
            return Result.success("");
        }else{
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
