package com.qjzd.network.config;

import com.qjzd.network.MainApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @Author:
 * @Description:
 * @Date Create on 14:22 2018/12/13
 * @MOdifyBy:
 * @parameter
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainApplication.class);
    }
}

