package com.xxcw.allowance.service;

import com.xxcw.allowance.common.base.BaseApiService;
import com.xxcw.allowance.common.base.BaseResponse;
import com.xxcw.allowance.bean.MainMenu;
import com.xxcw.allowance.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuhaiyang
 * @date 2021-02-04 9:00
 * Description：TODO
 */
@RestController
public class MenuService extends BaseApiService {
    @Autowired
    private MenuMapper menuMapper;

    @RequestMapping("/menus")
    public BaseResponse getAllMenus(){
        List<MainMenu> mainMenus = menuMapper.getMainMenus();
        System.out.println("成功访问！！！");
        return setResultSuccess(mainMenus);
    }
}

