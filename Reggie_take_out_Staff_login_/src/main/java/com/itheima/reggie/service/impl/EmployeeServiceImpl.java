
package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService{

//    继承 ServiceImpl：
//    EmployeeServiceImpl 继承了 ServiceImpl<EmployeeMapper, Employee>。这意味着它获得了 ServiceImpl 中定义的所有方法和功能，这些方法通常涉及到基本的 CRUD（增、删、改、查）操作。
//    ServiceImpl 是一个 MyBatis-Plus 提供的基础服务实现类，它的设计目的是为了简化对数据库的操作。

//    实现 EmployeeService 接口：
//    EmployeeServiceImpl 实现了 EmployeeService 接口。这意味着它需要提供 EmployeeService 中定义的所有方法的具体实现。
//    EmployeeService 接口通常包含一些与 Employee 实体相关的业务逻辑的方法，比如员工注册、更新信息等。





}