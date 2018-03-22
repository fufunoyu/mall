package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.po.IndexSlider;
import com.rhinoceros.mall.dao.dao.IndexSliderDao;
import com.rhinoceros.mall.service.impl.exception.common.EntityHasExistedException;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.service.IndexSliderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class IndexSliderServiceImpl implements IndexSliderService {
    @Autowired
    private IndexSliderDao indexSliderDao;
    
    @Override
    public List<IndexSlider> findAll(){
        return indexSliderDao.findAll();
    }
    
    @Transactional
    @Override
    public IndexSlider add(IndexSlider indexSlider){
        if(indexSlider.getTitle() == null){
            log.info("标题不能为空");
            throw new ParameterIsNullException("标题不能为空");
        }
        if(indexSlider.getImageUrl() == null){
            log.info("图片链接不能为空");
            throw new ParameterIsNullException("图片链接不能为空");
        }
        if(indexSlider.getJumpUrl() == null){
            log.info("跳转链接不能为空");
            throw new ParameterIsNullException("跳转链接不能为空");
        }
        IndexSlider slider = indexSliderDao.findByTitle(indexSlider.getTitle());
        if(slider == null){
            indexSliderDao.add(indexSlider);
            return indexSlider;
        }
        else {
            log.info("轮播图已存在");
            throw new EntityHasExistedException("轮播图已存在");
        }
    }
    
    @Transactional
    @Override
    public void deleteById(Long id){
        if(id==null){
            log.info("id不能为空");
            throw new ParameterIsNullException("id不能为空");
        }
        IndexSlider indexSlider = indexSliderDao.findById(id);
        if(indexSlider==null){
            log.info("轮播图不存在");
            throw new EntityNotExistException("轮播图不存在");
        }
        indexSliderDao.deleteById(id);
    }
}
