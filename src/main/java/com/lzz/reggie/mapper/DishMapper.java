package com.lzz.reggie.mapper;

import com.lzz.reggie.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品管理 Mapper 接口
 * </p>
 *
 * @author lazzy
 * @since 2023-03-01
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}
