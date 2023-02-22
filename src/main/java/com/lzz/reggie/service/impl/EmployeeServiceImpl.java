package com.lzz.reggie.service.impl;

import com.lzz.reggie.entity.Employee;
import com.lzz.reggie.mapper.EmployeeMapper;
import com.lzz.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-02-22
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
