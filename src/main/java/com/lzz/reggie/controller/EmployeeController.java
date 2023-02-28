package com.lzz.reggie.controller;


import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzz.reggie.common.R;
import com.lzz.reggie.entity.Employee;
import com.lzz.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Date;

/**
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-02-22
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        //对密码进行加密
        String password = employee.getPassword();
        password = MD5.create().digestHex16(password.getBytes());
        //按名字查询
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee user = employeeService.getOne(queryWrapper);

        if (user == null) {
            //查询不到数据，登录失败
            return R.error("登录失败，请输入账号或密码");
        }
        if (!user.getPassword().equals(password)) {
            //密码不正确
            return R.error("登录失败,密码错误！");
        }
        if (user.getStatus() == 0) {
            //状态停止，登录失败
            return R.error("登录失败，账号停用！");
        }
        request.setAttribute("employee", user.getId());
        return R.success(user);
    }


    /**
     * 用户退出功能
     */

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 添加员工
     */
    @PostMapping
    public R<String> addEmployee(@RequestBody Employee employee,HttpServletRequest request){
        employee.setPassword(MD5.create().digestHex16("123456".getBytes()));
        employee.setCreateTime(new Date());
        employee.setUpdateTime(new Date());

        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        employeeService.save(employee);
        return R.success("添加成功");
    }


    /**
     * 用户分页 TODO
     *
     * @return
     */
    @GetMapping("/page")
    public Page<Employee> pageR(@PathParam("page") Long page,
                                @PathParam("pagesize") Long pagesize,
                                Employee employee){

        Page<Employee> employeePage = new Page<>(page,pagesize);
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Employee> queryWrapper = employeeLambdaQueryWrapper.eq(Employee::getUsername, employee.getUsername());
        return employeeService.page(employeePage,queryWrapper);
    }


}

