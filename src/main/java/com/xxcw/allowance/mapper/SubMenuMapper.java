package com.xxcw.allowance.mapper;

import com.xxcw.allowance.bean.SubMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubMenuMapper {

    //根据主菜单的mid获取子菜单集合
    @Select("select id,title, path from submenu where mid = #{mid}")
    List<SubMenu> selectSubMenuByMid(@Param("mid") Integer mid);
}
