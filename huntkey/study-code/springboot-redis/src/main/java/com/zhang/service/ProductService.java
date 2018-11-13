package com.zhang.service;

import com.zhang.entity.Product;

/**
 * @author zhangyu
 * @create 2017-07-20 13:54
 **/
public interface ProductService {

    /**
     * 通过ID查询产品信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    Product selectById(long id) throws Exception;

    /**
     * 更新产品信息
     *
     * @param product
     * @throws Exception
     */
    int updateProduct(Product product) throws Exception;
}
