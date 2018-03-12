package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.po.IndexSlider;
import com.rhinoceros.mall.dao.dao.IndexSliderDao;
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
        indexSliderDao.add(indexSlider);
        return indexSlider;
    }
    
    @Transactional
    @Override
    public void deleteById(Long id){
        if(id==null){
            log.info("id不能为空");
            throw new ParameterIsNullException("id不能为空");
        }
        
/*        IndexSlider indexSlider = indexSliderDao.findById(id);
        if(indexSlider==null){
            log.info("轮播图不存在");
            throw new EntityNotExistException("轮播图不存在");
        }*/
        indexSliderDao.deleteById(id);
    }
}
