package com.zhang.controller;

import com.zhang.entity.Product;
import com.zhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyu
 * @create 2017-07-20 13:43
 **/
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProductInfo/{id}")
    public Product getProductInfo(@PathVariable("id") Long productId) {
        Product product = new Product();
        try {
            product = productService.selectById(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @PutMapping("/updateProductInfo")
    public int updateProductInfo(@RequestBody Product newProduct) {
        int retInt = 0;
        try {
            retInt = productService.updateProduct(newProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retInt;
    }
}
