package com.xxcw.allowance.mapper;

import com.xxcw.allowance.bean.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuhaiyang
 * @date 2021-02-04 9:01
 * Description：TODO
 */
@Repository
public interface MenuMapper {
    public List<MainMenu> getMainMenus();
}
