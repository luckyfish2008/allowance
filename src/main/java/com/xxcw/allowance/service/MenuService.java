package com.xxcw.allowance.service;

import com.xxcw.allowance.bean.SubMenu;
import com.xxcw.allowance.common.base.BaseApiService;
import com.xxcw.allowance.common.base.BaseResponse;
import com.xxcw.allowance.bean.MainMenu;
import com.xxcw.allowance.mapper.MenuMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuhaiyang
 * @date 2021-02-04 9:00
 * Description：TODO
 */
@Api("菜单接口")
@RestController
public class MenuService extends BaseApiService {
    @Autowired
    private MenuMapper menuMapper;

    @GetMapping("/menus")
    @ApiOperation(value = "菜单接口", notes = "查询全部菜单")
    public BaseResponse getAllMenus(){
        List<MainMenu> mainMenus = menuMapper.getMainMenus();
        /*
        // 子分支一
        SubMenu sub1_1 = new SubMenu(101, "用户列表", "/user");
        SubMenu sub1_2 = new SubMenu(102, "修改权限", "/rights");
        SubMenu sub1_3 = new SubMenu(103, "运动模块", "/sport");
        SubMenu sub1_4 = new SubMenu(104, "商品模块", "/goods");
        // 子分支二
        SubMenu sub2_1 = new SubMenu(201, "运动科普", "/Introduction");
        SubMenu sub2_2 = new SubMenu(202, "卡路里查询", "/calories");
        SubMenu sub2_3 = new SubMenu(203, "营养配餐", "/food");
        SubMenu sub2_4 = new SubMenu(204, "个人计划", "/plan");

        // 分支一集合
        ArrayList<SubMenu> subMenus1 = new ArrayList<>();
        subMenus1.add(sub1_1);
        subMenus1.add(sub1_2);
        subMenus1.add(sub1_3);
        subMenus1.add(sub1_4);
        // 分支二集合
        ArrayList<SubMenu> subMenus2 = new ArrayList<>();
        subMenus2.add(sub2_1);
        subMenus2.add(sub2_2);
        subMenus2.add(sub2_3);
        subMenus2.add(sub2_4);

        // 主分支
        MainMenu main1 = new MainMenu(100, "权限管理", "/admin", subMenus1);
        // 次分支
        MainMenu main2 = new MainMenu(200, "运动平台", "/use", subMenus2);
        ArrayList<MainMenu> mainMenus = new ArrayList<>();
        mainMenus.add(main1);
        mainMenus.add(main2);
        */
        return setResultSuccess(mainMenus);
    }
}

