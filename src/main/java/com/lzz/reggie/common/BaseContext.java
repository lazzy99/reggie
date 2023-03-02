package com.lzz.reggie.common;

/**
 * 用户保存和获取当前id，基于ThreadLocal工具类
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
       return threadLocal.get();
    }
}
