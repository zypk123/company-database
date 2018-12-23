package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhang.dao.DataDicDao;
import com.zhang.entity.DataDic;
import com.zhang.service.DataDicService;

/**
 * 数据字典DataDic接口实现类
 * @author zhangyu
 *
 */
@Service("dataDicService")
public class DataDicServiceImpl implements DataDicService{
	
	@Resource
	DataDicDao dataDicDao;

	@Override
	public List<DataDic> find(Map<String, Object> map) {
		return dataDicDao.find(map);
	}

	@Override
	public List<DataDic> findAll() {
		return dataDicDao.findAll();
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return dataDicDao.getTotal(map);
	}

	@Override
	public int add(DataDic dataDic) {
		return dataDicDao.add(dataDic);
	}

	@Override
	public int delete(Integer id) {
		return dataDicDao.delete(id);
	}

	@Override
	public int update(DataDic dataDic) {
		return dataDicDao.update(dataDic);
	}
}
