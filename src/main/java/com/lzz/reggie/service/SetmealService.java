package com.lzz.reggie.service;

import com.lzz.reggie.dto.SetmealDTO;
import com.lzz.reggie.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.reggie.entity.SetmealDish;

import java.util.List;

/**
 * <p>
 * 套餐 服务类
 * </p>
 *
 * @author lazzy
 * @since 2023-03-01
 */
public interface SetmealService extends IService<Setmeal> {
    public void saveWithSetmeal(SetmealDTO setmealDTO);



}
