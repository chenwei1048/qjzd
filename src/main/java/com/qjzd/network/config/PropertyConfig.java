package com.qjzd.network.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author:
 * @Description:
 * @Date Create on 11:38 2018/12/19
 * @MOdifyBy:
 * @parameter
 */
@Configuration
public class PropertyConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer loadProperties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        //yaml.setResources(new FileSystemResource("classpath:config/user.yml"));//File路径引入
        yaml.setResources(new ClassPathResource("param.yml"));//class路径引入
        configurer.setProperties(yaml.getObject());
        return configurer;
    }
}
