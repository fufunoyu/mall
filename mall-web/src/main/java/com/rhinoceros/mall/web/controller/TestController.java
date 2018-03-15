package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.manager.manager.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/12 22:15
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ProductManager productManager;

    @PostMapping
    public Product add(@RequestBody Product product) {
        productManager.add(product);
        return product;
    }

    @GetMapping
    public List<Product> query(@RequestParam("query") String query) {
        return productManager.query(query, new PageQuery(1, 10));
    }
}
