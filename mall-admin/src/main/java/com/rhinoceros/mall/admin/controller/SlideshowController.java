package com.rhinoceros.mall.admin.controller;


import com.rhinoceros.mall.core.po.Slideshow;
import com.rhinoceros.mall.service.service.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/**
 * 创建轮播图管理控制器
 */
@Controller
@RequestMapping("/slideshow")
public class SlideshowController {
//定义要调用的业务逻辑
    @Autowired
    private SlideshowService slideshowService;

    @RequestMapping()
    public String showSlideshowList() {
        return "include/slideshow";
    }

    /**
     * 获取所有轮播图信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/list.json")
    public List<Slideshow> getSlideshowList(){
        List<Slideshow> slideshowList = slideshowService.findAll();
        return slideshowList;
    }

    /**
     * 删除轮播图
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteslideshow.json")
    public String deleteSlideshow(@RequestParam("ids[]") List<Long> ids){
        for (Long id : ids){
            slideshowService.deleteById(id);
        }
        return "{\"result\":\"success\"}";
    }

    @ResponseBody
    @RequestMapping("/addslideshow.json")
    public Slideshow addSlideshow(Slideshow slideshow){

        return slideshow;
    }
}
