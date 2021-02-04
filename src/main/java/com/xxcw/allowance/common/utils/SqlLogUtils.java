package com.xxcw.allowance.common.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: sql语句记录工具类
 * @author: yuhaiyang
 * @date: 2020-11-07 9:38
 * @version: 1.0
 * @调用方式： 在执行sql语句的方法中使用  SqlLogUtils.add(sql);
 */
@Slf4j
public class SqlLogUtils {
    private static final ThreadLocal<List> SQLLOGS = new ThreadLocal<List>();

    public static void add(String sqlOrMessage) {
        log.debug(sqlOrMessage);
        List list = get();
        list.add(sqlOrMessage);
        SQLLOGS.set(list);
    }

    public static List get() {
        List list = SQLLOGS.get();
        if (list == null){
            list = new ArrayList();
        }
        return list;
    }
    public static void remove(){
        SQLLOGS.remove();
    }
}
