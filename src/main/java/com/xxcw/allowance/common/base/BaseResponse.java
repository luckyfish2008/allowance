package com.xxcw.allowance.common.base;

import lombok.Data;

/**
 * @Description: 基本返回值类
 * @author: yuhaiyang
 * @date: 2020-11-05 15:30
 * @version: 1.0
 */
@Data
public class BaseResponse<T> {
    private Integer code;//返回代码
    private String msg;//返回消息
    private T data;//返回的附带数据

    public BaseResponse() {

    }

    public BaseResponse(Integer code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
