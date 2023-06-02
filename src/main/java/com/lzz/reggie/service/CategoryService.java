package com.lzz.reggie.service;

import com.lzz.reggie.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜品及套餐分类 服务类
 * </p>
 *
 * @author lazzy
 * @since 2023-03-01
 */
public interface CategoryService extends IService<Category> {
    public void removeId(Long id);

}
