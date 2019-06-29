package com.brioal.music.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.util.List;

@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {
    /**
     * 配置自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 文件拦截
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + Config.PROJECT_DIR + "/");
    }
}
