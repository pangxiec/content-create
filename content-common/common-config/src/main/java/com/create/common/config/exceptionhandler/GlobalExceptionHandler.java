package com.create.common.config.exceptionhandler;


import com.create.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xmy
 * @date 2021/2/1 10:56
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 指定出现什么异常执行这个方法
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理..");
    }

    /**
     * 特定异常
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理..");
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(ContentException.class)
    @ResponseBody
    public R error(ContentException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}