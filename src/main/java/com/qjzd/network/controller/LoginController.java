package com.qjzd.network.controller;

import com.qjzd.network.domain.SysUser;
import com.qjzd.network.exception.GlobalException;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.SysUserService;
import com.qjzd.network.util.CommonUtils;
import com.qjzd.network.vo.LoginVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author:
 * @Description:
 * @Date Create on 17:34 2018/8/15
 * @MOdifyBy:
 * @parameter
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private SysUserService userService;

    @RequestMapping()
    public String toLogin(){
        return "pages/admin/login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<SysUser> doLogin(HttpSession session, @Valid LoginVo loginVo) {
        logger.info(loginVo.toString());
        if(loginVo == null) {
            return Result.error(CodeMsg.USERNAME_EMPTY);
        }
        SysUser user = userService.login(loginVo);
        session.setAttribute("user",user);
        return Result.success(user);
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        if(session.getAttribute("user")!=null){
            session.removeAttribute("user");
        }
        return "pages/admin/login";
    }
}
