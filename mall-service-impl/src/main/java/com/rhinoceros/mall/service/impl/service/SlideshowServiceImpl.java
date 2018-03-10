package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.po.Slideshow;
import com.rhinoceros.mall.dao.dao.SlideshowDao;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.service.SlideshowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SlideshowServiceImpl implements SlideshowService {
    @Autowired
    private SlideshowDao slideshowDao;
    
    @Override
    public List<Slideshow> findAll(){
        return slideshowDao.findAll();
    }
    
    @Transactional
    @Override
    public Slideshow add(Slideshow slideshow){
        slideshowDao.add(slideshow);
        return slideshow;
    }
    
    @Transactional
    @Override
    public void deleteById(Long id){
        if(id==null){
            log.info("id不能为空");
            throw new ParameterIsNullException("id不能为空");
        }
        
/*        Slideshow slideshow = slideshowDao.findById(id);
        if(slideshow==null){
            log.info("轮播图不存在");
            throw new EntityNotExistException("轮播图不存在");
        }*/
        slideshowDao.deleteById(id);
    }
}
