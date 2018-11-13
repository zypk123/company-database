package com.huntkey.rx.modeler.provider.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huntkey.rx.commons.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.EdmMethodArg;
import com.huntkey.rx.modeler.common.model.EdmMethodArgExample;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodArgVO;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.EdmCodeMapper;
import com.huntkey.rx.modeler.provider.dao.EdmMethodArgMapper;
import com.huntkey.rx.modeler.provider.service.EdmMethodArgService;

/**
 * 
 * @ClassName: EdmMethodArgServiceImpl
 * @Description: 方法参数实现类
 * @author: zhangyu
 * @date: 2017年4月25日上午9:29:05
 *
 */
@Service
@Transactional(readOnly = true)
public class EdmMethodArgServiceImpl implements EdmMethodArgService {

	@Autowired
	EdmMethodArgMapper edmMethodArgMapper; // 注入dao

	@Autowired
	EdmCodeMapper edmCodeMapper;

	@Override
	@Transactional(readOnly = false)
	public int deleteByPrimaryKey(String id) {
		return edmMethodArgMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public int insertInputArg(EdmMethodArg edmMethodArg) {
		edmMethodArg.setEdmaType((byte) 1); // 输入参数
		edmMethodArg.setId(UuidCreater.uuid()); // 主键采用uuid生成
		edmMethodArg.setIsDel((byte) 0); // 默认为未删除
		edmMethodArg.setAddtime(new Date()); // 默认创建时间是当前时间
		edmMethodArg.setModtime(new Date()); // 默认维护时间是当前时间
		return edmMethodArgMapper.insertSelective(edmMethodArg);
	}

	@Override
	@Transactional(readOnly = false)
	public int insertReturnArg(EdmMethodArg edmMethodArg) {
		edmMethodArg.setEdmaType((byte) 2); // 返回值
		edmMethodArg.setId(UuidCreater.uuid()); // 主键采用uuid生成
		edmMethodArg.setIsDel((byte) 0); // 默认为未删除
		edmMethodArg.setAddtime(new Date()); // 默认创建时间是当前时间
		edmMethodArg.setModtime(new Date()); // 默认维护时间是当前时间
		return edmMethodArgMapper.insertSelective(edmMethodArg);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EdmMethodArgVO> selectInputArgsByEdmmId(String edmmId) {
		List<EdmMethodArgVO> edmMethodArgVOList = null;
		EdmMethodArgExample example = new EdmMethodArgExample();
		example.createCriteria().andIsDelNotEqualTo((byte)1).andEdmaDataTypeEqualTo("1").andEdmaEdmmIdEqualTo(edmmId);
		example.setOrderByClause("edma_seq ASC");
		List<EdmMethodArg> edmMethodArgs = edmMethodArgMapper.selectByExample(example);
		if(null != edmMethodArgs && edmMethodArgs.size()>0){
			edmMethodArgVOList = new ArrayList<EdmMethodArgVO>();
			for (EdmMethodArg edmMethodArg : edmMethodArgs) {
				EdmMethodArgVO edmMethodArgVO = ModelToVO.edmMethodArgToVO(edmMethodArg);
				// 以下处理拓展字段
				String typeValue = edmCodeMapper.selectParaTypeCodeNameByCodeValue(edmMethodArg.getEdmaDataType());// 通过code表的得到对应的数据类型的名字
				edmMethodArgVO.setEdmaDataTypeName(typeValue);// 程序类型名称
				edmMethodArgVO.setIsEdit(0);
				edmMethodArgVOList.add(edmMethodArgVO);
			}
		}

		return edmMethodArgVOList;
	}

	@Override
	@Transactional(readOnly = true)
	public EdmMethodArgVO selectRetArgsByEdmmId(String edmmId) {
		EdmMethodArgVO edmMethodArgVO =null;
		EdmMethodArgExample example = new EdmMethodArgExample();
		example.createCriteria().andIsDelNotEqualTo((byte)1).andEdmaDataTypeEqualTo("2").andEdmaEdmmIdEqualTo(edmmId);
		//example.setOrderByClause("edma_seq ASC");
		List<EdmMethodArg> edmMethodArgs = edmMethodArgMapper.selectByExample(example);
		if(null != edmMethodArgs && edmMethodArgs.size()>0){
			edmMethodArgVO = ModelToVO.edmMethodArgToVO(edmMethodArgs.get(0));
			edmMethodArgVO.setEdmaDataTypeName(edmCodeMapper.selectParaTypeCodeNameByCodeValue(edmMethodArgs.get(0).getEdmaDataType()));// 程序类型名称
			edmMethodArgVO.setIsEdit(0);
		}
		return edmMethodArgVO;
	}

	@Override
	@Transactional(readOnly = false)
	public int updateByPrimaryKey(EdmMethodArg edmMethodArg) {
		edmMethodArg.setModtime(new Date()); // 修改时间为当前时间
		return edmMethodArgMapper.updateByPrimaryKeySelective(edmMethodArg);
	}

	@Override
	@Transactional(readOnly = false)
	public void move(String[] ids) {
		int seq = 1;
		if(null !=ids && ids.length>0) {
			for(String id:ids) {
				if(!StringUtil.isNullOrEmpty(id)) {
					edmMethodArgMapper.updateSeqById(id,seq);
					seq++;
				}
			}
		}
	}

	@Override
	public String checkUnique(String edmaName, String edmaEdmmId) {
		String errorStr = null;
		if (StringUtil.isNullOrEmpty(edmaName)) {
			errorStr = "参数名不能为空";
			return errorStr;
		}
		if ("".equals(edmaEdmmId)) {
			errorStr = "方法ID不能为空";
			return errorStr;
		}
		EdmMethodArgExample checkExample = new EdmMethodArgExample();
		checkExample.createCriteria().andIsDelNotEqualTo((byte)1).andEdmaNameEqualTo(edmaName).andEdmaEdmmIdEqualTo(edmaEdmmId);
		List<EdmMethodArg> edmMethodArgList = edmMethodArgMapper.selectByExample(checkExample);
		if (edmMethodArgList != null && edmMethodArgList.size() > 0) {
			errorStr = "参数名已存在";
			return errorStr;
		}
		return errorStr;
	}

	@Override
	public String geMethodArgNameById(String id) {
		return edmMethodArgMapper.geMethodArgNameById(id);
	}

	@Override
	public void deleteInputArgs(String[] ids) {
		if(null != ids && ids.length>0){
			for(String id : ids){
				edmMethodArgMapper.deleteByPrimaryKey(id);
			}
		}
	}
}
