# 项目说明
## IDEA提交项目到github
 ### [参考网址](https://www.cnblogs.com/gujun1998/p/11375625.html)
>   1、依次选择 File ---> Settings ---> Version Control ---> Git 在 Path to Git executable 中选择自己安装的git的路径，然后可以点击右边的Test测试，出现git的版本表示成功，如下图1所示。

>   2、 在Version Control中选择Github 点击右上方的加号，在弹出的界面中添加自己的github账号和密码，如下图2所示，显示出自己的github账号表示成功。

>   3、 在idea上方依次选择 VCS ---> Import into Version Control ---> Create Git Repository , 如图3所示，点击 Create Git Repository,出现如下图3所示

>   4、 在第3步后弹出的界面中选择本地要提交的项目的路径。如下图4所示。

>   5、 在弹出的 Git init 界面中点击 Yes 。

>   6、 右击项目选择 Git ---> Add

>   7、 右击项目选择 Git ---> Commit Directory ，将该项目提交到本地仓库，如下图5所示。

>   8、 在弹出的 Commit Changes 界面中输入自己对提交内容的描述，如下图6所示，点击commit,将代码提交到本地仓库完成。

>   9、 将该项目 push (上传)到新建的远程仓库中，(创建远程仓库步骤如上述所示)，如下图7所示。

>   10、 在弹出的界面中点击 Define remote，URL选择远程仓库的URL即可，选择OK，如果出现自己对提交内容的描述，点击对提交内容的描述，(如下图8所示)，选择push，稍等片刻，idea会提交项目 push successful ,项目上传成功,在github中可以看到提交内容，如下图9所示。

## 统一处理跨域
```$xslt
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yuhaiyang
 * @date 2021-02-03 8:28
 * Description：全局配置跨域
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080","null")
                .allowedMethods("GET","POST","PUT","OPTIONS","DELETE")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
```
## 整合统一返回结果
* Constants.java
```$xslt
package com.xxcw.allowance.common.base;

public interface Constants {
	// 响应请求成功
	String HTTP_RES_CODE_200_VALUE = "success";
	// 系统错误
	String HTTP_RES_CODE_400_VALUE = "fail";
	// 响应请求成功code
	Integer HTTP_RES_CODE_200 = 200;
	// 系统错误
	Integer HTTP_RES_CODE_400 = 400;


	// 未关联QQ账号
	Integer HTTP_RES_CODE_201 = 201;
	// 发送邮件
	String MSG_EMAIL = "email";
	// 会员token
	String TOKEN_MEMBER = "TOKEN_MEMBER";
	// 用户有效期 90天
	Long TOKEN_MEMBER_TIME = (long) (60 * 60 * 24 * 90);
	int COOKIE_TOKEN_MEMBER_TIME = (60 * 60 * 24 * 90);
	// cookie 会员 totoken 名称
	String COOKIE_MEMBER_TOKEN = "cookie_member_token";
	// 微信注册码存放rediskey
	String WEIXINCODE_KEY = "weixin.code";
	// 微信注册码有效期30分钟
	Long WEIXINCODE_TIMEOUT = 1800l;

	// 用户信息不存在
	Integer HTTP_RES_CODE_EXISTMOBILE_203 = 203;
}
```
* BaseResponse.java
```$xslt
package com.xxcw.allowance.common.base;

import lombok.Data;

/**
 * @Description: 基本返回值类
 * @author: yuhaiyang
 * @date: 2020-11-05 15:30
 * @version: 1.0
 */
@Data
public class BaseResponse<T> {
    private Integer code;//返回代码
    private String msg;//返回消息
    private T data;//返回的附带数据

    public BaseResponse() {

    }

    public BaseResponse(Integer code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
```
* BaseApiService
```$xslt
package com.xxcw.allowance.common.base;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author yuhaiyang
 * @date 2020-11-27 8:18
 * Description：直接封装统一返回结果集
 */
@Data
@Component
public class BaseApiService<T> {
    // 通用封装
    public BaseResponse<T> setResult(Integer code, String msg, T data) {
        return new BaseResponse(code, msg, data);
    }

    // 返回错误
    public BaseResponse<T> setResultError() {
        return setResult(Constants.HTTP_RES_CODE_400, Constants.HTTP_RES_CODE_400_VALUE, null);
    }

    // 返回错误，可以传msg
    public BaseResponse<T> setResultError(String msg) {
        return setResult(Constants.HTTP_RES_CODE_400, msg, null);
    }

    //返回错误，可以传code，msg
    public BaseResponse<T> setResultError(Integer code, String msg) {
        return setResult(code, msg, null);
    }

    // 返回成功，沒有data值
    public BaseResponse<T> setResultSuccess() {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
    }

    // 返回成功，可以传msg
    public BaseResponse<T> setResultSuccess(String msg) {
        return setResult(Constants.HTTP_RES_CODE_200, msg, null);
    }

    // 返回成功，可以传data值
    public BaseResponse<T> setResultSuccess(T data) {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
    }
}
```
## 统一日志处理

## 整合统一异常处理

## 整合redis进行登录校验

## 整合swigger
* pom.xml引入
```$xslt
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.8.0</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.8.0</version>
</dependency>
```
* 访问地址
``` 9000是项目的端口
http://localhost:9000/swagger-ui.html
```

