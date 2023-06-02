package com.lzz.reggie.dto;

import com.lzz.reggie.entity.Dish;
import com.lzz.reggie.entity.DishFlavor;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO extends Dish {
    private List<DishFlavor> dishFlavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;

}
