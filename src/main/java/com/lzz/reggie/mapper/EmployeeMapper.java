package com.lzz.reggie.mapper;

import com.lzz.reggie.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2023-02-22
 *
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
