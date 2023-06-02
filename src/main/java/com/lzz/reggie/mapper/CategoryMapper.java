package com.lzz.reggie.mapper;

import com.lzz.reggie.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品及套餐分类 Mapper 接口
 * </p>
 *
 * @author lazzy
 * @since 2023-03-01
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
