package com.lzz.reggie.handler;

import com.lzz.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exception(SQLIntegrityConstraintViolationException exception){
        log.info(exception.getMessage());
        if (exception.getMessage().contains("Duplicate entry")){
            String[] strs  = exception.getMessage().split(" ");
            String msg = strs[2]+"已存在";
            return R.error(msg);
        }
        return R.error("未知错误！");
    }

    /**
     * 处理自定义业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler({CustomExceptionHandler.class})
    public R<String> exception(CustomExceptionHandler ex) {

        return R.error(ex.getMessage());
    }
}
