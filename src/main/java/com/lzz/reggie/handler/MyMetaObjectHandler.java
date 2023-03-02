package com.lzz.reggie.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.lzz.reggie.common.BaseContext;
import com.lzz.reggie.entity.Employee;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {

//        setFieldValByName("create_time",new Date(),metaObject);


        metaObject.setValue("createTime",new Date());
        metaObject.setValue("updateTime",new Date());
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
//        setFieldValByName("update_time",new Date(),metaObject);

    }



    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime",new Date());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }
}
