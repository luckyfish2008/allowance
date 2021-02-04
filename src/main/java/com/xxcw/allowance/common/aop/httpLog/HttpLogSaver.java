package com.xxcw.allowance.common.aop.httpLog;

/**
 * @author yanglimou
 * @date 2019-03-11 15:39:51
 * @Description http日志保存器接口
 */
public interface HttpLogSaver {
    void save(HttpLog httpLog);
}
