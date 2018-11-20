package com.qjzd.network.interceptor;

import com.qjzd.network.annotation.MyOperation;
import com.qjzd.network.domain.Lookcount;
import com.qjzd.network.service.LookCountService;
import com.qjzd.network.util.IpAdrressUtil;
import com.qjzd.network.util.NetworkUtil;
import com.qjzd.network.vo.ClientIpVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author:
 * @Description:
 * @Date Create on 14:53 2018/11/20
 * @MOdifyBy:
 * @parameter
 */
public class CountInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LookCountService lookCountService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if(handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod)handler;
//            HttpSession session = httpServletRequest.getSession();
//            if( session.getAttribute("clientIp")==null){
//                return true;
//            }
//            ClientIpVo clientIpVo = (ClientIpVo)session.getAttribute("clientIp");
            Lookcount lookcount = new Lookcount();
            lookcount.setIp(IpAdrressUtil.getIpAdrress(httpServletRequest));
            lookcount.setUrl(h.getMethodAnnotation(MyOperation.class).value());
            lookcount.setCreatetime(new Date());
            lookCountService.insert(lookcount);
            logger.info("ip "+lookcount.getIp()+"\t"+"的用户正在浏览:"+lookcount.getUrl());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
