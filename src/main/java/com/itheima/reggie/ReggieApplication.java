package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
// lombok.extern.slf4j.Slf4j: 这是 Lombok 库提供的注解，通过添加 @Slf4j，它会自动为这个类生成一个 日志记录器（logger）。Slf4j 是一个常用的日志框架接口。

import org.springframework.boot.SpringApplication;
// 这是 Spring Boot 中启动应用程序的核心类，它提供了一个 run() 方法，用来启动 Spring Boot 应用。

import org.springframework.boot.autoconfigure.SpringBootApplication;
//这是一个组合注解，包含了 @Configuration、@EnableAutoConfiguration 和 @ComponentScan。它的作用是标记这个类为一个 Spring Boot 应用的主配置类，启用自动配置。

@Slf4j
// @Slf4j: Lombok 注解，用来为该类自动生成一个名为 log 的日志对象（log 是静态成员变量），你可以使用这个 log 对象来记录日志信息

@SpringBootApplication
//Spring Boot 应用的标志性注解，告诉 Spring Boot 这个类是应用的启动入口，并且自动配置 Spring 的组件。

public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class, args);
        log.info("Successfully");

//        SpringApplication.run(ReggieApplication.class, args);:
//        ReggieApplication.class：告诉 Spring Boot 这是应用的主类。
        //使用 @SpringBootApplication 注解来标记这个类为 Spring Boot 应用的启动类，并启用自动配置。
        //使用 SpringApplication.run() 方法来启动 Spring Boot 应用。


       // args 的自动处理： Spring Boot 内部使用 CommandLinePropertySource 来解析和处理 args 中的参数。
//        args：将命令行参数传递给 Spring Boot，允许它动态解析命令行传入的配置选项。
//        如果没有传递参数：
//        如果你没有通过命令行传递任何参数，args 数组就会是空的，即 args.length == 0。
//        Spring Boot 在这种情况下会使用默认的配置，比如端口 8080，默认的配置文件 application.properties 或 application.yml。
    }
}
