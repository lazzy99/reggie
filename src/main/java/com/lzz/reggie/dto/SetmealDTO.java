package com.lzz.reggie.dto;

import com.lzz.reggie.entity.Setmeal;
import com.lzz.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SetmealDTO extends Setmeal{
    private List<SetmealDish> setmealDishes = new ArrayList<>();
    private String categoryName;
}
