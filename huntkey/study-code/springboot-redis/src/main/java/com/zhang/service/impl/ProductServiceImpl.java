package com.zhang.service.impl;

import com.zhang.entity.Product;
import com.zhang.mapper.ProductMapper;
import com.zhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangyu
 * @create 2017-07-20 13:56
 **/
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product selectById(long id) {
        return productMapper.selectById(id);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }
}
