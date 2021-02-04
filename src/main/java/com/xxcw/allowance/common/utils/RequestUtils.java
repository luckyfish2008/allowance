package com.xxcw.allowance.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.*;

/**
 * @author yanglimou
 * @date 2019/1/10 16:16
 * @Description 从request对象直接获取参数
 * 把所有的字符串'替换’--->防止sql注入
 */
@Slf4j
public class RequestUtils {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        return response;
    }

    public static String getHeader(String key) {
        HttpServletRequest request = getRequest();
        String header = request.getHeader(key);
        return header == null ? "" : header;
    }

    public static String getString(String key, String defaultValue) {
        HttpServletRequest request = getRequest();
        String parameter = request.getParameter(key);
        return parameter == null || "".equals(parameter) ? defaultValue : parameter.replaceAll("'", "’");
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static int getInt(String key, int defaultValue) {
        try {
            String value = getString(key);
            return value == null ? defaultValue : Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        try {
            String value = getString(key);
            return value == null ? defaultValue : Boolean.parseBoolean(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }


    public static Map getMap(String key) {
        try {
            String string = getString(key, "{}");
            return new Gson().fromJson(string, new TypeToken<Map<String, String>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();
        }
    }

    public static List getList(String key) {
        try {
            String string = getString(key, "[]");
            return new Gson().fromJson(string, new TypeToken<List<Map<String, String>>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public static List getListCommon(String key) {
        try {
            String string = getString(key, "[]");
            return new Gson().fromJson(string, List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public static String getIp() {
        HttpServletRequest request = getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement();
            String header = request.getHeader(s);
            log.info(s + "：" + header);
        }
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //	  根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //	  对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        log.info("获取真实IP地址：" + ipAddress);
        return ipAddress;
    }

}
