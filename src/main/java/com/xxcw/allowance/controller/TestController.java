package com.xxcw.allowance.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuhaiyang
 * @date 2021-02-03 8:26
 * Description：TODO
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test TestController ";
    }
}
