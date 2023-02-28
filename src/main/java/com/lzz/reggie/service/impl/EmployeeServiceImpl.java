package com.lzz.reggie.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.crypto.digest.MD5;
import com.lzz.reggie.common.R;
import com.lzz.reggie.entity.Employee;

import com.lzz.reggie.mapper.EmployeeMapper;
import com.lzz.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Date;

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
