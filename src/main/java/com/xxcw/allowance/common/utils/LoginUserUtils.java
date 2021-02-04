package com.xxcw.allowance.common.utils;

import java.util.Map;

/**
 * @author yanglimou
 * @date 2019-03-15 15:32:10
 * @Description 获取登陆用户信息工具类  登录后使用LoginUserUtils.setLoginUser(user);将登录用户存储
 */

public class LoginUserUtils {
    private static final ThreadLocal<Map> USERS = new ThreadLocal<Map>();

    public static void setLoginUser(Map user) {
        USERS.set(user);
    }

    public static void removeLoginUser(){
        USERS.remove();
    }

    //获取用户信息map
    public static Map getLoginUser() {
        return USERS.get();
    }

    //获取用户信息中的某一个属性
    public static String getLoginParamsAsString(String key) {
        Map user = getLoginUser();
        return MapUtils.getString(user, key);
    }

    public static int getLoginParamsAsInt(String key) {
        Map user = getLoginUser();
        return MapUtils.getInt(user, key);
    }

    public static String getGh() {
        return getLoginParamsAsString("GH");
    }

    public static String getXm() {
        return getLoginParamsAsString("XM");
    }

    public static int getDwbh() {
        return getLoginParamsAsInt("DWBH");
    }


    public static int getYwflbh() {
        return getLoginParamsAsInt("YWFLBH");
    }

    public static String getDwdm() {
        return getLoginParamsAsString("DWDM");
    }

    public static String getDwmc() {
        return getLoginParamsAsString("DWMC");
    }

    public static String getZzdwbh() {
        return getLoginParamsAsString("ZZDWBH");
    }
    public static String getZzdwmc() {
        return getLoginParamsAsString("ZZDWMC");
    }
    public static String getZzdwdm() {
        return getLoginParamsAsString("ZZDWDM");
    }


    public static String getYsdwbh() {
        return getLoginParamsAsString("YSDWBH");
    }
    public static String getXtbh() {
        return getLoginParamsAsString("XTBH");
    }
    //用户是否是子系统的领导
    public static String getNkldbz(){
        return getLoginParamsAsString("NKLDBZ");
    }
    //用户当前点击的qxbh
    public static String getQxbh(){
        return getLoginParamsAsString("QXBH");
    }
}
