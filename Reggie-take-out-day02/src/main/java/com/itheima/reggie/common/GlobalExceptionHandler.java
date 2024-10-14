package com.itheima.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */

@ControllerAdvice(annotations = {RestController.class, Controller.class})
// @ControllerAdvice：该注解表示该类是一个全局的异常处理器。它会拦截所有使用 @RestController 或 @Controller 注解的控制器中的异常。

@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
   // 该注解指定了这个方法用于处理 SQLIntegrityConstraintViolationException 类型的异常。当在控制器中抛出这种异常时，Spring 会自动调用这个方法。

    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        // 方法名为 exceptionHandler，它接受一个 SQLIntegrityConstraintViolationException 类型的参数 ex，这个参数是抛出的异常实例。
        log.error(ex.getMessage());

        if(ex.getMessage().contains("Duplicate entry")){

            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);

//            处理重复条目：
//
//            if (ex.getMessage().contains("Duplicate entry"))：检查异常信息是否包含 "Duplicate entry" 字符串，表示数据库中存在重复数据的错误。
//            String[] split = ex.getMessage().split(" ");：将异常信息按空格分割成字符串数组。
//            String msg = split[2] + "已存在";：提取出重复的值（假设它是第三个分隔项），并构建一个错误消息，表示该值已经存在。
//            return R.error(msg);：返回一个包含错误消息的响应。
//            未知错误处理：如果异常信息中不包含 "Duplicate entry"，则返回一个通用的错误信息 return R.error("未知错误");。
        }

        return R.error("未知错误");
    }
}
