package com.qjzd.network.util;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * @Author: cherry
 * @Description:
 * @Date Create on 14:56 2018/8/6
 * @MOdifyBy:
 * @parameter
 */
public class MailUtil {


    public static boolean send(String to, String subject, String content, String smtp, String host,
                               String sendName, String sendPort, String userName, String userPwd) {

        // 第一步：创建Session
        Properties props = new Properties();
        // 指定邮件的传输协议，smtp(Simple Mail Transfer Protocol 简单的邮件传输协议)
        props.put("mail.transport.protocol", smtp);
        // 指定邮件发送服务器服务器 "smtp.qq.com"
        props.put("mail.host", host);
        // 指定邮件的发送人(您用来发送邮件的服务器，比如您的163\sina等邮箱)
        props.put("mail.from", sendName);
        if (true) {
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.socketFactory.port", sendPort);
        }
        Session session = Session.getDefaultInstance(props);
        // 开启调试模式
        session.setDebug(true);
        try {
            // 第二步：获取邮件发送对象
            Transport transport = session.getTransport();
            // 连接邮件服务器，链接您的163、sina邮箱，用户名（不带@163.com，登录邮箱的邮箱账号，不是邮箱地址）、密码
            transport.connect(userName, userPwd);
            Address toAddress = new InternetAddress(to);
            // 第三步：创建邮件消息体
            MimeMessage message = new MimeMessage(session);
            //设置自定义发件人昵称
            String nick="";
            try {
                nick=javax.mail.internet.MimeUtility.encodeText("项目掉线请紧急处理");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            message.setFrom(new InternetAddress(nick+" <"+sendName+">"));
            //设置发信人
            // message.setFrom(new InternetAddress(sendName));
            // 邮件的主题
            message.setSubject(subject);
            //收件人
            message.addRecipient(Message.RecipientType.TO, toAddress);
            /*//抄送人
            Address ccAddress = new InternetAddress("first.lady@whitehouse.gov");
            message.addRecipient(Message.RecipientType.CC, ccAddress);*/
            // 邮件的内容
            message.setContent(content, "text/html;charset=utf-8");
            // 邮件发送时间
            message.setSentDate(new Date());
            // 第四步：发送邮件
            // 第一个参数：邮件的消息体
            // 第二个参数：邮件的接收人，多个接收人用逗号隔开（test1@163.com,test2@sina.com）
            transport.sendMessage(message, InternetAddress.parse(to));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void heartMail(String email,String hosname) {
        // 您要发送给谁，标题，内容
        MailUtil.send(email, hosname+"已下线，请及时处理哦！", hosname+"已下线，请及时处理哦！", "smtp", "smtp.163.com", "cherry_5917@163.com", "25", "cherry_5917", "cherry917");
    }

    public static void main(String[] arg){
        String html="<div style=\"font-family: &quot;lucida Grande&quot;, Verdana, &quot;Microsoft YaHei&quot;; white-space: normal; color: rgb(32, 178, 170); font-size: 20px;\">√ Connect [中山市苏华赞医院] restore normally</div><div style=\"font-family: &quot;lucida Grande&quot;, Verdana, &quot;Microsoft YaHei&quot;; font-size: 14px; white-space: normal;\"><ul style=\"list-style: none; padding: 15px;\"><li style=\"list-style-type: disc; list-style-position: inside;\">Time:&nbsp;<span style=\"border-bottom: 1px dashed rgb(204, 204, 204); position: relative;\">2018-09-28</span>&nbsp;16:00:01</li><li style=\"list-style-type: disc; list-style-position: inside;\">Monitor URL:&nbsp;<a href=\"http://hosapi.zgjkw.cn/heart/zspt-one?hoscode=45726569044200011A1001\" target=\"_blank\" style=\"outline: none; text-decoration-line: underline; cursor: pointer; color: rgb(78, 93, 128);\">http://hosapi.zgjkw.cn/heart/zspt-one?hoscode=45726569044200011A1001</a></li><li style=\"list-style-type: disc; list-style-position: inside;\">Cost time: 362 ms</li></ul>More monitoring instance see&nbsp;<a href=\"http://localhost:7777/hb/monitoring/2a378795b1e94259939a8b84cb72e3f5.hb\" target=\"_blank\" style=\"outline: none; text-decoration-line: underline; cursor: pointer; color: rgb(78, 93, 128);\">中山市苏华赞医院</a></div><div style=\"font-family: &quot;lucida Grande&quot;, Verdana, &quot;Microsoft YaHei&quot;; font-size: 14px; white-space: normal; color: gray;\"><p style=\"line-height: 23.8px;\">From&nbsp;<a href=\"http://localhost:7777/hb/\" target=\"_blank\" style=\"outline: none; text-decoration-line: underline; cursor: pointer; color: rgb(78, 93, 128);\">HeartBeat</a></p></div><p><br></p>";
        MailUtil.send("1048676598@qq.com", "收到新的留言哦，请及时回复!", html, "smtp", "smtp.163.com", "cherry_5917@163.com", "25", "cherry_5917", "cherry917");

    }
}
