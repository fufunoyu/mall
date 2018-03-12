package com.rhinoceros.mall.manager.impl.manager;

import com.rhinoceros.mall.core.po.Category;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.dao.dao.CategoryDao;
import com.rhinoceros.mall.dao.dao.ProductDao;
import com.rhinoceros.mall.manager.manager.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/08 14:14
 */
@Component
public class ProductManagerImpl implements ProductManager {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;

    public List<Product> findDeepByCategoryId(Long categoryId, PageQuery pageQuery) {
        //查找指定id的分类下的所有子分类
        List<Category> list = categoryDao.findChildrenById(categoryId);
        for (int i = 0; i < list.size(); i++) {
            list.addAll(categoryDao.findChildrenById(list.get(i).getId()));
        }
        //获取该分类和子分类的id
        List<Long> ids = new LinkedList<Long>();
        ids.add(categoryId);
        for (Category category : list) {
            ids.add(category.getId());
        }

        // 根据分类id查找产品列表
        return productDao.findByCategoryIdIn(ids, pageQuery);
    }

    /**
     * 添加商品
     *
     * @param product
     */
    public void add(Product product) {


    }
}
