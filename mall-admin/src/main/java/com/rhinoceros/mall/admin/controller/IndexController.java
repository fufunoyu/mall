package com.rhinoceros.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/05 14:06
 */
@Controller
public class IndexController {

    /**
     * 返回首页
     *
     * @return
     */
    @RequestMapping({"/", "/index"})
    public String index() {

        return "index";
    }

    /**
     * 返回首页
     *
     * @return
     */
    @RequestMapping({"/test"})
    public String test() {

        return "test";
    }

    /**
     * 返回首页
     *
     * @return
     */
    @RequestMapping({"/test2"})
    public String test2() {

        return "test2";
    }

}
