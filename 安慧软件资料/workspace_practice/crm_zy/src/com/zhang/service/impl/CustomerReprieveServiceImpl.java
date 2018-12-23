package com.zhang.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zhang.dao.CustomerReprieveDao;
import com.zhang.entity.CustomerReprieve;
import com.zhang.service.CustomerReprieveService;

/**
 * 客户流失暂缓措施Service实现类
 * @author Administrator
 *
 */
@Service("customerReprieveService")
public class CustomerReprieveServiceImpl implements CustomerReprieveService{

	@Resource
	private CustomerReprieveDao CustomerReprieveDao;
	
	@Override
	public List<CustomerReprieve> find(Map<String, Object> map) {
		return CustomerReprieveDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return CustomerReprieveDao.getTotal(map);
	}

	@Override
	public int update(CustomerReprieve customerReprieve) {
		return CustomerReprieveDao.update(customerReprieve);
	}

	@Override
	public int add(CustomerReprieve customerReprieve) {
		return CustomerReprieveDao.add(customerReprieve);
	}

	@Override
	public int delete(Integer id) {
		return CustomerReprieveDao.delete(id);
	}

}
