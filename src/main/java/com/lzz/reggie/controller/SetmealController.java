package com.lzz.reggie.controller;


import com.lzz.reggie.common.R;
import com.lzz.reggie.dto.SetmealDTO;
import com.lzz.reggie.entity.Setmeal;
import com.lzz.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 套餐 前端控制器
 * </p>
 *
 * @author lazzy
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @PostMapping
    public R<String> save(@RequestBody SetmealDTO setmealDTO)
    {
        setmealService.saveWithSetmeal(setmealDTO);
        return R.success("新增成功");
    }

}

