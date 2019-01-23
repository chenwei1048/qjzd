package com.qjzd.network.interceptor;

import com.alibaba.fastjson.JSON;
import com.qjzd.network.activemq.Producer;
import com.qjzd.network.activemq.QueueConstant;
import com.qjzd.network.annotation.MyOperation;
import com.qjzd.network.domain.Lookcount;
import com.qjzd.network.domain.Product;
import com.qjzd.network.service.LookCountService;
import com.qjzd.network.util.IpAdrressUtil;
import com.qjzd.network.util.ThreadPoolUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private Producer producer;

    @Autowired
    private LookCountService lookCountService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if(handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod)handler;
            Lookcount lookcount = new Lookcount();
            lookcount.setIp(IpAdrressUtil.getIpAdrress(httpServletRequest));
            lookcount.setUrl(httpServletRequest.getServerName()+"/"+h.getMethodAnnotation(MyOperation.class).value());
            lookcount.setCreatetime(new Date());
            logger.info("ip "+lookcount.getIp()+"\t"+"的用户正在浏览:"+lookcount.getUrl()+"\t");
//            producer.sendData(new ActiveMQQueue(QueueConstant.IP_ADDRESS_QUEUE), JSON.toJSONString(lookcount));
            ThreadPoolUtil.getPool().execute(new SaveSystemLogThread(lookcount, lookCountService));

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    private static class SaveSystemLogThread implements Runnable {

        private Lookcount lookcount;
        private LookCountService lookCountService;

        public SaveSystemLogThread(Lookcount lookcount, LookCountService lookCountService) {
            this.lookcount = lookcount;
            this.lookCountService = lookCountService;
        }

        @Override
        public void run() {
            lookCountService.insert(lookcount);
        }
    }
}
