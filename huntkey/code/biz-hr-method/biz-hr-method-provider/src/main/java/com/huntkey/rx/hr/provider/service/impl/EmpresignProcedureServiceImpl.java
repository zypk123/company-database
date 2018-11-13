package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.DepttreeProperty;
import com.huntkey.rx.edm.constant.EmployeeProperty;
import com.huntkey.rx.edm.constant.EmpresignprocedureProperty;
import com.huntkey.rx.edm.constant.JobpositionProperty;
import com.huntkey.rx.edm.constant.OerpOerpItemSetaProperty;
import com.huntkey.rx.edm.constant.OrderProperty;
import com.huntkey.rx.edm.constant.RempRempContSetaProperty;
import com.huntkey.rx.edm.entity.DepttreeEntity;
import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.edm.entity.EmpresignprocedureEntity;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.edm.entity.OerpOerpItemSetaEntity;
import com.huntkey.rx.edm.entity.RempRempContSetaEntity;
import com.huntkey.rx.hr.common.constants.NumberConstants;
import com.huntkey.rx.hr.common.constants.WFHandlerTypeConstants;
import com.huntkey.rx.hr.common.constants.WorkFlowConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.CurrentSessionEntity;
import com.huntkey.rx.hr.common.model.OrderConstants;
import com.huntkey.rx.hr.common.util.JsonUtils;
import com.huntkey.rx.hr.provider.service.BaseService;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.EmployeeService;
import com.huntkey.rx.hr.provider.service.EmpresignApplyService;
import com.huntkey.rx.hr.provider.service.EmpresignProcedureService;
import com.huntkey.rx.hr.provider.service.OrderListService;
import com.huntkey.rx.sceo.method.register.plugin.entity.ParamsVo;
import com.huntkey.rx.sceo.method.register.plugin.util.ExecUtil;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 离职手续单服务类
 * @author Created by --- on 2017/12/5.
 */
@Service
public class EmpresignProcedureServiceImpl extends BaseService implements EmpresignProcedureService {
	@Autowired
	OrmService ormService;
	@Autowired
	EmployeeService employee;
	@Autowired
	OrderListService orderListService;
	@Autowired
	BizFormService bizFormService;
	@Autowired
	BaseService baseService;
	@Autowired
    private HttpServletRequest request;
	/**
	 * 离职申请单服务类
	 */
	@Autowired
	EmpresignApplyService empresignApplyService;
	private static final Logger logger = LoggerFactory.getLogger(EmpresignProcedureServiceImpl.class);

	/**
	 * 离职手续单列表查询方法
	 * 
	 * @param deptId
	 *            部门对象Id
	 * @param type
	 *            离职时间类型：申请日期
	 *            cretime、预离职日期oera_app_date、批准离职日期oera_appr_date、实际离职日期oera_real_date
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param orderStatus
	 *            手续单单据状态：待审2、待批3、待核4、完成5、退回6
	 * @param staffInfo
	 *            员工信息：姓名/工号
	 * @param pageNum
	 *            开始页
	 * @param pageSize
	 *            每页包含的记录数
	 * @return
	 */
	@Override
	public Result queryDeleteOrderService(String deptId, String type, String startTime, String endTime,
			String orderStatus, String staffInfo, int pageNum, int pageSize) {
		/**
		 * 手续单界面上的所有条件均指向离职手续单据
		 * 离职手续单列表的数据源，原始数据来自审批状态为通过的离职申请单，再根据手续单中的【oerp_oera
		 * 离职申请单】字段关联离职申请单对象Id 界面上第一列：申请单号指的是手续单单号，如果对应的离职申请单没有关联到手续单，则该字段不显示值
		 *
		 */
		Result empresignApplyOrderListResult = new Result();
		empresignApplyOrderListResult.setRetCode(Result.RECODE_SUCCESS);
		try {
			// 调用离职申请服务接口，查询离职申请单，取单据状态为：审批通过 5；离职手续单的数据源必须来自审批通过的离职申请单
			empresignApplyOrderListResult = empresignApplyService.query(deptId, type, startTime, endTime,
					OrderConstants.ORDE_STATUS_5, staffInfo, pageNum, pageSize);

			JSONObject pageObj = (JSONObject) empresignApplyOrderListResult.getData();
			// 如果有分页数据
			if (pageObj != null) {
				// 取出申请单数据
				JSONArray empresignApplyList = pageObj.getJSONArray("list");
				// 定义新的返回数据集
				JSONArray retArr = new JSONArray();
				// 根据申请单数据操作
				if (empresignApplyList != null && empresignApplyList.size() > 0) {
					for (int count = 0; count < empresignApplyList.size(); count++) {
						/** 循环根据申请单的员工+入参的单据状态 查询离职手续单 **/
						JSONObject empresignApplyJson = empresignApplyList.getJSONObject(count);
						// 获得员工ID
						String empId = empresignApplyJson.getString("oeraEmp");
						// 获取员工信息
						// 组装员工和单据状态查询条件
						OrmParam ormParam = new OrmParam();
						String whereXml = ormParam.getEqualXML(EmpresignprocedureProperty.OERP_EMP, empId);
						// 如果填写了单据单据状态
						if (!StringUtil.isNullOrEmpty(orderStatus)) {
							whereXml =ormParam.and(whereXml, ormParam.getEqualXML(OrderProperty.ORDE_STATUS, orderStatus));
						}
						ormParam.setWhereExp(whereXml);
						//按照cretime \ orde_status单据状态降序排序
						ormParam.setOrderExp(SQLSortEnum.DESC, OrderProperty.ORDE_STATUS, EdmSysColumn.CRETIME);
						// 查询离职手续单
						List<EmpresignprocedureEntity> listPro = ormService
								.selectBeanList(EmpresignprocedureEntity.class, ormParam);
						// 一个人只会有一份离职手续单
						empresignApplyJson.put(EdmSysColumn.ID, "");
						empresignApplyJson.put(OrderProperty.ORDE_NBR, "");
						empresignApplyJson.put(OrderProperty.ORDE_STATUS, "");
						if (listPro != null && listPro.size() > 0) {
							EmpresignprocedureEntity emproceOrder = listPro.get(0);
							empresignApplyJson.put(EdmSysColumn.ID, emproceOrder.getId());
							empresignApplyJson.put(OrderProperty.ORDE_NBR, emproceOrder.getOrde_nbr());
							empresignApplyJson.put(OrderProperty.ORDE_STATUS, emproceOrder.getOrde_status());
						}
						JsonUtils.underLine2Camel(empresignApplyJson);
						retArr.add(empresignApplyJson);
					}
				}
				pageObj.put("list", retArr);
			}
			empresignApplyOrderListResult.setData(pageObj);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return empresignApplyOrderListResult;
	}

	@Override
	public JSONObject loadEmpresignProcedureOrder(String orderId) {
		// TODO Auto-generated method stub
		JSONObject orderJson = new JSONObject();
		try {
			// 根据单据ID查询单据
			EmpresignprocedureEntity orderEntity = ormService.load(EmpresignprocedureEntity.class, orderId);
			if (orderEntity != null) {
				// 单据转化为json对象
				orderJson = (JSONObject) JSONObject.toJSON(orderEntity);
				// 获取制单人
				Map<String, Object> mapAdduser = baseService.queryEmployee(orderEntity.getOrde_adduser());
				orderJson.put("orde_adduser_name", mapAdduser.get(EmployeeProperty.REMP_NAME));
				orderJson.put("orde_adduser_code", mapAdduser.get(EmployeeProperty.REMP_NO));
				// 获取制单岗位
				Map<String, Object> mapDuty = baseService.queryPost(orderEntity.getOrde_duty());
				orderJson.put("orde_duty_name", mapDuty.get(JobpositionProperty.RPOS_NAME));
				// 后去制单部门
				Map<String, Object> mapDept = baseService.queryDept(orderEntity.getOrde_dept());
				orderJson.put("orde_dept_name", mapDept.get(DepttreeProperty.MDEP_NAME));
				if (!StringUtil.isNullOrEmpty(orderEntity.getOerp_emp())) {
					// 查询员工 岗位 部门名称
					JSONObject empJson = employee.queryEmployeeById(orderEntity.getOerp_emp(), true, false);
					// 单据添加员工链接对象的名称
					orderJson.put(DepttreeProperty.MDEP_NAME, empJson.getString("rempDeptName"));
					orderJson.put(JobpositionProperty.RPOS_NAME, empJson.getString("rempPostName"));
					orderJson.put(EmployeeProperty.REMP_NAME, empJson.getString(EmployeeProperty.REMP_NAME));
					orderJson.put(EmployeeProperty.REMP_NO, empJson.getString(EmployeeProperty.REMP_NO));
				}
				// 根据单据ID外键查询
				List<OerpOerpItemSetaEntity> listPro = ormService.getAttrubuteSetByPID(orderId,
						OerpOerpItemSetaEntity.class, EmpresignprocedureEntity.class);
				JSONArray retArr = null;
				// 单据添加手续详情
				if (listPro != null && listPro.size() > 0) {
					retArr = new JSONArray();
					for (int count = 0; count < listPro.size(); count++) {
						OerpOerpItemSetaEntity setEntity = listPro.get(count);
						// 手续项目集转化成json对象
						JSONObject oerpJson = (JSONObject) JSONObject.toJSON(setEntity);
						oerpJson.remove(EdmSysColumn.ID);
						String handler = setEntity.getOerp_handler();
						if (!StringUtil.isNullOrEmpty(handler)) {
							// 根据经办人查询 经办人的姓名和部门
							EmployeeEntity emp = ormService.load(EmployeeEntity.class, handler);
							if (!StringUtil.isNullOrEmpty(emp.getRemp_dept())) {
								// 根据员工部门查询部门信息
								DepttreeEntity depttree = ormService.load(DepttreeEntity.class, emp.getRemp_dept());
								oerpJson.put(EmployeeProperty.REMP_NAME, emp.getRemp_name());// 添加姓名
								oerpJson.put(EmployeeProperty.REMP_NO, emp.getRemp_no());// 添加工号
								oerpJson.put(DepttreeProperty.MDEP_NAME, depttree.getMdep_name());// 添加部门
							}
						}
						retArr.add(oerpJson);
					}
					// retArr.sort((o1,o2)->(JSONObject)o1).getInteger(OerpOerpItemSetaProperty.OERP_ITEM)
					// -((JSONObject)o2).getInteger(OerpOerpItemSetaProperty.OERP_ITEM));
					if (retArr != null && retArr.size() > 1) {
						// 将数据排序
						retArr.sort(new Comparator<Object>() {
							@Override
							public int compare(Object obj1, Object obj2) {
								// TODO Auto-generated method stub
								JSONObject o1 = (JSONObject) JSONObject.toJSON(obj1);
								JSONObject o2 = (JSONObject) JSONObject.toJSON(obj2);
								if (!StringUtil.isNullOrEmpty(o1.getString(OerpOerpItemSetaProperty.OERP_ITEM))
										&& !StringUtil
												.isNullOrEmpty(o2.getString(OerpOerpItemSetaProperty.OERP_ITEM))) {
									return o1.getString(OerpOerpItemSetaProperty.OERP_ITEM)
											.compareTo(o2.getString(OerpOerpItemSetaProperty.OERP_ITEM));
								} else {
									return 1;
								}

							}
						});
					}
					orderJson.put(EmpresignprocedureProperty.OERP_ITEM_SET, retArr);
				}
				JsonUtils.underLine2Camel(orderJson);
			}

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return orderJson;
	}

	@Override
	public JSONObject saveEmpresignProcedureOrder(EmpresignprocedureEntity entity) {
		// TODO Auto-generated method stub
		String orderId = "";
		String num = "";
		JSONObject retObj = new JSONObject();
		String orderStauts = "";
		CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
		try {
			orderId = entity.getId();
			orderStauts = entity.getOrde_status();
			orderStauts = StringUtil.isNullOrEmpty(orderStauts) ? OrderConstants.ORDE_STATUS_1 : orderStauts;
			// 变更单据状态
			entity.setOrde_status(orderStauts);
			num = entity.getOrde_nbr();
			// 根据单据ID判断保存
			if (StringUtil.isNullOrEmpty(orderId)) {
				num = getOrderNbr(NumberConstants.PREFIX_EMP_RESIGN_PROCEDURE);
				// 先新增临时单
				entity.setOrde_nbr(num);
				// 添加制单岗位 企业对象
				entity.setEdmd_ente(sessionEntity.getEnterpriseId());
				// creuser
				entity.setCreuser(sessionEntity.getEmployeeId());
				// 新增
				entity.setCretime(new Date());
				orderId = ormService.insert(entity).toString();
				entity.setId(orderId);
			} else {
				entity.setModuser(sessionEntity.getEmployeeId());
				// 修改
				entity.setModtime(new Date());
				ormService.update(entity);
			}
			// 保存属性集
			insertProperties(entity, entity.getOerp_item_set());
			retObj.put("orderId", orderId);
			retObj.put("orderNo", num);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return retObj;
	}
	// 保存属性集
	private void insertProperties(EmpresignprocedureEntity entity,List<OerpOerpItemSetaEntity> list){
		// 保存属性集
		// 1.先删除属性集
		OrmParam ormParam = new OrmParam();
		ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, entity.getId())));
		try {
			ormService.delete(OerpOerpItemSetaEntity.class, ormParam);
			// 2.新增属性集
			List<OerpOerpItemSetaEntity> newList=new ArrayList<OerpOerpItemSetaEntity>();
			EdmUtil.setPropertyBaseEntitiesSysColumns(EmpresignprocedureEntity.class, entity, list,
					SQLCurdEnum.INSERT);
			for(OerpOerpItemSetaEntity proEntity : list){
				proEntity.setId("");
				if(StringUtil.isEqual(proEntity.getOerp_item_state(),"1")
						&& StringUtil.isNullOrEmpty(proEntity.getOerp_time())){
					proEntity.setOerp_time(new Date());
				}
				newList.add(proEntity);
			}
			ormService.insert(newList);
		} catch (Exception e) {
			// TODO: handle exception
			if(logger.isDebugEnabled()){
				e.printStackTrace();
			}
		}
	}
	@Override
	public JSONObject submitEmpresignProcedureOrder(EmpresignprocedureEntity empresign) {
		// TODO Auto-generated method stub
		empresign.setOrde_status(OrderConstants.ORDE_STATUS_2);
		// 提交流程
		JSONObject retObj = saveEmpresignProcedureOrder(empresign);
		String orderDefId = empresign.getOrde_rode_obj();
		bizFormService.submitWorkFlow(orderDefId, retObj.getString("orderId"));
		return retObj;
	}

	private Result passEmpresignProcedureOrder(String orderId) {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			// 加载单据
			EmpresignprocedureEntity entity = ormService.load(EmpresignprocedureEntity.class, orderId);
			String empId=entity.getOerp_emp();
			//根据员工ID查询员工信息
			EmployeeEntity employee=ormService.load(EmployeeEntity.class, empId);
			String peopleId="";
			if(employee!=null){
				peopleId=employee.getRemp_epeo_obj();
			}

			// 临时单据删除
			orderListService.deleteHrTempOrderByCre(entity.getOerp_emp());
			// 员工状态离职 岗位清空 更新合同记录实际结束日
			// 1.员工离职 岗位清空
			employee.setId(empId);// 员工ID
			employee.setRemp_post("");
			employee.setRemp_status("3");
			ormService.updateSelective(employee);
			// 2.更新合同的实际结束日期 查询最后一笔合同 
			OrmParam ormParam = new OrmParam();
			ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, entity.getOerp_emp())));
			ormParam.setOrderExp(SQLSortEnum.DESC, RempRempContSetaProperty.REMP_CONT_BEG);
			List<RempRempContSetaEntity> conSetList = ormService.selectBeanList(RempRempContSetaEntity.class, ormParam);
			if (conSetList != null && conSetList.size() > 0) {
				// 取第一条合同记录 进行更新
				RempRempContSetaEntity con = conSetList.get(0);
				con.setRemp_cont_end(new Date());
				con.setRemp_cont_rend(new Date());
				ormService.update(con);
				
				//3.插入一条解约记录
				con.setId(null);
				con.setRemp_sign_type("3");
				con.setRemp_cont_rend(new Date());
				con.setRemp_cont_sdate(new Date());
				con.setRemp_cont_beg(null);
				con.setRemp_cont_end(null);
				ormService.insert(con);
			}

			// 岗位人空缺
			ormParam = new OrmParam();
			ormParam.setWhereExp(
					OrmParam.and(ormParam.getEqualXML(JobpositionProperty.RPOS_EMP, entity.getOerp_emp())));
			JobpositionEntity jobEntity = new JobpositionEntity();
			jobEntity.setRpos_emp("");
			ormService.updateSelective(jobEntity, ormParam);
			
			//单据状态变更为 5 通过状态
			entity.setOrde_status(OrderConstants.ORDE_STATUS_5);
			ormService.updateSelective(entity);
			
			//调用企业圈的方法
			if(!StringUtil.isNullOrEmpty(peopleId)){
				//获取session
				CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
				JSONObject removeObj=new JSONObject();
				removeObj.put("enterpriseId", sessionEntity.getEnterpriseId());
				removeObj.put("peopleId", peopleId);
				String token = request.getHeader("Authorization");
				ParamsVo params = new ParamsVo();
		        params.setClassName("people");
		        params.setMethodName("removePeopleEnteSet");
		        params.setAuthorization(token);// 可选
		        params.setParamObj(removeObj);
		        Result exec = ExecUtil.exec(params);
		        logger.info(exec.toString());
		        if (null == exec || !Result.RECODE_SUCCESS.equals(exec.getRetCode())) {
		            throw new ApplicationException(0, "离职手续更新自然人企业圈失败" + exec.getErrMsg());
		        }
		        return exec;
			}

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Result pass(String orderInstanceId, String handlerType) {
		Result result=new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		switch (handlerType) {
			case WFHandlerTypeConstants.PASS: {
				// TODO 单据状态改为 完成；将单据数据写入资源表
				passEmpresignProcedureOrder(orderInstanceId);
				break;
			}
			case WFHandlerTypeConstants.REVOKE: {
				// TODO 单据状态改为 待提
				updateOrderStatus(orderInstanceId, "1");
				break;
			}
			case WFHandlerTypeConstants.RETURN_BACK: {
				// TODO 单据状态改为 退回
				updateOrderStatus(orderInstanceId, "6");
				break;
			}
			default: {
				break;
			}
		}
		return result;
	}

	private void updateOrderStatus(String orderId, String status) {
		EmpresignprocedureEntity entity = new EmpresignprocedureEntity();
		entity.setId(orderId);
		entity.setOrde_status(status);
		try {
			ormService.updateSelective(entity);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Result auditPostivieOrder(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		 Result result = new Result();
	        //流程处理
	        String auditKey = jsonObject.getString(WorkFlowConstants.PARAM_AUDITKEY);
	        //单据编辑状态
	        String formState = jsonObject.getString(WorkFlowConstants.PARAM_FORMSTATE);
	        //流程实例ID
	        String actInstanceId = jsonObject.getString(WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
	        //审批说明/步骤职责
	        String opinion = jsonObject.getString(WorkFlowConstants.PARAM_OPINION);

	        JSONObject auditMsg = jsonObject.getJSONObject(WorkFlowConstants.PARAM_ORDER_OBJ);

	        //入参校验
	        if (StringUtil.isNullOrEmpty(actInstanceId)) {
	            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
	            return result;
	        }
	        if (StringUtil.isNullOrEmpty(auditKey)) {
	            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_AUDITKEY);
	            return result;
	        }
	        if (StringUtil.isNullOrEmpty(formState)) {
	            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_FORMSTATE);
	            return result;
	        }

	        if (WorkFlowConstants.FormState.EDITABLE.equals(formState)&&WorkFlowConstants.AuditKey.PASS.equals(auditKey)) {
	            try{

	                String orderId = auditMsg.getString("id");
	                String ordeNbr = auditMsg.getString("ordeNbr");

	                //step2：操作主表
	                //根据原来的数据ID查询原来数据库中的数据,根据id修改原来的数据的上一版单据对象
	                EmpresignprocedureEntity entity = ormService.load(EmpresignprocedureEntity.class,orderId);
	                //生成旧的数据
	                EmpresignprocedureEntity oldEntity=entity;
	                if (null == oldEntity) {
	                    result.setRetCode(Result.RECODE_ERROR);
	                    result.setErrMsg("没有找到单据编号为'" + ordeNbr + "'的单据信息");
	                    return result;
	                }
	                
	                //生成旧的记录
	                oldEntity.setId(null);
	                String oldVersionId=ormService.insert(oldEntity).toString();
	                //更新旧记录修改时间
	                ormService.update(oldEntity);
	                //将旧版本ID存到当前记录的上一版本
	                entity.setId(orderId);
	                entity.setCretime(new Date());
	                entity.setOrde_last_version_obj(oldVersionId);
	                ormService.update(entity);
	                
	                //step3：操作子表
	                JSONArray msgArr=auditMsg.getJSONArray("oerpItemSet");
	                if(msgArr!=null && msgArr.size()>0){
		                Object params=JSON.parse(msgArr.toJSONString());
		                JsonUtils.camel2UnderLine(params);
	                	List<OerpOerpItemSetaEntity> list=JSONArray.parseArray(JSONObject.toJSONString(params), OerpOerpItemSetaEntity.class);
	                	//插入旧版本的属性集纪录
	                	for(OerpOerpItemSetaEntity proEn: list){
	                		proEn.setPid(oldVersionId);
	                		ormService.updateSelective(proEn);
	                	}
	                	//新增属性集新记录
	                	insertProperties(entity, list);
	                }
	                //step1：调用流程
	                bizFormService.audit(actInstanceId, opinion, auditKey);
	            } catch (Exception e) {
	                if(logger.isDebugEnabled()){
	                    e.printStackTrace();
	                }
	                throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
	            }
	        }
	        return result;
	}
}
