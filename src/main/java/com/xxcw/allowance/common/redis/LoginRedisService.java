package com.xxcw.allowance.common.redis;

import com.google.gson.Gson;
import com.xxcw.allowance.common.utils.LoginUserUtils;
import com.xxcw.allowance.common.utils.MapUtils;
import com.xxcw.allowance.common.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yanglimou
 * @date 2019-03-08 10:28:38
 * @Description 专门针对登陆相关的redis操作
 */
@Component
@Slf4j
public class LoginRedisService {
    //前端传递过来的uuid的header中的key名
    public static final String COOKIE_UUID_KEY = "_uuid";
    //前端传递过来的gh的header中的key名
    public static final String COOKIE_GH_KEY = "_gh";
    //前端传递过来的gh的header中的xtbh名
    public static final String XTBH_KEY = "_xtbh";
    //给前端本地缓存的用户信息key名
    private static final String COOKIE_USER_KEY = "_user";
    private static final String LOGIN_IP = "LOGIN_IP";
    //登陆相关信息存储redis的key前缀
    private static final String REDIS_KEY_PREFIX = "login_";
    @Value("${redis.session.timeout}")
    private long redisSessionTimeout;
    @Autowired
    private BaseRedisService baseRedisService;

    //登陆后将信息写入redis中
    public Map login(String gh, Map user) {
        //产生一个唯一标识
        String uuid = UUID.randomUUID().toString();
        //获取登陆用户的ip地址
        String ip = RequestUtils.getIp();
        user.put(LOGIN_IP, ip);
        //存储redis
        //key是login_工号_唯一标识
        //存储的值是用户信息map
        //设置过期时间，具体数据配置在config.properties中
        baseRedisService.set(getRedisKey(gh, uuid), new Gson().toJson(user), redisSessionTimeout);
        //给前端返回map，以便于前端将其存储到localstorage中
        Map map = new HashMap();
        map.put(COOKIE_UUID_KEY, uuid);
        map.put(COOKIE_GH_KEY, gh);
        map.put(COOKIE_USER_KEY, user);
        return map;
    }

    //判断登陆并重新设置过期时间,true表明成功
    public boolean checkLoginAndResetTime() {
        HttpServletRequest request = RequestUtils.getRequest();
        //从request的请求头中取出uuid和gh参数   ajaxSetup中提交的异步请求中包含了下面的参数
        String uuid = request.getHeader(COOKIE_UUID_KEY);
        String gh = request.getHeader(COOKIE_GH_KEY);
        String xtbh = request.getHeader(XTBH_KEY);
        log.info("######################检测用户是否已经登录######################");
        log.info("request header uuid：" + uuid);
        log.info("request header gh：" + gh);
        log.info("request header xtbh：" + xtbh);
        if (StringUtils.isNoneBlank(gh, uuid)) {
            String redisKey = getRedisKey(gh, uuid);//获取redis中的用户的key
            if (baseRedisService.hasKey(redisKey)) {
                Map user = new Gson().fromJson(baseRedisService.get(redisKey), Map.class);
                //主责单位编号是同通过前端传递过来的xtbh来查询到的
                if (StringUtils.isNotBlank(xtbh)) {
                    /*
                    //形如：
                    ZZDWS={
                            21={XTBH=21, ZZDWDM=100245, ZZDWBH=3014.0, ZZDWMC=治安总队, NKLDBZ=0},
                            30={XTBH=30, ZZDWDM=100245, ZZDWBH=3014.0, ZZDWMC=治安总队, NKLDBZ=0},
                            33={XTBH=33, ZZDWDM=100245, ZZDWBH=3014.0, ZZDWMC=治安总队, NKLDBZ=0},
                            28={XTBH=28, ZZDWDM=100245, ZZDWBH=3014.0, ZZDWMC=治安总队, NKLDBZ=0}
                    }
                    */
                    Map zzdws = (Map) user.get("ZZDWS");//获取所有的主责单位
                    Map zzdw = MapUtils.getMap(zzdws, xtbh);//根据登录进入的子系统的xtbh获取对应的zzdw，同时存储到对象中
                    log.info("zzdw:"+zzdw);
                    user.put("ZZDWBH", MapUtils.getInt(zzdw, "ZZDWBH"));
                    user.put("ZZDWMC", MapUtils.getString(zzdw, "ZZDWMC"));
                    user.put("ZZDWDM", MapUtils.getString(zzdw, "ZZDWDM"));
                    user.put("NKLDBZ",MapUtils.getString(zzdw,"NKLDBZ"));//是否是子系统的领导
                }
                user.put("XTBH",xtbh);
                log.info("user:"+user);
                //把用户信息存储到threadlocal中
                LoginUserUtils.setLoginUser(user);
                //重置用户信息过期时间
                baseRedisService.expire(redisKey, redisSessionTimeout);
                return true;
            }
        }
        return false;
        //判断redis中有没有，如果没有说明没有登陆
    }

    //获取用户存储在redis中的key
    public String getRedisKey(String gh, String uuid) {
        return REDIS_KEY_PREFIX + gh + "_" + uuid;
    }

    //获得用户的UUID key如：myapp_login_zhangsan_xxxxxxxxxxxxxx   gh:zhangsan
    private String getLoginUuidByKeyAndGh(String key, String gh) {
        return key.substring((REDIS_KEY_PREFIX + gh + "_").length());
    }

    //获取用户是否已经登录了
    public Map getOthersLoginMessage(String gh) {
        //去redis中寻找关于该用户的key
        String key = baseRedisService.getOneKeyLike(REDIS_KEY_PREFIX + gh + "_");
        //从redis中没有找到用户对应的key值，证明用户没有登录过，返回null
        if (StringUtils.isBlank(key)){
            return null;
        }
        //从redis中找到了有关用户的key，对key进行截取获取当前用户在redis中存储的uuid的值
        String login_uuid = getLoginUuidByKeyAndGh(key, gh);
        HttpServletRequest request = RequestUtils.getRequest();
        //从request的请求头中取出uuid参数
        String uuid = request.getHeader(COOKIE_UUID_KEY);
        //redis中存储的uuid和用户请求头中的一致，证明没有从其他地方登录，返回null
        if (login_uuid.equals(uuid)){
            return null;
        }
        //获取登陆用户信息
        String loginMessage = baseRedisService.get(getRedisKey(gh, login_uuid));
        return new Gson().fromJson(loginMessage, Map.class);
    }

    //根据gh和uuid获取登录用户信息
    public Map getLoginMessage(){
        HttpServletRequest request = RequestUtils.getRequest();
        //从request的请求头中取出uuid和gh参数   ajaxSetup中提交的异步请求中包含了下面的参数
        String uuid = request.getHeader(COOKIE_UUID_KEY);
        String gh = request.getHeader(COOKIE_GH_KEY);
        if (StringUtils.isNoneBlank(gh, uuid)) {
            String redisKey = getRedisKey(gh, uuid);//获取redis中的用户的key
            if (baseRedisService.hasKey(redisKey)) {
                Map user = new Gson().fromJson(baseRedisService.get(redisKey), Map.class);
                return user;
            }
        }
        return null;
    }

    public void setOthersSignOff(String gh) {
        baseRedisService.removeByKeyLike(REDIS_KEY_PREFIX + gh + "_");
    }
}
