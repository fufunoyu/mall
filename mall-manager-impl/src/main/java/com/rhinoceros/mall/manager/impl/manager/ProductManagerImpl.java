package com.rhinoceros.mall.manager.impl.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhinoceros.mall.core.po.Category;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.query.Order;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.dao.dao.CategoryDao;
import com.rhinoceros.mall.dao.dao.ProductDao;
import com.rhinoceros.mall.manager.impl.exception.BaseManagerException;
import com.rhinoceros.mall.manager.manager.ProductManager;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
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
@Slf4j
public class ProductManagerImpl implements ProductManager {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private TransportClient client;

    private ObjectMapper mapper = new ObjectMapper();

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

    @Override
    public Long countDeepByCategoryId(Long categoryId) {
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
        return productDao.countByCategoryIdIn(ids);
    }


    @Override
    public int add(Product product) {
        //添加数据到数据库
        int modify = productDao.add(product);
        try {
            byte[] bytes = mapper.writeValueAsBytes(product);
            client.prepareIndex("mall", "product", product.getId().toString())
                    .setSource(bytes, XContentType.JSON)
                    .get();
            return modify;
        } catch (Exception e) {
            log.error("json转换失败");
            throw new BaseManagerException("json转换失败");
        }
    }

    @Override
    public int deleteById(Long id) {
        int modify = productDao.deleteById(id);
        client.prepareDelete("mall", "product", id.toString());
        return modify;
    }

    @Override
    public int updateSelectionById(Product product) {
        int modify = productDao.updateSelectionById(product);
        try {
            byte[] bytes = mapper.writeValueAsBytes(product);
            client.prepareUpdate("mall", "product", product.getId().toString())
                    .setDoc(bytes, XContentType.JSON);
            return modify;
        } catch (Exception e) {
            log.error("json转换失败");
            throw new BaseManagerException("json转换失败");
        }
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> query(String queryString, PageQuery pageQuery) {

        //根据商品名称和描述查询
        BoolQueryBuilder builder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name", queryString))
                .should(QueryBuilders.matchQuery("description", queryString));
        SearchRequestBuilder searchResponseBuilder = client.prepareSearch("mall")
                .setTypes("product")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(builder)
                .setFrom(pageQuery.getOffset())
                .setSize(pageQuery.getSize());
        List<Order> orders = pageQuery.getOrders();
        if (orders != null && orders.size() > 0) {
            for (Order order : orders) {
                searchResponseBuilder.addSort(order.getField(), order.getDirection() == Order.Direction.DESC ? SortOrder.DESC : SortOrder.ASC);
            }
        }

        SearchResponse searchResponse = searchResponseBuilder.get();
        try {
            List<Product> products = new LinkedList<Product>();
            for (SearchHit hit : searchResponse.getHits()) {
                Product product = mapper.readValue(hit.getSourceAsString(), Product.class);
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            log.error("json转换失败");
            throw new BaseManagerException("json转换失败");
        }
    }

    @Override
    public Long countQuery(String queryString) {
        //根据商品名称和描述查询
        BoolQueryBuilder builder = QueryBuilders
                .boolQuery()
                .should(QueryBuilders.matchQuery("name", queryString))
                .should(QueryBuilders.matchQuery("description", queryString));
        SearchResponse searchResponse = client.prepareSearch("mall")
                .setTypes("product")
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(builder)
                .get();
        return searchResponse.getHits().totalHits;
    }

    @Override
    public Long countDeepByCategoryId(Long categoryId) {
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
        return productDao.countByCategoryIdIn(ids);
    }

}
