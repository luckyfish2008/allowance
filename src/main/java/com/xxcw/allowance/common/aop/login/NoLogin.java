package com.xxcw.allowance.common.aop.login;

import java.lang.annotation.*;

/**
 * @author yanglimou
 * @date 2019-03-08 09:13:39
 * @Description 自定义注解
 * 不需要登陆验证的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoLogin {
}
