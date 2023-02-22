package com.lzz.reggie.service.impl;

import com.lzz.reggie.entity.User;
import com.lzz.reggie.mapper.UserMapper;
import com.lzz.reggie.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-02-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
