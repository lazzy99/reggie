package com.lzz.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lzz.reggie.entity.Category;
import com.lzz.reggie.entity.Dish;
import com.lzz.reggie.entity.Setmeal;
import com.lzz.reggie.handler.CustomExceptionHandler;
import com.lzz.reggie.mapper.CategoryMapper;
import com.lzz.reggie.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzz.reggie.service.DishService;
import com.lzz.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author lazzy
 * @since 2023-03-01
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类
     */

    public void removeId(Long id){
        //查询菜品
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询菜品的条件--->根据菜品id查询
        LambdaQueryWrapper<Dish> dish = dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);

        //查看当前分类是否关联菜品，如果已经关联则抛出异常
        int dishCount = dishService.count(dish);
        if (dishCount>0){
            //已经关联菜品，需抛出异常
                throw new CustomExceptionHandler("当前分类已经关联菜品，无法删除！");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询当前分类是否关联套餐，如果已经关联则抛出异常
        LambdaQueryWrapper<Setmeal> setmeal = setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int setmealCount = setmealService.count(setmeal);
        if (setmealCount>0){
            //已经关联套餐，需要抛出异常
            throw new CustomExceptionHandler("当前分类已经关联套餐，无法删除！");

        }


        //正常删除分类
        super.removeById(id);
    }

}
