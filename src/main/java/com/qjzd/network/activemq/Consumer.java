package com.qjzd.network.activemq;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qjzd.network.config.ParamConfig;
import com.qjzd.network.domain.BasOrganization;
import com.qjzd.network.domain.Lookcount;
import com.qjzd.network.service.LookCountService;
import com.qjzd.network.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.servlet.ServletContext;

/**
 * @Author:
 * @Description:
 * @Date Create on 14:59 2018/12/18
 * @MOdifyBy:
 * @parameter
 */
@Component
public class Consumer {

    @Autowired
    private ParamConfig paramConfig;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LookCountService lookCountService;

    @Autowired
    private ServletContext servletContext;

    /**
     * 处理ip 获取对应地址
     * @param text
     * @throws JMSException
     */
    @JmsListener(destination = QueueConstant.IP_ADDRESS_QUEUE)
    public void receiveQueue(String text) throws JMSException {
        try {
            Lookcount lookcount = JSON.parseObject(text,Lookcount.class);
            if (lookcount == null || (lookcount != null&&lookcount.getIp()==null)) {
                return;
            }
            if("127.0.0.1".equals(lookcount.getIp())){
                lookcount.setCity("内网");
            }else{
                String back = HttpUtil.get(paramConfig.getIpAddressUrl() + "?ip=" + lookcount.getIp());
                JSONObject json = JSONObject.parseObject(back);
                if (json.getIntValue("code") == 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    JSONObject dataJson = json.getJSONObject("data");
                    if (dataJson.containsKey("country")) {
                        stringBuffer.append(dataJson.getString("country"));
                    }
                    if (dataJson.containsKey("region")) {
                        stringBuffer.append(dataJson.getString("region"));
                    }
                    if (dataJson.containsKey("city")) {
                        stringBuffer.append(dataJson.getString("city"));
                    }
                    lookcount.setCity(stringBuffer.toString());
                }
            }
            lookCountService.insert(lookcount);
            logger.info(lookcount.getIp() + ":" + lookcount.getCity());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取ip城市失败:"+text);
        }
    }

    /**
     * 留言发送邮件通知
     * @param text
     * @throws JMSException
     */
    @JmsListener(destination = QueueConstant.LEAVE_MAIL)
    public void sendMail(String text) throws JMSException {
        logger.info("sendMail:"+text);
        BasOrganization basOrganization = (BasOrganization)servletContext.getAttribute("basOrganization");
        if(basOrganization.getEmail()==null){
            return;
        }
        MailUtil.sendMail(basOrganization.getEmail(),"您有新的客户留言，请进后台查看！","您有新的客户留言，请进后台查看！<br><a href=\"www.shqjcd.com\">www.shqjcd.com</a>");
    }
}
