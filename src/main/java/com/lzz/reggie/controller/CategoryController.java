package com.lzz.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzz.reggie.common.R;
import com.lzz.reggie.entity.Category;
import com.lzz.reggie.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜品及套餐分类 前端控制器
 * </p>
 *
 * @author lazzy
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增菜品分类
     */
    @PostMapping
    public R<Category> addCaategory(@RequestBody Category category){
        categoryService.save(category);
        return R.success(category);
    }

    /**
     * 菜品分页
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        Page<Category> pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.orderByDesc(Category::getSort);
        categoryService.page(pageInfo,categoryLambdaQueryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 删除分类
     */

    @DeleteMapping
    public R<String> delCategoryById(Long id){
        categoryService.removeId(id);
        return R.success("删除分类成功");
    }

    /**
     * 修改菜品分类
     *
     */

    @PutMapping
    public R<String> updateCategory(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("修改菜品分类成功");
    }

    @GetMapping("/list")
    public R<List<Category>> categoryList(Category category){
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(category.getType() != null,Category::getType,category.getType());
        categoryLambdaQueryWrapper.orderByDesc(Category::getSort);
        List<Category> list = categoryService.list(categoryLambdaQueryWrapper);
        return R.success(list);
    }

}

