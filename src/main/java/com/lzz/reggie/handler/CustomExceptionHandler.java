package com.lzz.reggie.handler;

public class CustomExceptionHandler extends RuntimeException{
    public CustomExceptionHandler(String msg){
        super(msg);
    }
}
