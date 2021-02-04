package com.xxcw.allowance.common.aop.httpLog;

import java.util.Map;

/**
 * @author yanglimou
 * @date 2019-03-11 15:30:45
 * @Description httpLog记录类
 */
public class HttpLog {
    private String gh;//工号
    private String ip;//ip地址
    private String url;//请求路径
    private Map params;//请求参数
    private long requestTime;//请求时间
    private long spendTime;//请求响应花费时间
    private Boolean isError;//是否有错误

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(long spendTime) {
        this.spendTime = spendTime;
    }

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }
}
