package com.lzz.reggie.service.impl;

import com.lzz.reggie.dto.SetmealDTO;
import com.lzz.reggie.entity.Setmeal;
import com.lzz.reggie.entity.SetmealDish;
import com.lzz.reggie.mapper.SetmealMapper;
import com.lzz.reggie.service.SetmealDishService;
import com.lzz.reggie.service.SetmealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 套餐 服务实现类
 * </p>
 *
 * @author lazzy
 * @since 2023-03-01
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealService setmealService;


    @Override
    public void saveWithSetmeal(SetmealDTO setmealDTO) {
        //保存套餐的基本信息
        this.save(setmealDTO);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setDishId(setmealDTO.getId());
            return item;
        }).collect(Collectors.toList());
//        setmealService.saveBatch(setmealDishes);

    }



}
