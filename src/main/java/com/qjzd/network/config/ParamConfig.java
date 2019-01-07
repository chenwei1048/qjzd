package com.qjzd.network.config;

import com.qjzd.network.util.CosUtil;
import com.qjzd.network.util.MailUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * @Author:
 * @Description:
 * @Date Create on 9:57 2018/12/19
 * @MOdifyBy:
 * @parameter
 */
@Component
@PropertySource("classpath:param.yml")
public class ParamConfig {

    @Value("${ipAddressUrl}")
    private String ipAddressUrl;
    @Value("${ipConfig}")
    private String ipConfig;
    @Value("${uploadSrc}")
    private String uploadSrc;
    private String SECRETID;
    private String SECRETKEY;
    private String BUCKETNAME;
    private String REGIONID;

    public String getIpAddressUrl() {
        return ipAddressUrl;
    }

    public void setIpAddressUrl(String ipAddressUrl) {
        this.ipAddressUrl = ipAddressUrl;
    }

    public String getIpConfig() {
        return ipConfig;
    }

    public void setIpConfig(String ipConfig) {
        this.ipConfig = ipConfig;
    }

    public String getUploadSrc() {
        return uploadSrc;
    }

    public void setUploadSrc(String uploadSrc) {
        this.uploadSrc = uploadSrc;
    }

    public String getSECRETID() {
        return SECRETID;
    }

    @Value("${SECRETID}")
    public void setSECRETID(String SECRETID) {
        CosUtil.SECRETID = SECRETID;
        this.SECRETID = SECRETID;
    }

    public String getSECRETKEY() {
        return SECRETKEY;
    }

    @Value("${SECRETKEY}")
    public void setSECRETKEY(String SECRETKEY) {
        CosUtil.SECRETKEY = SECRETKEY;
        this.SECRETKEY = SECRETKEY;
    }

    public String getBUCKETNAME() {
        return BUCKETNAME;
    }

    @Value("${BUCKETNAME}")
    public void setBUCKETNAME(String BUCKETNAME) {
        CosUtil.BUCKETNAME = BUCKETNAME;
        this.BUCKETNAME = BUCKETNAME;
    }

    public String getREGIONID() {
        return REGIONID;
    }

    @Value("${REGIONID}")
    public void setREGIONID(String REGIONID) {
        CosUtil.REGIONID = REGIONID;
        this.REGIONID = REGIONID;
    }




    @Value("${mail.host}")
    public void setHost(String host) {
        MailUtil.host = host;
    }


    @Value("${mail.sendName}")
    public void setSendName(String sendName) {
        MailUtil.sendName = sendName;
    }


    @Value("${mail.sendPort}")
    public void setSendPort(String sendPort) {
        MailUtil.sendPort = sendPort;
    }


    @Value("${mail.userName}")
    public void setUserName(String userName) {
        MailUtil.userName = userName;
    }



    @Value("${mail.userPass}")
    public void setUserPass(String userPass) {
        MailUtil.userPass = userPass;
    }


}
