package com.qjzd.network.controller.admin;

import com.qjzd.network.domain.SysUser;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.SysUserService;
import com.qjzd.network.vo.LoginVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/admin/login")
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private SysUserService userService;

    @RequestMapping()
    public String toLogin(HttpSession session){
        if(session.getAttribute("user")!=null){
            return "redirect:/admin/index";
        }
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
