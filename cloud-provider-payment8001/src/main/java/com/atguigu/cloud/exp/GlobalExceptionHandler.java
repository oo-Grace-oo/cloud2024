package com.atguigu.cloud.exp;

import com.atguigu.cloud.base.ReturnCodeEnum;
import com.atguigu.cloud.resp.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 *
 * </p>
 *
 * @author ShaoHuaYuGong
 * @date 2024/06/10
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultData<String> exceptionHandler(Exception ex) {
        log.error("全局异常处理", ex);
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), ex.getMessage());
    }

}
