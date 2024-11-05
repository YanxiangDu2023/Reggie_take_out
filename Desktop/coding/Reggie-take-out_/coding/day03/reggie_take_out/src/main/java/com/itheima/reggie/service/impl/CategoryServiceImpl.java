package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService{

    // ServiceImpl 是 MyBatis-Plus 提供的一个通用的服务实现类。
    //
    //这个类在定义时是泛型的，表示它可以操作各种不同的 Mapper 对象和实体类，而不局限于某一种特定类型。
    //
    //泛型定义的形式如下

    // public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T>

    // 同样地，ServiceImpl<CategoryMapper, Category> 表示我们创建了一个通用的“服务”类，可以操作数据库（通过 CategoryMapper）并处理 Category 类型的实体对象。



    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

   // 依赖注入：Spring 的依赖注入（Dependency Injection）机制会自动创建 dishService 和 setmealService 实例，并注入到 CategoryServiceImpl 中。这意味着你可以直接使用它们，而不必手动创建实例。

    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishLambdaQueryWrapper);

//        首先创建了一个 LambdaQueryWrapper<Dish> 对象，用来构建查询条件。
//
//        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);：设置查询条件，即查询 Dish 表中 categoryId 为 id 的记录。
//
//        int count1 = dishService.count(dishLambdaQueryWrapper);：调用 dishService.count() 方法获取符合条件的记录数。
//        这是调用 dishService 对象中的 count() 方法。dishService 是一个服务层对象，负责与 Dish 表进行交互。
//        count() 方法会根据传入的查询条件，统计符合条件的记录数。
//
//        如果 count1 > 0，说明该分类下存在关联的菜品，因此继续执行判断逻辑。

        //查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
        if(count1 > 0){
            //已经关联菜品，抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count();
        if(count2 > 0){
            //已经关联套餐，抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        //正常删除分类
        super.removeById(id);
    }
}
