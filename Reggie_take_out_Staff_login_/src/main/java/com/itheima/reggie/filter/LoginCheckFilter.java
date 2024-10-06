package com.itheima.reggie.filter;


// 检查用户是否已经完成登录

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.commom.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
//拦截所有请求路径
// 这段代码实现了一个名为 LoginCheckFilter 的过滤器，它使用了 Java Servlet API 的 Filter 接口来拦截所有 HTTP 请求，并执行某些逻辑处理。

//@WebFilter：这是一个用于声明 Servlet 过滤器的注解，filterName 是过滤器的名称，urlPatterns 设置过滤器拦截的请求路径。
//filterName = "LoginCheckFilter"：定义过滤器的名称为 LoginCheckFilter。
//urlPatterns = "/*"：拦截所有请求（包括静态资源和 API 调用），即所有以 / 开头的请求路径。


@Slf4j
public class LoginCheckFilter implements Filter {

    // 路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    // LoginCheckFilter 实现了 Filter 接口，这是 Servlet 中的标准接口，用于对请求进行拦截处理，通常用于验证登录、记录日志等操作。
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

//        doFilter 是 Filter 接口中的核心方法，每当请求到达服务器时，这个方法都会被调用。
//        参数说明：
//        ServletRequest servletRequest：表示客户端的 HTTP 请求，稍后会转换成 HttpServletRequest，以便处理 HTTP 相关的内容。
//        ServletResponse servletResponse：表示服务器返回给客户端的响应对象，稍后会转换成 HttpServletResponse。
//        FilterChain filterChain：用于将请求传递给过滤器链中的下一个过滤器或最终的目标资源（如 Servlet、JSP 等）。

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

//        将传入的 servletRequest 和 servletResponse 转换为 HTTP 协议下的请求与响应对象。因为 ServletRequest 和 ServletResponse 是通用接口，
//        而我们需要处理的是具体的 HTTP 请求与响应，因此需要进行强制类型转换。

        //1.获取本次请求的URL
        String requestURI = request.getRequestURI(); //backend/index.html

        log.info("拦截到请求：{}", requestURI);

// 定义不需要请求的路径
        String[] urls = new String[]{
                "/employee/login",
                // 用户登录，放行

                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        //2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);
        //判断本次传过来的路径是否在上面那一堆里面

        // 3. 如果不需要处理，则直接放行
        if (check) {
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        // 4.判断登录状态，如果登录，则直接放行
        if(request.getSession().getAttribute("employee") !=null){
            log.info("用户已登录,用户id为{}",request.getSession().getAttribute("employee"));
            filterChain.doFilter(request, response);
            return;

        }

        log.info("用户未登录");

        // 5.如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;


//        if (request.getSession().getAttribute("employee") == null) {
//            log.info("用户未登录");
//            // 进行重定向到登录页面
//            response.sendRedirect(request.getContextPath() + "/employee/login.html");
//            return;
//        }
//        response.sendRedirect(request.getContextPath() + "/employee/login.html");


//        log.info("拦截到请求：{}",request.getRequestURI());


        //filterChain.doFilter 用于将请求和响应传递给过滤器链中的下一个过滤器或最终处理请求的目标资源。它确保请求在经过这个过滤器后，能够继续被其他过滤器或处理逻辑处理。


    }
    //路径匹配，检查本次请求是否需要放行
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;


    }
}
