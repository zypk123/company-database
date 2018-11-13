package com.huntkey.rx.hr.provider.service.impl;

import com.huntkey.rx.edm.constant.DeptpostsetorderProperty;
import com.huntkey.rx.edm.constant.DeptstuchangeorderProperty;
import com.huntkey.rx.edm.constant.EmployeeProperty;
import com.huntkey.rx.edm.constant.EmployeeentryapplyProperty;
import com.huntkey.rx.edm.constant.EmppostchangeapplyProperty;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.OrderProperty;
import com.huntkey.rx.edm.constant.PostdefinitionorderProperty;
import com.huntkey.rx.edm.entity.Constants;
import com.huntkey.rx.edm.entity.DeptpostsetorderEntity;
import com.huntkey.rx.edm.entity.DeptstuchangeorderEntity;
import com.huntkey.rx.edm.entity.EmppostchangeapplyEntity;
import com.huntkey.rx.edm.entity.PostdefinitionorderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huntkey.rx.hr.common.util.JsonUtils;
import com.huntkey.rx.hr.provider.service.BaseService;
import com.huntkey.rx.hr.provider.service.EmployeeService;
import com.huntkey.rx.hr.provider.service.OrderListService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
/**
 *查询单据列表类
 * @author fangkun
 *
 */
@Service
public class OrderListServiceImpl implements OrderListService {
	
	@Autowired
	EmployeeService employee;//员工服务
	@Autowired
	OrmService ormService;
	@Autowired
	BaseService baseService;
	
	/***
	 * 查询单据列表
	 * @param orderNo 单据号
	 * @param orderType 单据类型
	 * @param applicant 申请人
	 * @param orderStatus 单据状态
	 * @param startPage 当前页
	 * @param rows 每页记录数	 
	 * @return 单据列表数据
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public Pagination orderList(String orderNo, String orderType, String applicant, String orderStatus,
			int startPage,int rows) {
		Pagination<?> page=null;
		// TODO Auto-generated method stub
		try {
			OrmParam ormParam=new OrmParam();
			ormParam.addColumn("*");
			//失档案变更单据还失其他类型单据查询条件
			String recordWhere="";
			//单据编号查询条件
			String orderNbrWhere="";
			//申请人查询条件
			String applicantWhere="";
			//单据状态查询条件
			String orderStatusWhere="";
			//条件集
			List<String> listConditions=new ArrayList<String>();
			//组装查询条件
			if(StringUtil.isEqual(orderType,"recordChange")){
				//如果是档案变更
				recordWhere=ormParam.getEqualXML(EmployeeentryapplyProperty.OEEO_TYPE, "0");
				listConditions.add(recordWhere);
			}
			else if(StringUtil.isEqual(orderType,Constants.EmployeeentryapplyEntity)){
				recordWhere=ormParam.getEqualXML(EmployeeentryapplyProperty.OEEO_TYPE, "1");
				listConditions.add(recordWhere);
			}
			if(!StringUtil.isNullOrEmpty(orderNo)){
				//如果填写了单号
				orderNbrWhere=ormParam.getMatchMiddleXML(OrderProperty.ORDE_NBR,orderNo);
				listConditions.add(orderNbrWhere);
			}
			if(!StringUtil.isNullOrEmpty(applicant)){
				//如果填写了申请人
				applicantWhere=ormParam.getEqualXML(OrderProperty.ORDE_ADDUSER,applicant);
				listConditions.add(applicantWhere);
			}
			if(!StringUtil.isNullOrEmpty(orderStatus)){
				//如果填写了单据状态
				orderStatusWhere=ormParam.getEqualXML(OrderProperty.ORDE_STATUS,orderStatus);
				listConditions.add(orderStatusWhere);
			}
			
			//组装所有符合条件的查询条件
			if(listConditions!=null && listConditions.size()>0){
				ormParam.setWhereExp(OrmParam.and(listConditions));
			}
			//暂时按照制单日期降序排序
			ormParam.addOrderExpElement(SQLSortEnum.DESC, OrderProperty.ORDE_DATE);
			//添加分页参数
			ormParam.setPageNo(startPage);
			ormParam.setPageSize(rows);
			//得出类名
			String tableName=StringUtil.isEqual(orderType,"recordChange")?Constants.EmployeeentryapplyEntity:orderType;
			String docType="";//定义单据类型
			//得到每种单据的单据类型字段
			if(StringUtil.isEqual(tableName, EdmUtil.getEdmClassName(DeptstuchangeorderEntity.class))){
				docType=DeptstuchangeorderProperty.ODSC_TYPE;
			}else if(StringUtil.isEqual(tableName,EdmUtil.getEdmClassName(DeptpostsetorderEntity.class))){
				docType= DeptpostsetorderProperty.ODPS_TYPE;
			}else if(StringUtil.isEqual(tableName,EdmUtil.getEdmClassName(EmppostchangeapplyEntity.class))){
				docType=EmppostchangeapplyProperty.OEPC_TYPE;
			}
			else if(StringUtil.isEqual(tableName,EdmUtil.getEdmClassName(PostdefinitionorderEntity.class))){
				//职位维护单
				docType=PostdefinitionorderProperty.OPDE_EDIT_TYPE;
			}
			Class className=EdmUtil.getEntityClassByEdmName(tableName);
			//分页查询数据
			page= ormService.selectPagedBeanList(className, ormParam);
			if(page!=null){
			//取出分页数据中的数据集
			List list=page.getList();
			List newList=new ArrayList();
			if(list!=null && list.size()>0){
				//查询制单人姓名
				for(int count=0;count<list.size();count++){
					JSONObject order=(JSONObject) JSONObject.toJSON(list.get(count));
					order.put("docType", order.getString(docType));
					order.put("addUserName", "");
					order.put("orderType", orderType);
					String addUser=order.getString(OrderProperty.ORDE_ADDUSER);
					if(!StringUtil.isNullOrEmpty(addUser)){
						Map<String, Object> emp=baseService.queryEmployee(addUser);
						if(emp!=null){
							order.put("addUserName", emp.get(EmployeeProperty.REMP_NAME));
						}
					}
					JsonUtils.underLine2Camel(order);
					newList.add(order);
				}
			}
			//将新的数据源替换进入分页返回的数据源中
			page.setList(newList);
		}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return page;
	}
	
	@Override
	public int deleteOrder(String orderTable, String orderId) {
		// TODO Auto-generated method stub
		int count=0;
		try {
			count=ormService.delete(EdmUtil.getEntityClassByEdmName(orderTable), orderId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return count;
	}

	@Override
	public int deleteHrTempOrderByCre(String creuser) {
		// TODO Auto-generated method stub
		OrmParam ormParam=new OrmParam();
		int countRet=0;
		ormParam.setWhereExp(ormParam.and(ormParam.getEqualXML(OrderProperty.ORDE_ADDUSER, creuser)));
		String[] hrOrder=new String[]{
				Constants.DeptstuchangeorderEntity,Constants.EmpresignapplyEntity,
				Constants.PostdefinitionorderEntity,Constants.DeptchargerapplyorderEntity,
				Constants.DeptpostsetorderEntity,Constants.EmployeeentryapplyEntity,
				Constants.ContractsignapplyEntity,Constants.EmppostchangeapplyEntity,
				Constants.EmppostiveapplyEntity,Constants.EmpresignprocedureEntity
		};
		try {
			for(int count =0;count<hrOrder.length;count++){
				countRet+=ormService.delete(EdmUtil.getEntityClassByEdmName(hrOrder[count]), ormParam);
			} 
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return countRet;
	}
	
	
}
