package com.qjzd.network.interceptor;

import com.qjzd.network.domain.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:
 * @Description:
 * @Date Create on 9:16 2018/11/20
 * @MOdifyBy:
 * @parameter
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 在请求被处理之前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查每个到来的请求对应的session域中是否有登录标识
        Object loginName = request.getSession().getAttribute("user");
        if (null == loginName || !(loginName instanceof SysUser)) {
            // 未登录，重定向到登录页
            if(!"/admin/index".equals(request.getRequestURI())){
                response.getWriter().write("<script language=javascript>top.location.href='/admin/login';</script>");
            }else{
                response.sendRedirect("/admin/login");
            }
            return false;
        }
        SysUser user = (SysUser) loginName;
        System.out.println("当前用户已登录，登录的用户名为： " + user.getUsername()+request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }


    /**
     * 在整个请求结束后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
