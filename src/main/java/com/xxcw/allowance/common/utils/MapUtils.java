package com.xxcw.allowance.common.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 通用的map工具类
 * @author: yuhaiyang
 * @date: 2020-11-07 9:43
 * @version: 1.0
 */
@Slf4j
public class MapUtils {

    public static String getString(Map map, String key, String defaultValue) {
        String string = org.apache.commons.collections4.MapUtils.getString(map, key, defaultValue);
        return string;
    }

    public static String getString(Map map, String key) {
        return getString(map, key, "");
    }

    public static int getInt(Map map, String key, int defaultValue) {
        return org.apache.commons.collections4.MapUtils.getIntValue(map, key, defaultValue);
    }

    public static int getInt(Map map, String key) {
        return getInt(map, key, 0);
    }

    public static long getLong(Map map, String key, long defaultValue) {
        return org.apache.commons.collections4.MapUtils.getLong(map, key, defaultValue);
    }

    public static long getLong(Map map, String key) {
        return getLong(map, key, 0l);
    }

    public static double getDouble(Map map, String key, double defaultValue) {
        return org.apache.commons.collections4.MapUtils.getDoubleValue(map, key, defaultValue);
    }

    public static double getDouble(Map map, String key) {
        return getDouble(map, key, 0.0);
    }

    public static Date getDate(Map map, String key) {
        if (map == null || StringUtils.isBlank(key) || !map.containsKey(key)) {
            return null;
        }
        try {
            return (Date) map.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Map getMap(Map map, String key) {
        return org.apache.commons.collections4.MapUtils.getMap(map, key);
    }

    public static List getList(Map map, String key) {
        String string = getString(map, key);
        try {
            return new Gson().fromJson(string, List.class);
        } catch (Exception e) {
            log.debug("string转list错误", e);
            return new ArrayList();
        }
    }

}
