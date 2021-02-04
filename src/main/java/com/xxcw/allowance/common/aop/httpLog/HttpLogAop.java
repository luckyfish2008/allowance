package com.xxcw.allowance.common.aop.httpLog;

import com.xxcw.allowance.common.redis.LoginRedisService;
import com.xxcw.allowance.common.utils.LoginUserUtils;
import com.xxcw.allowance.common.utils.RequestUtils;
import com.xxcw.allowance.common.utils.SqlLogUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author yanglimou
 * @date 2019/1/10 15:05
 * @Description 请求记录日志拦截器
 */
@Aspect
@Component
@Order(1)
public class HttpLogAop {

    @Resource
    @Qualifier("httpLogFileSaver")
    private HttpLogSaver httpLogSaver;


    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void httpLogAop() {
    }

    @Around("httpLogAop()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //增加清空信息的代码
        SqlLogUtils.remove();
        LoginUserUtils.removeLoginUser();


        HttpServletRequest request = RequestUtils.getRequest();
        HttpLog httpLog = new HttpLog();
        httpLog.setIp(request.getRemoteAddr());
        // 这里的工号是cookie中记录的工号，如果用户没有登陆，或者用户登陆后切换用户在登陆成功前gh是错误的
        httpLog.setGh(RequestUtils.getRequest().getHeader(LoginRedisService.COOKIE_GH_KEY));
        Map params = new HashMap();
        /*
        for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
            params.put(param.getKey(), param.getValue()[0]);
        }
        */
        Map map = request.getParameterMap();
        Set all = map.entrySet();
        Iterator iter = all.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String[]> me = (Map.Entry<String, String[]>) iter.next();
            params.put(me.getKey(), me.getValue()[0]);
        }

        httpLog.setParams(params);
        httpLog.setUrl(request.getRequestURL().toString());
        long requestTime = System.currentTimeMillis();
        httpLog.setRequestTime(requestTime);
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            httpLog.setError(true);
            throw throwable;
        } finally {
            httpLog.setSpendTime(System.currentTimeMillis() - requestTime);
            httpLogSaver.save(httpLog);
        }
    }
}
