package com.xxcw.allowance.common.base;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author yuhaiyang
 * @date 2020-11-27 8:18
 * Description：直接封装统一返回结果集
 */
@Data
@Component
public class BaseApiService<T> {
    // 通用封装
    public BaseResponse<T> setResult(Integer code, String msg, T data) {
        return new BaseResponse(code, msg, data);
    }

    // 返回错误
    public BaseResponse<T> setResultError() {
        return setResult(Constants.HTTP_RES_CODE_400, Constants.HTTP_RES_CODE_400_VALUE, null);
    }

    // 返回错误，可以传msg
    public BaseResponse<T> setResultError(String msg) {
        return setResult(Constants.HTTP_RES_CODE_400, msg, null);
    }

    //返回错误，可以传code，msg
    public BaseResponse<T> setResultError(Integer code, String msg) {
        return setResult(code, msg, null);
    }

    // 返回成功，沒有data值
    public BaseResponse<T> setResultSuccess() {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
    }

    // 返回成功，可以传msg
    public BaseResponse<T> setResultSuccess(String msg) {
        return setResult(Constants.HTTP_RES_CODE_200, msg, null);
    }

    // 返回成功，可以传data值
    public BaseResponse<T> setResultSuccess(T data) {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
    }
}
