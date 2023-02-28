package com.lzz.reggie.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({WebMvcConfig.class,SwaggerConfig.class})
public class ApplicationContextConfig {

    //配置mybatis-plus分页插件 TODO
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }


}
