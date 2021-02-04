package com.xxcw.allowance.common.exception;

import com.xxcw.allowance.common.base.BaseResponse;
import com.xxcw.allowance.common.utils.SqlLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description: 统一异常处理
 * @author: yuhaiyang
 * @date: 2020-11-07 9:19
 * @version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public BaseResponse handler(Exception e) {
        log.info("统一异常处理=="+e.getMessage());
        long errorTime = System.currentTimeMillis();
        String errorMessage = "错误码【" + errorTime + "】";
        if(e instanceof BadSqlGrammarException){
            errorMessage += "，错误类型【SQL语法错误】";
        }else if(e instanceof SQLException){
            errorMessage += "，错误类型【SQL异常】";
        }else if(e instanceof NullPointerException){
            errorMessage += "，错误类型【空指针异常】";
        }else if(e instanceof ArithmeticException){
            errorMessage += "，错误类型【算术异常】";
        }else if(e instanceof ClassNotFoundException){
            errorMessage += "，错误类型【类不存在异常】";
        }else if(e instanceof NoSuchMethodException){
            errorMessage += "，错误类型【方法不存在异常】";
        }else if(e instanceof ClassCastException){
            errorMessage += "，错误类型【类转换异常】";
        }else if(e instanceof IllegalArgumentException){
            errorMessage += "，错误类型【非法参数异常】";
        }else if(e instanceof IndexOutOfBoundsException){
            errorMessage += "，错误类型【下标越界异常】";
        }else if(e instanceof SecurityException){
            errorMessage += "，错误类型【安全异常】";
        }else if (e instanceof CustomException) {
            CustomException customException = (CustomException) e;
            errorMessage += "，错误类型【" + customException.getExceptionType() + "】" + (StringUtils.isNotBlank(customException.getDesc()) ? "，错误描述【" + customException.getDesc() + "】" : "");
        }
        // TODO 错误类型待完善
        try {
            //返回前端只需要返回错误码错误类型之类
            return new BaseResponse(10001,errorMessage,e.getMessage());
        } finally {
            //记录日志需要多记录执行过的sql语句
            List<String> list = SqlLogUtils.get();
            if (!list.isEmpty()) {
                errorMessage += "\n执行的sql语句有：\n";
                for (String sql : list)
                    errorMessage += sql + "\n";
            }
            log.error(errorMessage, e);
        }
    }
}
