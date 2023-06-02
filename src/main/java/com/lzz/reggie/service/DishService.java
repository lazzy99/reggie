package com.lzz.reggie.service;

import com.lzz.reggie.dto.DishDTO;
import com.lzz.reggie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜品管理 服务类
 * </p>
 *
 * @author lazzy
 * @since 2023-03-01
 */
public interface DishService extends IService<Dish> {
    public void saveWithDishFlavor(DishDTO dishDTO);

    public DishDTO getByIdWithFlavor(Long id);
    public void updateByIdWithFlavor(DishDTO dishDTO);
}
