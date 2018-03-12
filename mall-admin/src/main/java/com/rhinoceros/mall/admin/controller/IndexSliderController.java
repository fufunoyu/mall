package com.rhinoceros.mall.admin.controller;


import com.rhinoceros.mall.core.po.IndexSlider;
import com.rhinoceros.mall.service.service.IndexSliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 创建轮播图管理控制器
 */
@Controller
@RequestMapping("/slideshow")
public class IndexSliderController {
//定义要调用的业务逻辑
    @Autowired
    private IndexSliderService indexSliderService;

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
    public List<IndexSlider> getSlideshowList(){
        List<IndexSlider> indexSliderList = indexSliderService.findAll();
        return indexSliderList;
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
            indexSliderService.deleteById(id);
        }
        return "{\"result\":\"success\"}";
    }

    @ResponseBody
    @RequestMapping("/addslideshow.json")
    public IndexSlider addSlideshow(IndexSlider indexSlider){

        return indexSlider;
    }
}
