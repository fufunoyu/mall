package com.rhinoceros.mall.service.impl.service;


import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.InputStreamWithFileName;
import com.rhinoceros.mall.manager.manager.FileUploadManager;
import com.rhinoceros.mall.manager.manager.ProductManager;
import com.rhinoceros.mall.service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @author Rhys Xia
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductManager productManager;

    @Autowired
    private FileUploadManager fileUploadManager;


    /**
     * 根据商品id获取商品信息，并封装成商品展示信息对象
     *
     * @param id 商品id号
     * @return 商品信息展示对象
     */
    @Override
    public Product findById(Long id) {
        return productManager.findById(id);
    }


    /**
     * 通过分类id查找当前及其子节点下所有商品
     *
     * @param categoryId 分类id
     * @param pageQuery  分页条件
     * @return
     */
    @Override
    public List<Product> findDeepByCategoryId(Long categoryId, PageQuery pageQuery) {
        return productManager.findDeepByCategoryId(categoryId, pageQuery);
    }

    @Override
    public List<Product> query(String query, PageQuery pageQuery) {
        return productManager.query(query, pageQuery);
    }

    @Override
    public Long countQuery(String query) {
        return productManager.countQuery(query);
    }

    /**
     * 通过分类id查询当前及其子节点的数量
     *
     * @param categoryId
     * @return
     */
    @Override
    public Long countDeepByCategoryId(Long categoryId) {
        return productManager.countDeepByCategoryId(categoryId);
    }

    /**
     * 通过id删除商品
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        productManager.deleteById(id);
    }

    /**
     * 通过id修改商品
     *
     * @param product
     * @param inputStreamWithFileNames
     */
    @Override
    @Transactional
    public void updateSelectionById(final Product product, List<InputStreamWithFileName> inputStreamWithFileNames) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(new Date());
        String savePath = "product/" + dateString;
        List<String> urls = new LinkedList<>();
        for (InputStreamWithFileName inputStreamWithFileName : inputStreamWithFileNames) {
            String saveName = UUID.randomUUID().toString() + "_" + inputStreamWithFileName.getFileName();
            String url = fileUploadManager.upload(inputStreamWithFileName.getIs(), savePath, saveName);
            urls.add(url);
        }
        product.setImageUrls(urls.stream()
                .reduce((left, acc) -> left + Product.IMAGE_SEPARATION + acc)
                .orElse("")
        );
        productManager.updateSelectionById(product);
    }

    /**
     * 通过id增添商品
     *
     * @param product
     */
    @Override
    @Transactional
    public void addSelectionById(final Product product, List<InputStreamWithFileName> inputStreamWithFileNames) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(new Date());
        String savePath = "product/" + dateString;
        List<String> urls = new LinkedList<>();
        for (InputStreamWithFileName inputStreamWithFileName : inputStreamWithFileNames) {
            String saveName = UUID.randomUUID().toString() + "_" + inputStreamWithFileName.getFileName();
            String url = fileUploadManager.upload(inputStreamWithFileName.getIs(), savePath, saveName);
            urls.add(url);
        }
        product.setImageUrls(urls.stream()
                .reduce((left, acc) -> left + Product.IMAGE_SEPARATION + acc)
                .orElse("")
        );
        productManager.add(product);
    }


}
