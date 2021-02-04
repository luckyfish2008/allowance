package com.xxcw.allowance.common.aop.login;


import com.xxcw.allowance.common.base.BaseResponse;
import com.xxcw.allowance.common.redis.LoginRedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author yanglimou
 * @date 2019/1/12 17:38
 * @Description 登陆判断aop
 * 拦截所有controller和有@RequestMapping注解的方法
 * 不拦截有@NoLogin注解的方法
 */
@Aspect
@Order(2)
@Component
@Slf4j
public class LoginAop {

    @Autowired
    private LoginRedisService loginRedisService;

    @Value("${redis.session.timeout}")
    private long redisSessionTimeout;

    /**
     * @Description: 切入点 service包及其子包下的类的所有方法并且没有@NoLogin注解的方法
     * @author: yuhaiyang
     * @date: 2020-11-26 20:56
     * @version: 1.0
     */
    @Pointcut("execution(public * com.xxcw.allowance.service..*.*(..)) && !@annotation(com.xxcw.allowance.common.aop.login.NoLogin)")
    /*@Pointcut("(" +
            " @annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            " @annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            " @annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            " @annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            " @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            " ) " +
            " && !@annotation(com.xxcw.allowance.common.aop.login.NoLogin)")*/
    public void login() {

    }

    /**
     * @Description: 验证登录，已经登录的将用户信息存储到ThreadLocal中，重置redis的超时
     * @author: yuhaiyang
     * @date: 2020-11-26 20:54
     * @version: 1.0
     */
    @Around("login()")
    public Object validate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature method = (MethodSignature)proceedingJoinPoint.getSignature();
        log.info("########################所有接口请求的登陆验证:"+method.getMethod());
        //查看是否登录，如果登录将登录信息放入ThreadLocal中，并重置redis的超时设置
        if (loginRedisService.checkLoginAndResetTime()) {
            return proceedingJoinPoint.proceed();
        }
        return new BaseResponse(10000,"error",null);
    }

}
