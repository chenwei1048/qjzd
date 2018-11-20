package com.qjzd.network.config;

import com.qjzd.network.interceptor.CountInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author:
 * @Description:
 * @Date Create on 17:56 2018/11/20
 * @MOdifyBy:
 * @parameter
 */
//@EnableWebMvc
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    CountInterceptor localInterceptor() {
        return new CountInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localInterceptor());
    }
}
