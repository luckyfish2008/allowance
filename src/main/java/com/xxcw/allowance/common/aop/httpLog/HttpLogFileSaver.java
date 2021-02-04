package com.xxcw.allowance.common.aop.httpLog;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yanglimou
 * @date 2019-03-11 15:41:14
 * @Description 日志存文件
 */
@Component
@Slf4j
public class HttpLogFileSaver implements HttpLogSaver {

    @Override
    public void save(HttpLog httpLog) {
        //将日志记录到文件中
        log.info(new Gson().toJson(httpLog));
    }
}
