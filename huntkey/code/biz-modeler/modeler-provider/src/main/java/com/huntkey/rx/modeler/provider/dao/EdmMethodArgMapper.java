package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmMethodArg;
import com.huntkey.rx.modeler.common.model.EdmMethodArgExample;
import com.huntkey.rx.modeler.provider.annotation.RedisCache;
import com.huntkey.rx.modeler.provider.annotation.RedisEvict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

	/**
	 *
	 * @ClassName: EdmMethodArgMapper
	 * @Description: 方法参数Mapper接口
	 * @author: zhangyu
	 * @date: 2017年4月25日上午9:25:28
	 *
	 */
	public interface EdmMethodArgMapper {

	@RedisCache(type =EdmMethodArg.class )
	List<EdmMethodArg> selectByExample(EdmMethodArgExample example);

	/**
	 * 通过主键id删除参数
	 * 
	 * @param id
	 * @return
	 */
	@RedisEvict(type = EdmMethodArg.class)
	int deleteByPrimaryKey(String id);

	/**
	 * 新增参数
	 * @param edmMethodArg
	 * @return
	 */
	@RedisEvict(type = EdmMethodArg.class)
	int insertSelective(EdmMethodArg edmMethodArg);

	/**
	 * 通过主键id更新参数
	 * @param edmMethodArg
	 * @return
	 */
	@RedisEvict(type = EdmMethodArg.class)
	int  updateByPrimaryKeySelective(EdmMethodArg edmMethodArg);

	/**
	 * 通过id查询参数
	 * 
	 * 
	 * @param id
	 * @return
	 */
	@RedisCache(type =EdmMethodArg.class )
	EdmMethodArg selectByPrimaryKey(String id);

	/**
	 * 修改排序字段
	 * 
	 * @param id
	 * @param seq
	 */
	@RedisEvict(type = EdmMethodArg.class)
	void updateSeqById(@Param("id") String id, @Param("seq") Integer seq);

	/**
	 * 根据ID获取方法参数名
	 * @param id
	 * @return
	 */
	@RedisCache(type =EdmMethodArg.class )
	String geMethodArgNameById(String id);

	@RedisEvict(type = EdmMethodArg.class)
	void updateIsDelByEdmaEdmmId(String edmaEdmmId);

}