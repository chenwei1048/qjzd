package com.qjzd.network.listener;

import com.alibaba.fastjson.JSONObject;
import com.qjzd.network.domain.BasInformation;
import com.qjzd.network.domain.BasOrganization;
import com.qjzd.network.service.BasInformationService;
import com.qjzd.network.service.BasOrganizationService;
import com.qjzd.network.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author:
 * @Description:
 * @Date Create on 16:46 2018/11/28
 * @MOdifyBy:
 * @parameter
 */
@Component
public class PublicDataListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BasOrganizationService organizationService;

    @Autowired
    private BasInformationService informationService;

    @Override
    public void contextInitialized(ServletContextEvent servlet) {
        logger.info("启动监听器，创建全局变量");
        ServletContext servletContext = servlet.getServletContext();
        BasOrganization basOrganization = organizationService.selectById(new Long(Constant.QJCD.getCode()));
        servletContext.setAttribute("basOrganization",basOrganization);
        JSONObject param = new JSONObject();
        param.put("type", Constant.GYWM.getCode());
        BasInformation information = informationService.selectByExample(param);
        servletContext.setAttribute("gywm",information);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
