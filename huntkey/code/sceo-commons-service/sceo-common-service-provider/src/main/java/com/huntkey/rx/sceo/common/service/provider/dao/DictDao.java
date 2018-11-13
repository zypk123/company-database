package com.huntkey.rx.sceo.common.service.provider.dao;

import java.util.List;
import java.util.Map;

import com.huntkey.rx.sceo.common.service.common.model.Dict;

public interface DictDao {
	/**
	 * 查询字典根集合，即parent_id为0的集合
	 * @return
	 */
	List<Dict> selAllDicts();
	
	
	/**
	 * 查询字典子元素
	 * @param value 父节点ID
	 * @return
	 */
	List<Dict> selDictsByParent(String value);
	
	/**
	 * 判断是否有子节点
	 * @param pid
	 * @return
	 */
	int isHaveChild(String pid);
	
	/**
	 * 根据ID查询一条字典记录
	 * @param id
	 * @return
	 */
	Dict selDictById(String id);
	
	int insert(Dict dict);
	
	int update(Dict dict);
	
	int delete(List<String> del_ids);

	 /**
     * 根据CODE值查询字典值的信息
     * @param param code跟type值
     * @return 字典列表
     */
	List<Dict> getDictsByCode(Map<String, String> param);


	/**
	 * 根据CODE与VALUE查询DICT信息
	 * @param code
	 * @param value
	 * @return
	 */
	Dict getDictByCodeVal(Map<String,String> param);

	/**
	 * 根据一组CODE值查询一组字典值
	 * @param codes
	 * @return
	 */
	List<Dict> getDictsByCodes(String[] codes);
}
