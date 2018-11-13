package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.DeptpostsetorderProperty;
import com.huntkey.rx.edm.constant.DepttreeProperty;
import com.huntkey.rx.edm.constant.EmployeeProperty;
import com.huntkey.rx.edm.constant.JobpositionProperty;
import com.huntkey.rx.edm.constant.MdepMdepChgrSetaProperty;
import com.huntkey.rx.edm.constant.OdpsOdpsDpostSetaProperty;
import com.huntkey.rx.edm.constant.OrderProperty;
import com.huntkey.rx.edm.entity.DeptpostsetorderEntity;
import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.edm.entity.MdepMdepChgrSetaEntity;
import com.huntkey.rx.edm.entity.OdpsOdpsDpostSetaEntity;
import com.huntkey.rx.edm.entity.PositiondefinitionEntity;
import com.huntkey.rx.edm.entity.RempRempPostSetaEntity;
import com.huntkey.rx.hr.common.constants.DeptPostDeleteConstant;
import com.huntkey.rx.hr.common.constants.NumberConstants;
import com.huntkey.rx.hr.common.constants.WFHandlerTypeConstants;
import com.huntkey.rx.hr.common.model.CurrentSessionEntity;
import com.huntkey.rx.hr.common.model.DeptTreeDTO;
import com.huntkey.rx.hr.common.model.EmployeeConstants;
import com.huntkey.rx.hr.common.model.OrderConstants;
import com.huntkey.rx.hr.provider.dao.DeptTreeDao;
import com.huntkey.rx.hr.provider.service.BaseService;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.DeptTreeService;
import com.huntkey.rx.hr.provider.service.EmployeeService;
import com.huntkey.rx.hr.provider.service.PostDeleteService;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * 部门岗位注销接口实现类
 * @author fangkun
 */
@Service
public class PostDeleteServiceImpl extends BaseService implements PostDeleteService  {
	
	@Autowired
	DeptTreeService deptTree;
	@Autowired
	DeptTreeDao deptTreeDao;
	@Autowired
	OrmService ormService;
	@Autowired
	EmployeeService employee;
	@Autowired
    BizFormService bizFormService;
	@Autowired
	BaseService baseService;
	private static final Logger logger = LoggerFactory.getLogger(PostDeleteServiceImpl.class);
	
	/***
	 * 根据单据ID加载部门岗位注销单
	 */
	@Override
	public JSONObject loadDpDeleteOrder(String orderId) {
		// TODO Auto-generated method stub
		JSONArray retArr=null;
		JSONObject deptPostJson=new JSONObject();
		try {
			DeptpostsetorderEntity deptPost=ormService.load(DeptpostsetorderEntity.class, orderId);
			deptPostJson=(JSONObject)JSONObject.toJSON(deptPost);
			//获取制单人
			Map<String, Object> mapAdduser=baseService.queryEmployee(deptPost.getOrde_adduser());
			if(mapAdduser!=null && !mapAdduser.isEmpty()){
				deptPostJson.put("orde_adduser_name", mapAdduser.get(EmployeeProperty.REMP_NAME));
				deptPostJson.put("orde_adduser_code", mapAdduser.get(EmployeeProperty.REMP_NO));
			}
			//获取制单岗位
			Map<String, Object> mapDuty=baseService.queryPost(deptPost.getOrde_duty());
			if(mapDuty!=null && !mapDuty.isEmpty()){
				deptPostJson.put("orde_duty_name", mapDuty.get(JobpositionProperty.RPOS_NAME));
			}
			//后去制单部门
			Map<String, Object> mapDept=baseService.queryDept(deptPost.getOrde_dept());
			if(mapDept!=null && !mapDept.isEmpty()){
				deptPostJson.put("orde_dept_name", mapDept.get(DepttreeProperty.MDEP_NAME));
			}
			//获得初始部门字段
			deptPostJson.put("dept_beg_name", "");
			String deptBeg=deptPost.getOdps_dept_beg();
			if(!StringUtil.isNullOrEmpty(deptBeg)){
				//查询出初始部门的名称
				DeptTreeDTO depttree= deptTree.getDept(deptBeg, new Date());
				if(depttree!=null){
					deptPostJson.put("dept_beg_name", depttree.getMdepName());
				}
			}
			//查询属性集表
			List<OdpsOdpsDpostSetaEntity> listProperties= ormService.getAttrubuteSetByPID(orderId, 
					OdpsOdpsDpostSetaEntity.class, DeptpostsetorderEntity.class);

			if(listProperties!=null && listProperties.size()>0){
				retArr=new JSONArray();
				for(OdpsOdpsDpostSetaEntity entity :listProperties){
					JSONObject json=(JSONObject) JSONObject.toJSON(entity);
					String postNo=entity.getOdps_post();
					json.put("rpos_pos_name",postNo
							+"/"+entity.getOdps_name());
					//汇报岗位ID
					String ppostId=entity.getOdps_ppost();
					//职位ID
					String rpofId=entity.getOdps_rpof();
					//部门ID
					String deptId=entity.getOdps_dept();
					//查询岗位编码查询员工
					String empId="";
					List<JobpositionEntity> listPost=queryPostByColumn(JobpositionProperty.RPOS_CODE,postNo);
					if(listPost!=null && listPost.size()>0){
						JobpositionEntity postInfo=listPost.get(0);
						empId=postInfo.getRpos_emp();
					}
					
					transField(json, empId, ppostId, rpofId,deptId);
					retArr.add(json);
				}
				deptPostJson.put(DeptpostsetorderProperty.ODPS_DPOST_SET, retArr);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				e.printStackTrace();
			}
		}
		return deptPostJson;
	}
	
	/**
	 * 根据岗位ID查询需要注销的部门岗位
	 */
	@Override
	public JSONArray loadDpDelete(String[] postIds) {
		// TODO Auto-generated method stub
		JSONArray retArr=new JSONArray();
		JobpositionEntity entity=null;
		for(int i=0;i<postIds.length;i++){
			List<JobpositionEntity> postList=queryPostByColumn(EdmSysColumn.ID,postIds[i]);
			if(postList!=null && postList.size()>0){
				entity=postList.get(0);
				JSONObject obj=(JSONObject) JSONObject.toJSON(entity);
				obj.put("rpos_pos_name",entity.getRpos_code()+"/"+entity.getRpos_name());
				transField(obj,entity.getRpos_emp(),
						entity.getRpos_ppost(),entity.getRpos_rpof(),entity.getRpos_dept());
				retArr.add(obj);
			}
		}
		if(retArr!=null && retArr.size()>1){
			retArr.sort(new Comparator<Object>() {
				@Override
				public int compare(Object o1, Object o2) {
					// TODO Auto-generated method stub
					JSONObject json1=(JSONObject) JSONObject.toJSON(o1);
					JSONObject json2=(JSONObject) JSONObject.toJSON(o2);
					return json1.getString(JobpositionProperty.RPOS_GRADE)
							.compareTo(json2.getString(JobpositionProperty.RPOS_GRADE));
				}
				
			});
		}
		return retArr;
	}
	
	private JSONObject transField(JSONObject obj,String empId,
			String ppostId,String profId,String deptId){
		try{
			obj.put(JobpositionProperty.RPOS_EMP+"_name", "");
			if(!StringUtil.isNullOrEmpty(empId)){
				//查询在编职员
				EmployeeEntity employeeObj=ormService.load(EmployeeEntity.class, empId);
				if(employeeObj!=null){
					obj.put(JobpositionProperty.RPOS_EMP+"_name", employeeObj.getRemp_no()+"/"
							+employeeObj.getRemp_name());
				}
			}
			obj.put("rpos_sup_name", "");
			if(!StringUtil.isNullOrEmpty(ppostId)){
				//查询岗位
				List<JobpositionEntity> postArr=queryPostByColumn(EdmSysColumn.ID,ppostId);
				if(postArr!=null && postArr.size()>0){
					JobpositionEntity postJson=postArr.get(0);
					obj.put("rpos_sup_name", postJson.getRpos_name());
				}
			}
			obj.put("rpof_type", "");	
			if(!StringUtil.isNullOrEmpty(profId)){
				//查询职位
				PositiondefinitionEntity profObj=ormService.load(PositiondefinitionEntity.class, profId);
				if(profObj!=null){
					obj.put("rpof_type", profObj.getRpof_type());				
				}
			}
			obj.put(JobpositionProperty.RPOS_DEPT+"_name", "");	
			if(!StringUtil.isNullOrEmpty(deptId)){
				//查询岗位所属部门
				Map<String, Object> deptObj=baseService.queryDept(deptId);
				if(deptObj!=null && !deptObj.isEmpty()){
					obj.put(JobpositionProperty.RPOS_DEPT+"_name", 
							deptObj.get(DepttreeProperty.MDEP_NAME));				
				}
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				e.printStackTrace();
			}
		}
		return obj;
	}
	@Override
	public JSONObject saveDpDeleteOrder(DeptpostsetorderEntity saveParams) {
		// TODO Auto-generated method stub
		String retId=saveParams.getId();
		String num="";
		String status=saveParams.getOrde_status();
		JSONObject retObj=null;
		CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
		num=saveParams.getOrde_nbr();
		saveParams.setOrde_status(StringUtil.isNullOrEmpty(status)?"1":status);
		try {
			if(StringUtil.isNullOrEmpty(retId)){
				num=getOrderNbr(NumberConstants.PREFIX_DEPT_POST_SET_ORDER_CANCEL);
				//添加制单岗位  企业对象
				saveParams.setEdmd_ente(sessionEntity.getEnterpriseId());
				//creuser
				saveParams.setCreuser(sessionEntity.getEmployeeId());
				//先新增临时单
				saveParams.setOrde_nbr(num);
				//添加创建时间
				saveParams.setCretime(new Date());
				//生成临时单
				retId=ormService.insert(saveParams).toString();
				saveParams.setId(retId);
			}else{
				//moduser
				saveParams.setModuser(sessionEntity.getEmployeeId());
				//添加修改时间
				saveParams.setModtime(new Date());
				//修改临时单
				ormService.update(saveParams);
			}
			//先删除后批量新增岗位集
			OrmParam ormParam=new OrmParam();
			ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID,retId)));
			ormService.delete(OdpsOdpsDpostSetaEntity.class, ormParam);
			//批量新增岗位集
			EdmUtil.setPropertyBaseEntitiesSysColumns(DeptpostsetorderEntity.class,saveParams,
					saveParams.getOdps_dpost_set(), SQLCurdEnum.INSERT);
			ormService.insert(saveParams.getOdps_dpost_set());
			retObj=new JSONObject();
			retObj.put(EdmSysColumn.ID, retId);
			retObj.put(OrderProperty.ORDE_NBR, num);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		return retObj;
	}
	/***
	 * 校验当前岗位是否是部门的责任人或者下级岗位是所属部门的负责人岗位
	 * @param deptPostDelete 临时单内容
	 * @param checkSub 是否需要校验下级
	 * 
	 * validateCode 1当前岗位是否主责 2当前岗位是否存在部门岗位变更待审单 
	 * 				3下级岗位是否是主责 4下级岗位是否存在部门岗位变更待审单
	 * 				5被删除岗位是否存在 6被删除岗位的汇报上级是否存在
	 */
	@Override
	public Map<String, String> curPostContainOrder(DeptpostsetorderEntity deptPostDelete,
			Boolean checkSub){
		//获得注销岗位信息
		Map<String, String> retMap=new HashMap<String,String>();
		List<OdpsOdpsDpostSetaEntity> list=deptPostDelete.getOdps_dpost_set();
		for(OdpsOdpsDpostSetaEntity entity :list){
			String postNo=entity.getOdps_post();
			//根据岗位编码查询岗位ID
			JobpositionEntity postJson=queryPostByNo(entity.getOdps_post());
			//校验岗位是否删除 
			retMap=subPostDelOrPpostDel(postJson,postNo);
			if(!retMap.isEmpty()){
				//如果是空map则返回
				return retMap;
			}
			String postId=postJson.getId();
			String deptId=entity.getOdps_dept();
			retMap=new HashMap<String, String>();
			//判断当前岗位是否是部门主责岗位
			//validateCode 1表示当前岗位是部门主责岗位  2 表示当前岗位存在待审单据 
			//3表示当前岗位的下级岗位是部门主责岗位  4 表示当前岗位的下级岗位存在待审单据
			Map<String, String> mapMajor=postEmpIsMajor(deptId,postId,"",DateUtil.formatDate(new Date()));
			if(mapMajor!=null && !mapMajor.isEmpty()){
				retMap.put("validateCode", "1");
				retMap.putAll(mapMajor);
				return retMap;
			}
			//判断当前岗位是否存在新增/修改/注销待审的单据
			Map<String, String> curOrderMap=getPostStatus(entity.getOdps_post(), new String[]{
					DeptPostDeleteConstant.ODPS_TYPE_ADD,
			DeptPostDeleteConstant.ODPS_TYPE_UPDATE,
			DeptPostDeleteConstant.ODPS_TYPE_DELETE});
			if(curOrderMap!=null && !curOrderMap.isEmpty()){
				retMap.put("validateCode", "2");
				retMap.putAll(curOrderMap);
				return retMap;
			}
			if(checkSub){
				if(StringUtil.isEqual(entity.getOdps_sub(),"1")){
					//校验岗位是否删除或者汇报上级不存在
					Map<String, String> mapSubDelete= checkSubPost(postNo, 3);
					if(mapSubDelete!=null && !mapSubDelete.isEmpty()){
						//下级岗位是否是所属部门的责任岗位
						retMap.putAll(mapSubDelete);
						return retMap;
					}
					//是否需要校验下级
					Map<String, String> mapSubMajor= checkSubPost(postNo,1);
					if(mapSubMajor!=null && !mapSubMajor.isEmpty()){
						//下级岗位是否是所属部门的责任岗位
						retMap.put("validateCode", "3");
						retMap.putAll(retMap);
						return retMap;
					}
					//校验下级是否存在待审单据
					Map<String, String> subOrderMap=checkSubPost(postNo,2);
					if(subOrderMap!=null && !subOrderMap.isEmpty()){
						//下级岗位是否是所属部门的责任岗位
						retMap.put("validateCode", "4");
						retMap.putAll(subOrderMap);
						return retMap;
					}
				}
			}
		}
		return retMap;
	}
	
	private Map<String, String> subPostDelOrPpostDel(JobpositionEntity postJson,String postNo) {
		// TODO Auto-generated method stub
		Map<String, String> retMap=new HashMap<String, String>();
		if(postJson==null){
			//被删除岗位不存在
			retMap.put("validateCode", "5");
			retMap.put("rposCode",postNo);
			return retMap;
		}
//		//校验被删除岗位的汇报岗位是否存在
//		if(!StringUtil.isNullOrEmpty(postJson.getRpos_ppost())){
//			Map<String, Object> mapPpost=queryPost(postJson.getRpos_ppost());
//			if(mapPpost==null || mapPpost.isEmpty()){
//				//如果汇报岗位为空
//				retMap.put("validateCode", "6");
//				retMap.put("rposName",postJson.getRpos_name());
//				return retMap;
//			}
//		}
		return retMap;
	}

	/***
	 * 查询下级岗位是否是所属部门的负责人岗位
	 */
	@Override
	public Map<String, String> checkSubPost(String postNo,int checkType){
		Map<String, String> retMap=null;
		JobpositionEntity postJson=queryPostByNo(postNo);
		String postId=(postJson==null?"":postJson.getId());
		if(StringUtil.isNullOrEmpty(postId)){
			return null;
		}
		//查询下级岗位
		List<JobpositionEntity> subArr=getSubPost(postId);
		//判断下级是否有是主责岗位的
		if(subArr!=null && subArr.size()>0){
			retMap=new HashMap<String,String>();
			for(int i=0;i<subArr.size();i++){
				JobpositionEntity post=subArr.get(i);
				if(post!=null){
					if(checkType==1){
						//校验主责
						retMap=postEmpIsMajor(post.getRpos_dept(),post.getId(), 
								"", DateUtil.formatDate(new Date()));
					}else if(checkType==2){
						//校验单据
						retMap=getPostStatus(post.getRpos_code(),new String[]{"0","1","3"});
					}else{
						//校验岗位或者汇报岗位是否被删除
						retMap=subPostDelOrPpostDel(post, postNo);
					}
					if(retMap!=null && !retMap.isEmpty()){
						return retMap;
					}
				}
			}
		}
		return retMap;
	}
	/***
	 * 查询下级岗位是否存在待审单
	 * @param postId
	 * @return
	 */
	@Override
	//根据岗位编号查询是否存在 0 新增 1修改 3注销单据
	public Map<String, String> subPostContainOrder(String postNo){
		Map<String, String> mapRet=null;
		JobpositionEntity postJson=queryPostByNo(postNo);
		String postId=(postJson==null?"":postJson.getId());
		if(StringUtil.isNullOrEmpty(postId)){
			return null;
		}
		//查询下级岗位
		List<JobpositionEntity> subArr=getSubPost(postId);
		//判断下级是否存在单据
		if(subArr!=null && subArr.size()>0){
			for(int i=0;i<subArr.size();i++){
				JobpositionEntity post=subArr.get(i);
				Map<String, String> map=getPostStatus(post.getRpos_code(),new String[]{"0","1","3"});
				if(map!=null && !map.isEmpty()){
					mapRet=map;
					return mapRet;
				}
			}
		}
		return mapRet;
	}
	/**
	 * 在岗位设置单中查找待审且为status集合中单据类型的单据
	 * @param postNo 岗位编码
	 * @param status 岗位设置单单据状态 0 新增 1修改 3注销
	 */
	@Override
	public Map<String, String> getPostStatus(String postNo,String[] status){
		Map<String, String> map=null;
		//根据编号查询PID
		OrmParam ormParam=new OrmParam();
		try {
			//岗位编码查询条件
			ormParam.setWhereExp(ormParam.getEqualXML(OdpsOdpsDpostSetaProperty.ODPS_POST, postNo));
			List<OdpsOdpsDpostSetaEntity> arr=ormService.selectBeanList(OdpsOdpsDpostSetaEntity.class, ormParam);
			if(arr!=null && arr.size()>0){
				for(OdpsOdpsDpostSetaEntity proEntity:arr){
				//因为岗位同时只能存在多个单子
				String pid=proEntity.getPid();
				//根据PID查询单据
				ormParam=new OrmParam();
				String pidWhere=ormParam.getEqualXML(EdmSysColumn.ID, pid);
				//加上单据类型的查询条件
				String orderTypeWhere=ormParam.getInXML(DeptpostsetorderProperty.ODPS_TYPE, status);
				//加上单据审核状态的查询条件
				String[] orderStutus=new String[]{OrderConstants.ORDE_STATUS_2,OrderConstants.ORDE_STATUS_3
						,OrderConstants.ORDE_STATUS_4};
				String orderStatusWhere=ormParam.getInXML(OrderProperty.ORDE_STATUS, orderStutus);
				//组装查询条件
				ormParam.setWhereExp(OrmParam.and(pidWhere,orderTypeWhere,orderStatusWhere));
				List<DeptpostsetorderEntity> listOrder=ormService.selectBeanList(DeptpostsetorderEntity.class, ormParam);
				if(listOrder!=null && listOrder.size()>0){
					map=new HashMap<String,String>();
					//如果有单子，只会存在一张单子
					DeptpostsetorderEntity entity=listOrder.get(0);
					//返回存在单据的单据号
					map.put("orderNbr", entity.getOrde_nbr());
					//返回存在单据状态
					map.put("orderStatus", entity.getOrde_status());
					//返回存在单据的类型
					map.put("orderType", entity.getOdps_type());
					
					//存在就立即返回
					return map;
				}
			}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				e.printStackTrace();
			}
		}
		return map;
	}
	//在部门的责任人属性集中查询==》岗位是否是部门负责岗位
	@Override
	public Map<String, String> postEmpIsMajor(String deptId,String postId,String empId,String date){
		//根据部门ID 和 岗位ID 查询是否是部门的负责岗
		Map<String, String> retMap=null;
		try {
				OrmParam ormParam=new OrmParam();
				//查询条件组合
				List<String> combConditions=new ArrayList<String>();
				//当前日期在生、失效范围内
				String dateBetweenWhere=OrmParam.and(ormParam.getLessThanAndEqualXML(MdepMdepChgrSetaProperty.MDEP_CHGR_BEG,
						date),ormParam.getGreaterThanXML(MdepMdepChgrSetaProperty.MDEP_CHGR_END,date));
				
				combConditions.add(dateBetweenWhere);
				//PID条件
				String pidWhere=ormParam.getEqualXML(EdmSysColumn.PID, deptId);
				
				combConditions.add(pidWhere);
				
				//岗位ID查询条件
				if(!StringUtil.isNullOrEmpty(postId)){
					String postWhere= ormParam.getEqualXML(MdepMdepChgrSetaProperty.MDEP_CHGR_POST, postId);
					
					combConditions.add(postWhere);
				}
				//员工ID查询条件
				if(!StringUtil.isNullOrEmpty(empId)){
					String empWhere= ormParam.getEqualXML(MdepMdepChgrSetaProperty.MDEP_CHGR_EMP, empId);
					
					combConditions.add(empWhere);
				}
				
				//组装查询条件
				ormParam.setWhereExp(OrmParam.and(combConditions));
				List<MdepMdepChgrSetaEntity> list= ormService.selectBeanList(MdepMdepChgrSetaEntity.class, ormParam);
				if(list!=null && list.size()>0){
					retMap=new HashMap<String,String>();
					if(!StringUtil.isNullOrEmpty(postId)){
						//查询岗位名称
						Map<String, Object> mapPost=baseService.queryPost(postId);
						if(mapPost!=null && !mapPost.isEmpty()){
							retMap.put("rposName", (String)mapPost.get(JobpositionProperty.RPOS_NAME));
						}
					}
					if(!StringUtil.isNullOrEmpty(empId)){
						//查询岗位名称
						Map<String, Object> mapEmp=baseService.queryEmployee(empId);
						if(mapEmp!=null && !mapEmp.isEmpty()){
							retMap.put("rempName", (String)mapEmp.get(EmployeeProperty.REMP_NAME));
						}
					}
				}
				
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				e.printStackTrace();
			}
		}
		return retMap;
	}
	/***
	 * 递归查询下级岗位
	 * @param postId
	 */
	private List<JobpositionEntity> getSubPost(String postId){
		OrmParam ormParam=new OrmParam();
		//根据汇报岗位查询
		ormParam.setWhereExp(ormParam.getEqualXML(JobpositionProperty.RPOS_PPOST, postId));
		List<JobpositionEntity> newArr=new ArrayList<JobpositionEntity>();
		try {
			//查询下级岗位
			List<JobpositionEntity> arr=ormService.selectBeanList(JobpositionEntity.class, ormParam);
			if(arr!=null && arr.size()>0){
				for(int i=0;i<arr.size();i++){
					JobpositionEntity obj=arr.get(i);
					newArr.add(obj);
					List<JobpositionEntity> subArr=getSubPost(obj.getId());
					newArr.addAll(subArr);
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				e.printStackTrace();
			}
		}
		return newArr;
	}
	/***
	 * 岗位注销单据的提交
	 */
	@Override
	public JSONObject submitDpDeleteOrder(DeptpostsetorderEntity deptPostDeleteOrder) {
		// TODO Auto-generated method stub
		deptPostDeleteOrder.setOrde_status("2");
		//先保存后 提交
		JSONObject retObj=saveDpDeleteOrder(deptPostDeleteOrder);
		//提交流程
        String orderDefId = deptPostDeleteOrder.getOrde_rode_obj();
		bizFormService.submitWorkFlow(orderDefId, retObj.getString("id"));
		return retObj;
	}

	private Result passDpDeleteOrder(String orderId) {
		// TODO Auto-generated method stub
		Result result=new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try{
			//1.部门岗位失效  失效日期为当天 
			JSONObject deteleOrder=loadDpDeleteOrder(orderId);
			DeptpostsetorderEntity entity=JSONObject.parseObject(JSONObject.toJSONString(deteleOrder), DeptpostsetorderEntity.class);
			List<OdpsOdpsDpostSetaEntity> entityList=entity.getOdps_dpost_set();
			if(entityList!=null && entityList.size()>0){
				for(int i=0;i<entityList.size();i++){
					OdpsOdpsDpostSetaEntity entityOrder=entityList.get(i);
					//根据岗位编码查询岗位
					List<JobpositionEntity> posts=
							queryPostByColumn(JobpositionProperty.RPOS_CODE, entityOrder.getOdps_post());
					JobpositionEntity curPost=((posts==null || posts.size()==0)?null:posts.get(0));
					//根据岗位编码注销岗位
					deletePost(curPost.getId(),curPost.getRpos_emp());
					//如果没有勾选下级  则下级的汇报为当前岗位的汇报
					if(!StringUtil.isEqual(entityOrder.getOdps_sub(),"1")){
						//根据当前岗位编码查询下级岗位
						List<JobpositionEntity> listPostSub=queryPostByColumn(JobpositionProperty.RPOS_PPOST, 
								curPost.getId());
						if(listPostSub!=null && listPostSub.size()>0)
						{
							for(int subIndex=0;subIndex<listPostSub.size();subIndex++){
								JobpositionEntity postSub=listPostSub.get(subIndex);
								postSub.setRpos_ppost(entityOrder.getOdps_ppost());
								ormService.updateSelective(postSub);
							}
						}
					}else{
						//如果勾选了下级 则注销下级岗位
						//查询下级岗位
						if(curPost!=null){
							List<JobpositionEntity> subPost=getSubPost(curPost.getId());
							if(subPost!=null && subPost.size()>0){
								for(int j=0;j<subPost.size();j++){
									deletePost(subPost.get(j).getId(),subPost.get(j).getRpos_emp());
								}
							}
						}
					}
				}
				//单据状态变更为 5 通过状态
				DeptpostsetorderEntity deptPost=new DeptpostsetorderEntity();
				deptPost.setId(orderId);
				deptPost.setOrde_status(OrderConstants.ORDE_STATUS_5);
				ormService.updateSelective(deptPost);
			}
		}
		catch (Exception e) {
			if(logger.isDebugEnabled()){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/***
	 * 根据岗位ID注销岗位
	 * @param postNo
	 */
	private void deletePost(String postId,String empId){
		try {
			//根据岗位ID查询岗位
			JobpositionEntity postEntity=ormService.load(JobpositionEntity.class, postId);
			//1.根据岗位ID更新岗位的失效日期当天
			postEntity.setRpos_end(new Date());
			ormService.update(postEntity);
			//查询员工类
			if(!StringUtil.isNullOrEmpty(empId)){
				EmployeeEntity emp=ormService.load(EmployeeEntity.class, empId);
				//当注销的是员工的任职岗位  将员工状态 和岗位置空
				if(emp!=null && StringUtil.isEqual(EmployeeConstants.DUTY_TYPE_IN,postEntity.getRpos_duty_type())){
					emp.setRemp_status(null);//员工待岗
					emp.setRemp_post(null);//岗位空
					emp.setRemp_pgrade(null);//岗级空
					ormService.update(emp);
					//根据根据员工id和岗位id将岗位的失效时间变更
					RempRempPostSetaEntity postSet=new RempRempPostSetaEntity();
					postSet.setRemp_post_end(new Date());
					postSet.setPid(emp.getId());
					postSet.setRemp_post_his(postId);
					ormService.updateSelective(postSet);
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * 根据岗位ID查询岗位信息
	 * @param postId
	 * @return
	 */
	private List<JobpositionEntity> queryPostByColumn(String field,String value) {
		OrmParam ormParam=new OrmParam();
		ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(field,
				value)));
		List<JobpositionEntity> list=null;
		try {
			list = ormService.selectBeanList(JobpositionEntity.class, ormParam);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				e.printStackTrace();
			}
		}
		return list;
	}
	/***
	 * 根据岗位编号查询有效岗位信息 
	 * @param postId
	 * @return
	 */
	@SuppressWarnings("static-access")
	private JobpositionEntity queryPostByNo(String postNo) {
		// 组装查询员工类对象
		OrmParam ormParam=new OrmParam();
		//根据岗位编码查询岗位
		String postNoWhere=ormParam.getEqualXML(JobpositionProperty.RPOS_CODE, postNo);
		//根据时间区间查询
		String nowDate=DateUtil.formatDate(new Date());
		String dateBetween=ormParam.and(ormParam.getLessThanAndEqualXML(
				JobpositionProperty.RPOS_BEG, nowDate),ormParam.getGreaterThanXML(
						JobpositionProperty.RPOS_END, nowDate));
		//组装查询条件
		ormParam.setWhereExp(ormParam.and(postNoWhere,dateBetween));
		JobpositionEntity json = null;
		try {
			// 获得岗位对象
			List<JobpositionEntity> postArr = ormService.selectBeanList(JobpositionEntity.class, ormParam);
			
			if (postArr != null && postArr.size() > 0) {
				// 因为根据ID获取 只会返回一条记录 取出第一条
				json = postArr.get(0);
			} 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return json;
	}
	
	 @Override
    public Result pass(String orderInstanceId, String handlerType) {
        logger.info("部门岗位通过回调接口：orderInstanceId：" + orderInstanceId + ",handlerType:" + handlerType);
        Result result=new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
        switch (handlerType) {
            case WFHandlerTypeConstants.PASS: {
                //TODO 单据状态改为 完成；将单据数据写入资源表
            	result=passDpDeleteOrder(orderInstanceId);
                break;
            }
            case WFHandlerTypeConstants.REVOKE: {
                //TODO 单据状态改为 待提
            	updateOrderStatus(orderInstanceId,"1");
                break;
            }
            case WFHandlerTypeConstants.RETURN_BACK: {
                //TODO 单据状态改为 退回
            	updateOrderStatus(orderInstanceId,"6");
                break;
            }
            default: {
                break;
            }
        }
        return result;
	}
	 private void updateOrderStatus(String orderId,String status){
		 DeptpostsetorderEntity entity=new DeptpostsetorderEntity();
		 entity.setId(orderId);
		 entity.setOrde_status(status);
		 try {
			ormService.updateSelective(entity);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				e.printStackTrace();
			}
		}
	 }
}
