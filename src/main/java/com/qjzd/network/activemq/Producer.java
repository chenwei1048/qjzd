package com.qjzd.network.activemq;

import com.qjzd.network.domain.Lookcount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @Author:
 * @Description:
 * @Date Create on 14:58 2018/12/18
 * @MOdifyBy:
 * @parameter
 */
@Service
public class Producer {

    // JmsMessagingTemplate 是对JmsTemplate的封装
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    /**
     *
     * @param destination
     *            发送到的队列
     * @param text
     *            待发送的消息
     */
    public void sendData(Destination destination, String text) {
        jmsTemplate.convertAndSend(destination, text);
    }
}
