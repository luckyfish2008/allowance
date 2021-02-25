package com.xxcw.allowance.mapper;

import com.xxcw.allowance.bean.MainMenu;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author yuhaiyang
 * @date 2021-02-04 9:01
 * Description：TODO
 */
@Repository
public interface MenuMapper {
    /*
    1）@Results的基本用法。当数据库字段名与实体类对应的属性名不一致时，可以使用@Results映射来将其对应起来。column为数据库字段名，porperty为实体类属性名，jdbcType为数据库字段数据类型，id为是否为主键。
    2）@One的用法。当我们需要通过查询到的一个字段值作为参数，去执行另外一个方法来查询关联的内容，而且两者是一对一关系时，可以使用@One注解来便捷的实现。比如当我们需要查询用户信息以及其地址时，需要以查询到的userId为参数，来执行AddressMapper中的getAddressByUserId方法，从而获得用户的地址信息。可以使用如下代码。
    3）@Many的用法。与@One类似，只不过如果使用@One查询到的结果是多行，会抛出TooManyResultException异常，这种时候应该使用的是@Many注解，实现一对多的查询。比如在需要用户所购买的书信息时。
    4）fetch = FetchType.EAGER，如果是EAGER，那么表示取出这条数据时，它关联的数据也同时取出放入内存中。如果是LAZY，那么取出这条数据时，它关联的数据并不取出来，在同一个session中，什么时候要用，就什么时候取(再次访问数据库)。
    */
    //通过注解的方式实现菜单
    /*
    @Select(" SELECT * FROM mainmenu ")
    @Results({@Result(id=true,property = "id" ,column = "id"),
            @Result(property = "title" ,column = "title"),
            @Result(property = "path" ,column = "path"),
            @Result(property = "slist", column = "id", javaType = List.class,
                    many = @Many(select="com.xxcw.allowance.mapper.SubMenuMapper.selectSubMenuByMid",fetchType= FetchType.EAGER))
    })
    */
    List<MainMenu> getMainMenus();

    @Select("SELECT mm.*,sm.id as sid ,sm.title as stitle,sm.path as spath FROM mainmenu mm ,submenu sm WHERE mm.id = sm.mid")
    List<Map> findMeanus();

}
