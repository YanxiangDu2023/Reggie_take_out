package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * 分类管理
 */
@RestController
@RequestMapping("/category")
//http://localhost:8080/category 路径在此

@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category:{}",category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        // Page 是一个用于存储分页数据的对象，通常由 MyBatis-Plus 或其他框架生成，具有以下特点：
        //分页构造器
        //泛型定义：泛型允许你在定义类或方法时不指定具体的类型，而是使用一个或多个类型参数。这样，类或方法可以操作不同类型的数据。
        //
        //类型参数：在 Page<Category> 中，Category 是 Page 类的类型参数。它告诉编译器这个 Page 对象将存储 Category 类型的数据。换句话说，Category 是 Page 类的一个具体实现

        // Page<>：这里的 Page<> 表示要创建的对象是 Page 类的实例。(page, pageSize)
        //这是构造函数的参数，传递给 Page 类的构造函数：

        Page<Category> pageInfo = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

        // LambdaQueryWrapper<Category>：这是创建一个查询条件构造器的实例 queryWrapper。使用 LambdaQueryWrapper 可以以 Lambda 表达式的形式构建 SQL 查询条件。
        //泛型参数：<Category> 表明该 queryWrapper 将用于构建与 Category 实体相关的查询条件。
        queryWrapper.orderByAsc(Category::getSort);

        // orderByAsc：这是 queryWrapper 的一个方法，表示按照升序对结果进行排序。
        //Category::getSort：这是一个方法引用，指向 Category 类的 getSort() 方法，表示将使用 getSort() 返回的值作为排序的依据。这样，查询结果将根据 sort 字段升序排列。


        //分页查询
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);

        // categoryService.page(...)：这是调用服务层的方法来执行分页查询。categoryService 是一个服务接口（通常是 MyBatis-Plus 提供的），其 page 方法接受两个参数：
        //pageInfo：封装了分页信息的对象。
        //queryWrapper：用于构建查询条件的对象。
        //该方法会根据 queryWrapper 中定义的条件，从数据库中查询数据，并将结果存储到 pageInfo 中。
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(long id){
        log.info("删除分类，id为：{}",id);
        // categoryService.removeById(id);
        return R.success("分类信息删除成功");

    }


    /**
     * 根据id修改分类信息
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("修改分类信息：{}",category);

        categoryService.updateById(category);

//        categoryService.updateById(category) 调用服务层的 updateById 方法，执行分类信息的更新操作。
//        updateById 方法根据 category 对象中的 id 查找到对应的记录，并更新其他字段的数据。

        return R.success("修改分类信息成功");
    }
}
