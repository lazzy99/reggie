package com.lzz.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzz.reggie.common.R;
import com.lzz.reggie.dto.DishDTO;
import com.lzz.reggie.entity.Category;
import com.lzz.reggie.entity.Dish;
import com.lzz.reggie.service.CategoryService;
import com.lzz.reggie.service.DishService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜品管理 前端控制器
 * </p>
 *
 * @author lazzy
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 菜品分页查询
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {

        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDTO> dtoPage = new Page<>();

        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        dishLambdaQueryWrapper.orderByDesc(Dish::getSort);
        dishService.page(pageInfo, dishLambdaQueryWrapper);

        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Dish> records = pageInfo.getRecords();

        List<DishDTO> list = records.stream().map((item) -> {
            DishDTO dishDTO = new DishDTO();
            BeanUtils.copyProperties(item, dishDTO);
            //拿取菜品分类id
            Long categoryId = item.getCategoryId();
            //根据菜品分类id获取分类对象
            Category category = categoryService.getById(categoryId);

            if (category != null) {
                //拿取菜品名称
                String categoryName = category.getName();
                //给dishDTO设置菜品名称
                dishDTO.setCategoryName(categoryName);
            }
            return dishDTO;
        }).collect(Collectors.toList());
        //从Dish中拿取实体类属性赋给DishDTO
        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }

    /**
     * 新增菜品
     */

    @PostMapping
    public R<String> addDish(@RequestBody DishDTO dishDTO) {
        dishService.saveWithDishFlavor(dishDTO);
        return R.success("新增菜品成功");
    }

    /**
     * 查询菜品及口味信息
     */

    @GetMapping("/{id}")
    public R<DishDTO> get(@PathVariable("id") Long id){
        DishDTO byIdWithFlavor = dishService.getByIdWithFlavor(id);
        return R.success(byIdWithFlavor);
    }

    /**
     * 修改菜品
     */
    @PutMapping
    public R<String> update(@RequestBody DishDTO dishDTO){
        dishService.updateByIdWithFlavor(dishDTO);
        return R.success("修改成功");
    }

    /**
     * 查询菜品
     */

    @GetMapping("/list")
    public R<List<Dish>> getDish(Dish dish){
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId()!=null,Dish::getCategoryId,dish.getCategoryId());
        queryWrapper.eq(Dish::getStatus,1);
        queryWrapper.orderByDesc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> list = dishService.list(queryWrapper);
        return R.success(list);
    }
}

