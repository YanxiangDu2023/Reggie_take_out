package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.commom.R;
//一个自定义的响应对象，通常用于统一返回结果的格式。
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.impl.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




import javax.servlet.http.HttpServletRequest;

//org.springframework.web.bind.annotation.*：用于处理 HTTP 请求的注解。
//javax.servlet.http.HttpServletRequest：用于处理 HTTP 请求的信息。


@Slf4j
@RestController

//@RestController：标识这个类为 RESTful 风格的控制器，它会自动将返回的对象转换为 JSON 格式。
@RequestMapping("/employee")
// @RequestMapping("/employee")：指定控制器的基础请求路径为 /employee。这意味着所有以 /employee 开头的请求都会被这个控制器处理。

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // @Autowired：通过这个注解，Spring 会自动查找 EmployeeService 的实现类并将其实例注入到 employeeService 属性中。这样，控制器可以使用 employeeService 来处理与员工相关的业务逻辑，如登录、注册、查询等。
    // @Autowired：通过这个注解，Spring 会自动查找 EmployeeService 的实现类并将其注入到 employeeService 属性中。这允许控制器使用 employeeService 进行与 Employee 实体相关的业务操作，如新增、删除、查询等


//EmployeeController 是一个 RESTful 风格的控制器，负责处理与 Employee 相关的 HTTP 请求。
//通过使用 @RestController，它可以直接返回 JSON 格式的响应。
//@RequestMapping("/employee") 指定了控制器的基础路径，控制器中的方法（虽然在这段代码中没有显示）将以此路径为基础进行映射。
//@Autowired 注解使得控制器可以使用 EmployeeService 来处理业务逻辑，提供与员工相关的功能。


    // 员工登录
    @PostMapping("/login")
//    1. @PostMapping("/login")
//    含义：这个注解指示该方法会处理 POST 请求，URL 路径为 /login。在这种情况下，/login 是相对于 EmployeeController 的基础路径 /employee，因此完整的请求路径是 /employee/login。
//    用途：通常，POST 请求用于提交数据，如用户凭证（用户名和密码）进行登录操作。
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

//        返回值类型 R<Employee>:
//
//        这个方法返回一个 R<Employee> 对象。R<T> 是一个泛型类，常用于 API 返回统一的响应格式。通常，R 类包含字段来表示响应的状态码、提示信息、以及具体的数据。
//        在这里，R<Employee> 表示返回的响应数据是一个 Employee 对象（员工对象），比如登录成功时返回的员工信息。
//        方法名 login:
//
//        方法名是 login，表示该方法是处理登录请求的逻辑。
//        参数 HttpServletRequest request:
//
//        HttpServletRequest 是 Java Servlet 提供的类，用于访问当前 HTTP 请求的相关信息。
//        你可以通过这个对象来获取请求中的信息，比如请求头（headers）、请求参数、用户的 session 等。在登录场景下，request 可以用来保存登录成功后的 session 信息（如用户 ID）。
//        参数 @RequestBody Employee employee:
//
//        @RequestBody 注解告诉 Spring 从 HTTP 请求的 主体（body） 中获取数据，并将其自动转换为 Employee 对象。




        //1. 将页面提交的password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 确保这行代码存在

        //2. 根据页面提交的用户username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();

//        LambdaQueryWrapper<Employee> 是 MyBatis-Plus 提供的查询构造器，
//        是 MyBatis-Plus 框架中的一个类，用于构建条件查询。它支持使用 Java 8 的 Lambda 表达式来指定查询条件，使得编写 SQL 查询变得更加简洁和安全。用于构建条件查询语句。
//        通过调用该类的方法（例如 eq()、ne()、gt() 等）来构建不同类型的查询条件。
//        Employee 是你要查询的实体类（即数据库中的员工表对应的 Java 类）。
//        ✨queryWrapper 是你创建的一个查询条件构造器实例，接下来会用它来定义查询条件。


        queryWrapper.eq(Employee::getUsername, employee.getUsername());

//        Employee::getUsername 是一个 Lambda 表达式，它表示一个方法引用。它是 Employee 类中的 getUsername 方法的引用。
//        具体地说，Employee::getUsername 意味着告诉 MyBatis-Plus，“请使用 Employee 实体类中的 getUsername 方法，来指定我要查询的字段（username）”。MyBatis-Plus 会自动将这个方法解析为对应的数据库字段，即 username 列。
//        eq 是 MyBatis-Plus 提供的用于表示“等于”的条件查询方法。
//        这行代码的完整含义是：
//        查询 Employee 表中，username 字段的值 等于 employee.getUsername() 的记录。
//        employee.getUsername() 是从请求中接收到的 employee 对象，它表示用户在前端登录表单中提交的用户名。通过 employee.getUsername() 获取这个用户名。


        Employee emp = employeeService.getOne(queryWrapper);

//        employeeService.getOne(queryWrapper) 是调用 EmployeeService 接口中的一个方法，用于根据构建的查询条件从数据库中获取一条符合条件的 Employee 记录。
//        定义：getOne() 是 EmployeeService 接口中定义的方法，通常是 MyBatis-Plus 提供的，用于根据条件查询一条记录。
//
//        参数：该方法接受一个 Wrapper 类型的参数，这里是 LambdaQueryWrapper<Employee> 类型的 queryWrapper。
//
//        queryWrapper 包含了你之前构建的查询条件，例如：username 字段等于用户输入的值。
//        返回值：getOne() 方法返回一个 Employee 对象，表示查询到的员工记录。如果没有找到符合条件的记录，则返回 null。

        //3.如果没有查询到则返回登录失败结果
        if (emp == null) {
            return R.error("Failed");

        }

        // 4. 密码比对
        if (!emp.getPassword().equals(password)) {
            return R.error("Failed");

        }

        //5.查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (emp.getStatus() == 0) {
            return R.error("Account banned");

        }

        //6.登录成功，将员工id存入session并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);


    }


//    员工退出
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        //清理Session 中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return R.success("Successfully");




    }




}

