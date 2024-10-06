package com.itheima.reggie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Slf4j
@Configuration

// @Configuration 注解将这个类标记为一个配置类，它类似于 XML 配置文件。Spring 在启动时会自动扫描并加载这个类中的配置

public class WebMvcConfig extends WebMvcConfigurationSupport {
//    设置静态资源映射

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("addResourceHandlers");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }
//    // addResourceHandlers 方法的主要作用是配置静态资源的路径映射。将 URL 中 /backend/** 和 /front/** 开头的请求映射到 classpath:/backend/ 和 classpath:/front/ 目录下的静态资源。
// 比如，当用户请求 /backend/css/style.css 时，Spring MVC 会自动查找 classpath:/backend/ 目录下的 css/style.css 文件并返回给客户端。
// 6. 作用
// 通过这段代码，你为 Spring Boot 应用配置了两个静态资源路径映射：
//
// /backend/**：所有以 /backend/ 开头的请求将被映射到项目中的 resources/backend/ 文件夹下。
// /front/**：所有以 /front/ 开头的请求将被映射到 resources/front/ 文件夹下。
//}

}
