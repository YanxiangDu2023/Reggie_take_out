package com.itheima.reggie.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>{

   // BaseMapper 是 MyBatis-Plus 框架中的一个基础接口。它封装了常见的 CRUD（增、删、改、查）操作，使得开发者可以不用手动编写基础的 SQL 语句。
//   EmployeeMapper 接口用于与数据库进行交互，专门处理与 Employee 实体相关的数据库操作。
//    通过继承 BaseMapper<Employee>，它可以自动获得 CRUD（增删改查）操作的默认实现，这样你就不需要手动编写 SQL 语句了，极大简化了开发流程。
//    @Mapper 注解将这个接口标识为一个 MyBatis 的 Mapper，Spring 会自动为这个接口生成实现类并将其注册为 Bean。


}
