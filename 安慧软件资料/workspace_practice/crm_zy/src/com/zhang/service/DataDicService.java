package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.entity.DataDic;

/**
 * 数据字典DataDic接口
 * @author zhangyu
 *
 */
public interface DataDicService {
	
	/**
	 * 查询数据字典集合
	 * @param map
	 * @return
	 */
	public List<DataDic> find(Map<String,Object> map);//用map为参数，是为了迎合mybatis框架
	
	/**
	 * 查询所有的数据字典值
	 * @return
	 */
	public List<DataDic> findAll();
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加数据字典
	 * @param dataDic
	 * @return 添加的记录数
	 */
	public int add(DataDic dataDic);
	
	/**
	 * 删除数据字典
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 修改数据字典
	 * @param dataDic
	 * @return
	 */
	public int update(DataDic dataDic);

}
