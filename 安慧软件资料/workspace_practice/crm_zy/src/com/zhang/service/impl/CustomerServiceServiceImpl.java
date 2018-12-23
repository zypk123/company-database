package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zhang.dao.CustomerServiceDao;
import com.zhang.entity.CustomerService;
import com.zhang.service.CustomerServiceService;

/**
 * 客户服务Service接口实现类
 * @author zhangyu
 *
 */
@Service("customerServiceService")
public class CustomerServiceServiceImpl implements CustomerServiceService{
	
	@Resource
	CustomerServiceDao customerServiceDao;

	@Override
	public int add(CustomerService customerService) {
		return customerServiceDao.add(customerService);
	}

	@Override
	public List<CustomerService> find(Map<String, Object> map) {
		return customerServiceDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return customerServiceDao.getTotal(map);
	}

	@Override
	public int update(CustomerService customerService) {
		return customerServiceDao.update(customerService);
	}
}
