package com.huntkey.rx.hr.provider.service;

import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.JobpositionProperty;
import com.huntkey.rx.edm.entity.DeptstuchangeorderEntity;
import com.huntkey.rx.edm.entity.DepttreeEntity;
import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.hr.common.constants.NumberConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.provider.client.InformationClient;
import com.huntkey.rx.hr.provider.dao.DeptTreeDao;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 抽取公用的服务类方法
 * Created by liuwens on 2017/11/28.
 */
@Component
public class BaseService
{
    @Autowired
    OrmService ormService;

    @Autowired
    InformationClient informationClient;

    @Autowired
	DeptTreeDao deptTreeDao;

	public Map<String, Object> queryEmployee(String employeeId) {
		// TODO Auto-generated method stub
		OrmParam ormParam=new OrmParam();
		ormParam.addColumn("id,remp_name,remp_no");
		ormParam.setWhereExp(ormParam.getEqualXML("id", employeeId));
		Map<String, Object> map=null;
		try {
			List<Map<String, Object>> entityList=ormService.selectMapList(EmployeeEntity.class, ormParam);
			if(entityList!=null && entityList.size()>0){
				map=entityList.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public Map<String, Object> queryDept(String deptId) {
		// TODO Auto-generated method stub
		OrmParam ormParam=new OrmParam();
		ormParam.addColumn("id,mdep_code,mdep_name");
		ormParam.setWhereExp(ormParam.getEqualXML("id", deptId));
		Map<String, Object> map=null;
		try {
			List<Map<String, Object>> entityList=ormService.selectMapList(DepttreeEntity.class, ormParam);
			if(entityList!=null && entityList.size()>0){
				map=entityList.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public Map<String, Object> queryPost(String postId) {
		// TODO Auto-generated method stub
		OrmParam ormParam=new OrmParam();
		ormParam.addColumn(EdmSysColumn.ID+","
							+JobpositionProperty.RPOS_CODE+","
							+JobpositionProperty.RPOS_NAME+","
							+JobpositionProperty.RPOS_EMP+","
							+JobpositionProperty.RPOS_PPOST);
		//计算当前时间
		String nowDate=DateUtil.formatDate(new Date());
		String dateBetween=ormParam.and(ormParam.getLessThanAndEqualXML(JobpositionProperty.RPOS_BEG, nowDate),
				ormParam.getGreaterThanXML(JobpositionProperty.RPOS_END, nowDate));
		String idWhere=ormParam.getEqualXML(EdmSysColumn.ID, postId);
		ormParam.setWhereExp(ormParam.and(dateBetween,idWhere));
		Map<String, Object> map=null;
		try {
			List<Map<String, Object>> entityList=ormService.selectMapList(JobpositionEntity.class, ormParam);
			if(entityList!=null && entityList.size()>0){
				map=entityList.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	public String getOrderNbr(String nbrlCode){
		Result codeResult = informationClient.getNumbers(nbrlCode,null);
        if (codeResult.getData() == null || StringUtil.isNullOrEmpty(codeResult.getData().toString())) {
            throw new ApplicationException(Result.RECODE_ERROR, "单据编号生成失败");
        }
        String orderNbr = codeResult.getData().toString();
		if (orderNbr.indexOf(NumberConstants.PREFIX_DEPT_STU_CHANGE_ORDER_ADD) > -1 ||
				orderNbr.indexOf(NumberConstants.PREFIX_DEPT_STU_CHANGE_ORDER_MODIFY) > -1 ||
				orderNbr.indexOf(NumberConstants.PREFIX_DEPT_STU_CHANGE_ORDER_MOVE) > -1 ||
				orderNbr.indexOf(NumberConstants.PREFIX_DEPT_STU_CHANGE_ORDER_CANCEL) > -1){
			DeptstuchangeorderEntity deptstuchangeorderEntity = deptTreeDao.findDeptChangeOrderByNbr(orderNbr);
			if (deptstuchangeorderEntity != null){
				throw new ApplicationException(Result.RECODE_ERROR, "单据编号已存在");
			}
		}
        return orderNbr;
	}

	public String getDeptCode(){
		Result codeResult = informationClient.getNumbers(NumberConstants.PREFIX_DEPT_CODE,null);
		if (codeResult.getData() == null || StringUtil.isNullOrEmpty(codeResult.getData().toString())) {
			throw new ApplicationException(Result.RECODE_ERROR, "部门编码生成失败");
		}
		String deptCode = codeResult.getData().toString();
		DepttreeEntity depttreeEntity = deptTreeDao.findByCode(deptCode);
		if (depttreeEntity != null){
			throw new ApplicationException(Result.RECODE_ERROR, "部门编码已存在");
		}
		return deptCode;
	}

	public String getQuartersCode(){
		Result codeResult = informationClient.getNumbers(NumberConstants.PREFIX_QUARTERS_CODE,null);
		if (codeResult.getData() == null || StringUtil.isNullOrEmpty(codeResult.getData().toString())) {
			throw new ApplicationException(Result.RECODE_ERROR, "岗位编码生成失败");
		}
		return codeResult.getData().toString();
	}

}
