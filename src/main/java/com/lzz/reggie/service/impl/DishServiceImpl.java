package com.lzz.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lzz.reggie.common.R;
import com.lzz.reggie.dto.DishDTO;
import com.lzz.reggie.entity.Dish;
import com.lzz.reggie.entity.DishFlavor;
import com.lzz.reggie.mapper.DishMapper;
import com.lzz.reggie.service.DishFlavorService;
import com.lzz.reggie.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author lazzy
 * @since 2023-03-01
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * @param dishDTO
     */
    @Override
    public void saveWithDishFlavor(DishDTO dishDTO) {
        //保存菜品的基本信息到菜品表
        this.save(dishDTO);
        List<DishFlavor> flavors = dishDTO.getDishFlavors();
        List<DishFlavor> list= flavors.stream().map((item) ->
        {
            //获取到菜品的id
            Long id = dishDTO.getId();
            item.setDishId(id);
            return item;
        }).collect(Collectors.toList());
        //保存菜品的口味数据到口味表
        dishFlavorService.saveBatch(list);
    }

    @Override
    public DishDTO getByIdWithFlavor(Long id) {
        //查询菜品基本信息
        Dish dish = this.getById(id);
        DishDTO dishDTO = new DishDTO();
        BeanUtils.copyProperties(dish,dishDTO);
        //查询口味信息
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> list = dishFlavorService.list(dishFlavorLambdaQueryWrapper);
        if (list!=null){

            dishDTO.setDishFlavors(list);
        }
        return dishDTO;
    }


    @Transactional
    @Override
    public void updateByIdWithFlavor(DishDTO dishDTO){

        this.updateById(dishDTO);
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDTO.getId());
        dishFlavorService.remove(queryWrapper);
        List<DishFlavor> dishFlavors = dishDTO.getDishFlavors();
        dishFlavors.stream().map((item)->{
            item.setDishId(dishDTO.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(dishFlavors);
    }


}
