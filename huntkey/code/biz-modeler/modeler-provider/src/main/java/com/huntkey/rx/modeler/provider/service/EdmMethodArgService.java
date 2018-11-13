package com.huntkey.rx.modeler.provider.service;

import java.util.List;

import com.huntkey.rx.modeler.common.model.EdmMethodArg;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodArgVO;

/**
 * 
 * @ClassName: EdmMethodArgService
 * @Description: 方法参数Service接口
 * @author: zhangyu
 * @date: 2017年4月25日上午9:25:53
 *
 */
public interface EdmMethodArgService {

	/**
	 * 通过主键id删除参数
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 新增方法输入参数 
	 * 
	 * @param edmMethodArg
	 * @return
	 */
	int insertInputArg(EdmMethodArg edmMethodArg);

	/**
	 * 新增方法返回值
	 * 
	 * @param edmMethodArg
	 * @return
	 */
	int insertReturnArg(EdmMethodArg edmMethodArg);
	
	/**
	 * 通过方法id查找输入参数
	 * 
	 * @param edmmId
	 * @return
	 */
	List<EdmMethodArgVO> selectInputArgsByEdmmId(String edmmId);

	/**
	 * 通过方法id查找返回值
	 * 
	 * @param id
	 * @return
	 */
	EdmMethodArgVO selectRetArgsByEdmmId(String id);

	/**
	 * 通过主键id更新参数
	 * 
	 * @param edmMethodArg
	 * @return
	 */
	int updateByPrimaryKey(EdmMethodArg edmMethodArg);

	/**
	 * 参数的移动
	 * @param ids
	 */
	void move(String[] ids);
	
	/**
	 * 校验方法参数名称的唯一性
	 * @param edmaName
	 * @param edmaEdmmId
	 * @return
	 */
	String checkUnique(String edmaName,String edmaEdmmId);

	/**
	 * 根据ID获取方法参数名
	 * @param id
	 * @return
	 */
	String geMethodArgNameById(String id);

	/**
	 * 批量删除参数
	 * @param ids
	 */
	void deleteInputArgs(String[] ids);
}