package com.zhang.mapper;

import com.zhang.entity.Product;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangyu
 * @create 2017-07-20 13:52
 **/
public interface ProductMapper {

    Product selectById(@Param("id") long id);

    int updateProduct(Product product);
}