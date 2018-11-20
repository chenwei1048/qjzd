package com.qjzd.network.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
     * 常用获取客户端信息的工具 
     *  
     */  
    public final class NetworkUtil {
        /** 
         * Logger for this class 
         */  
        private static Logger logger = Logger.getLogger(NetworkUtil.class);
      
        /** 
         * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址; 
         *  
         * @param request 
         * @return 
         * @throws IOException 
         */  
        public final static String getIpAddress(HttpServletRequest request) {
            // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址  
      
            String ip = request.getHeader("X-Forwarded-For");  
            if (logger.isInfoEnabled()) {  
                logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);  
            }  
      
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                    ip = request.getHeader("Proxy-Client-IP");  
                    if (logger.isInfoEnabled()) {  
                        logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);  
                    }  
                }  
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                    ip = request.getHeader("WL-Proxy-Client-IP");  
                    if (logger.isInfoEnabled()) {  
                        logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);  
                    }  
                }  
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                    ip = request.getHeader("HTTP_CLIENT_IP");  
                    if (logger.isInfoEnabled()) {  
                        logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);  
                    }  
                }  
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
                    if (logger.isInfoEnabled()) {  
                        logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);  
                    }  
                }  
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                    ip = request.getRemoteAddr();  
                    if (logger.isInfoEnabled()) {  
                        logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);  
                    }  
                }  
            } else if (ip.length() > 15) {  
                String[] ips = ip.split(",");  
                for (int index = 0; index < ips.length; index++) {  
                    String strIp = ips[index];
                    if (!("unknown".equalsIgnoreCase(strIp))) {  
                        ip = strIp;  
                        break;  
                    }  
                }  
            }  
            return ip;  
        }



    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        //ipAddress = request.getRemoteAddr();
        ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }

        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}