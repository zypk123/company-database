package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.EmployeeProperty;
import com.huntkey.rx.edm.constant.EmployeeentryapplyProperty;
import com.huntkey.rx.edm.constant.JobpositionProperty;
import com.huntkey.rx.edm.constant.MdepMdepChagSetaProperty;
import com.huntkey.rx.edm.constant.MdepMdepChgrSetaProperty;
import com.huntkey.rx.edm.constant.OdcsOdcsChrgSetaProperty;
import com.huntkey.rx.edm.constant.OepcOepcChangSetaProperty;
import com.huntkey.rx.edm.constant.OrderProperty;
import com.huntkey.rx.edm.constant.RempRempPostSetaProperty;
import com.huntkey.rx.edm.constant.RempRempStudySetaProperty;
import com.huntkey.rx.edm.constant.SchoolProperty;
import com.huntkey.rx.edm.entity.DeptchargerapplyorderEntity;
import com.huntkey.rx.edm.entity.DepttreeEntity;
import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.edm.entity.EmployeeentryapplyEntity;
import com.huntkey.rx.edm.entity.EmppostchangeapplyEntity;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.edm.entity.MdepMdepChagSetaEntity;
import com.huntkey.rx.edm.entity.MdepMdepChgrSetaEntity;
import com.huntkey.rx.edm.entity.OdcsOdcsChrgSetaEntity;
import com.huntkey.rx.edm.entity.OepcOepcChangSetaEntity;
import com.huntkey.rx.edm.entity.ParkEntity;
import com.huntkey.rx.edm.entity.RelationEntity;
import com.huntkey.rx.edm.entity.RempRempFamSetaEntity;
import com.huntkey.rx.edm.entity.RempRempPostSetaEntity;
import com.huntkey.rx.edm.entity.RempRempSkillSetaEntity;
import com.huntkey.rx.edm.entity.RempRempStudySetaEntity;
import com.huntkey.rx.edm.entity.RempRempWorkSetaEntity;
import com.huntkey.rx.edm.entity.SchoolEntity;
import com.huntkey.rx.hr.common.constants.DeptPostDeleteConstant;
import com.huntkey.rx.hr.common.constants.EmployeeEntryApplyConstant;
import com.huntkey.rx.hr.common.model.CurrentSessionEntity;
import com.huntkey.rx.hr.common.model.EmployeeConstants;
import com.huntkey.rx.hr.common.model.OrderConstants;
import com.huntkey.rx.hr.common.util.DateDiff;
import com.huntkey.rx.hr.common.util.JsonUtils;
import com.huntkey.rx.hr.provider.service.BaseService;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.DeptTreeService;
import com.huntkey.rx.hr.provider.service.EmployeeService;
import com.huntkey.rx.hr.provider.service.PostDeleteService;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * 员工类接口实现类
 * 
 * @author fangkun
 */
@Service
public class EmployeeServiceImpl extends BaseService implements EmployeeService {
	@Autowired
	DeptTreeService deptService;
	@Autowired
	PostDeleteService postDelete;
	@Autowired
	OrmService ormService;
	@Autowired
	BizFormService bizFormService;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	/***
	 * 根据部门ID 人员信息模糊查询员工
	 */
	public Result queryEmployee(String deptId, String searchName, int removeLeav, int startPage, int rows) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		OrmParam ormParam = new OrmParam();
		ormParam.addColumn(EdmSysColumn.ID + "," + EmployeeProperty.REMP_NAME + "," + EmployeeProperty.REMP_NO + ","
				+ EmployeeProperty.REMP_DEPT + "," + EmployeeProperty.REMP_POST);
		// 员工姓名或者员工工号模糊查询
		String empNoOrNameQuery = ormParam.or(ormParam.getMatchMiddleXML(EmployeeProperty.REMP_NAME, searchName),
				ormParam.getMatchMiddleXML(EmployeeProperty.REMP_NO, searchName));
		// 部门查询条件
		String deptWhere = "";
		if (!StringUtil.isNullOrEmpty(deptId)) {
			// 如果部门ID不为空 添加部门查询条件
			deptWhere = ormParam.getEqualXML(EmployeeProperty.REMP_DEPT, deptId);
		}
		if (!StringUtil.isNullOrEmpty(removeLeav) && removeLeav == 1) {
			// 不需要查询离职人员
			deptWhere = ormParam.getUnequalXML(EmployeeProperty.REMP_STATUS, "3");
		}
		ormParam.setWhereExp(ormParam.and(empNoOrNameQuery, deptWhere));
		// 添加分页查询条件
		ormParam.setPageNo(startPage);
		ormParam.setPageSize(rows);
		try {
			// 查询
			Pagination<EmployeeEntity> page = ormService.selectPagedBeanList(EmployeeEntity.class, ormParam);
			result.setData(page);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据员工ID 或者员工工号 查询员工的任岗经历
	 */
	@Override
	public JSONArray getEmployeePositions(String employeeId, String employeeNo) {
		// TODO Auto-generated method stub
		JSONArray newPostSetArr = new JSONArray();
		// 按照工号查询出员工id==》再根据员工ID查询任岗经历属性集
		if (StringUtil.isNullOrEmpty(employeeId) && !StringUtil.isNullOrEmpty(employeeNo)) {
			OrmParam ormParam = new OrmParam();
			ormParam.addColumn(EdmSysColumn.ID);
			ormParam.setWhereExp(ormParam.getEqualXML(EmployeeProperty.REMP_NO, employeeNo));
			try {
				List<EmployeeEntity> entityList = ormService.selectBeanList(EmployeeEntity.class, ormParam);
				if (entityList != null && entityList.size() > 0) {
					EmployeeEntity entity = entityList.get(0);
					employeeId = entity.getId();
				}
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					e.printStackTrace();
				}
			}
		}
		// 2.查询任岗经历表
		List<RempRempPostSetaEntity> postSetList = queryPostSet(employeeId);
		// 如果存在任岗经历
		if (postSetList != null && postSetList.size() > 0) {
			for (RempRempPostSetaEntity entity : postSetList) {
				// 获得任岗经历汇报上级ID
				String supEmpId = entity.getRemp_pemp_his();

				// 获得任岗经历岗位ID
				String postId = entity.getRemp_post_his();

				// 任岗经历的生效日期
				String postBegDate = (entity.getRemp_post_beg() == null) ? DateUtil.formatDate(new Date())
						: DateUtil.formatDate(entity.getRemp_post_beg());
				// 任岗经历中的失效日期
				String postEndDate = (entity.getRemp_post_end() == null) ? DateUtil.formatDate(new Date())
						: DateUtil.formatDate(entity.getRemp_post_end());

				// 得出任岗经历中任岗时间
				Map<String, String> map = calPostDate(entity);

				// 将记录转化成json格式 为拼接字段做准备
				JSONObject jsonPostSet = (JSONObject) JSONObject.toJSON(entity);
				try {
					// 计算每段任岗经历的在岗天数和在岗月数
					jsonPostSet.putAll(map);

					// 根据汇报上级ID获得上级员工信息
					if (!StringUtil.isNullOrEmpty(supEmpId)) {

						EmployeeEntity empEntity = ormService.load(EmployeeEntity.class, supEmpId);

						jsonPostSet.put(EmployeeConstants.REMP_PEMP_NAME,
								empEntity != null ? empEntity.getRemp_name() : "");
					}

					// 根据任岗尽力岗位ID获取岗位信息
					if (!StringUtil.isNullOrEmpty(postId)) {

						// 查询岗位
						JobpositionEntity postEntity = ormService.load(JobpositionEntity.class, postId);

						// 拼接岗位名称
						jsonPostSet.put(EmployeeConstants.REMP_POST_NAME, postEntity.getRpos_name());

						// 根据任岗经历信息获取部门 园区信息
						// 1.得到岗位所在部门ID
						String deptId = postEntity.getRpos_dept();

						// 如果岗位部门ID和任岗经历的生效时间不为空
						if (!StringUtil.isNullOrEmpty(deptId)) {

							// 查询部门变更集中->任岗经历生效日期在 部门变更集的生失效时间内
							MdepMdepChagSetaEntity deptChangentity = queryDeptSetHis(deptId, postBegDate, postEndDate);

							// 任岗经历合并部门数据
							if (deptChangentity != null) {
								jsonPostSet.put(EmployeeConstants.REMP_DEPT_NAME, deptChangentity.getMdep_name_his());
							}

							// 判断是否是部门负责人
							jsonPostSet.putAll(deptMajorOrAssit(deptId, postId, postBegDate, postEndDate));

						}
					}
				} catch (Exception e) {
					if (logger.isDebugEnabled()) {
						e.printStackTrace();
					}
				}
				JsonUtils.underLine2Camel(jsonPostSet);
				newPostSetArr.add(jsonPostSet);
			}
			;
		}
		return newPostSetArr;
	}

	/**
	 * 判断是否是部门主管人 或者协管人
	 * 
	 * @param deptId
	 *            部门ID
	 * @param postId
	 *            岗位ID
	 * @param dateStr
	 *            日期
	 * @return
	 */
	private Map<String, String> deptMajorOrAssit(String deptId, String postId, String dateBegStr, String dateEndStr) {
		// 根据日期查询员工在指定日期内是否是部门的负责人
		List<MdepMdepChgrSetaEntity> listDeptChgr = queryChgrSet(deptId, dateBegStr, dateEndStr);
		Map<String, String> map = new HashMap<String, String>();
		map.put(EmployeeConstants.ISMAJOR, "0");
		if (listDeptChgr != null && listDeptChgr.size() > 0) {
			// 判断岗位是否是负责人岗位
			listDeptChgr.forEach((chgr) -> {
				if (StringUtil.isEqual(postId, chgr.getMdep_chgr_post())
						&& StringUtil.isEqual("1", chgr.getMdep_chgr_type())) {
					map.put(EmployeeConstants.ISMAJOR, "1");
				} else if (StringUtil.isEqual(postId, chgr.getMdep_chgr_post())
						&& StringUtil.isEqual("2", chgr.getMdep_chgr_type())) {
					map.put(EmployeeConstants.ISMAJOR, "2");
				}
			});
		}
		return map;
	}

	/**
	 * 根据ID查询员工类
	 * 
	 * @param employeeId
	 *            员工类ID
	 * @param needTrans
	 *            员工对象链接字段是否需要转换成名称
	 * @param containSet
	 *            是否包含属性集： 0 不包含 1包含
	 * @return
	 */
	@Override
	public JSONObject queryEmployeeById(String employeeId, Boolean needTrans, Boolean containSet) {
		// 根据员工ID查询员工方法 不排序 不分页
		EmployeeEntity employee = null;
		try {
			employee = ormService.load(EmployeeEntity.class, employeeId);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		JSONObject empInfo = new JSONObject();
		if (employee == null) {
			logger.info("未找到员工信息");
			return null;
		}
		if (needTrans) {
			empInfo = empField(employee);
		}
		if (empInfo == null) {
			// 如果为null
			empInfo = (JSONObject) JSONObject.toJSON(employee);
		}
		// 是否包含属性集
		if (containSet) {
			// 查询员工任岗经历
			JSONArray postExp = getEmployeePositions(employeeId, "");
			if (postExp != null && postExp.size() > 0) {
				empInfo.put(EmployeeProperty.REMP_POST_SET, postExp);
			}
			// 查询教育背景
			List<Map<String, Object>> empStudy = queryStudySet(employeeId);
			if (empStudy != null && empStudy.size() > 0) {
				empInfo.put(EmployeeProperty.REMP_STUDY_SET, empStudy);
			}
			// 查询专业技能
			List<RempRempSkillSetaEntity> empSkill = querySkillSet(employeeId);
			if (empSkill != null && empSkill.size() > 0) {
				empInfo.put(EmployeeProperty.REMP_SKILL_SET, empSkill);
			}
			// 查询家庭成员
			List<RempRempFamSetaEntity> empFamily = queryFamSet(employeeId);
			if (empFamily != null && empFamily.size() > 0) {
				empInfo.put(EmployeeProperty.REMP_FAM_SET, empFamily);
			}
			// 查询工作经历
			List<RempRempWorkSetaEntity> empWork = queryWorkSet(employeeId);
			if (empWork != null && empWork.size() > 0) {
				empInfo.put(EmployeeProperty.REMP_WORK_SET, empWork);
			}
		}
		return empInfo;
	}

	/**
	 * 员工类中 对象链接字段转换成对应需要的名称
	 * 
	 * @param employee
	 *            员工类对象
	 * @return
	 */
	private JSONObject transEmployeeField(EmployeeEntity employee) {
		// TODO Auto-generated method stub
		// 入职日期
		Date inDateTime = employee.getRemp_in_date();
		long inDate = (inDateTime == null ? 0 : inDateTime.getTime());
		// 首次工作时间
		Date workDateTime = employee.getRemp_work_date();
		long workDate = (workDateTime == null ? 0 : workDateTime.getTime());
		// 离职日期
		Date leaDateTime = employee.getRemp_lea_date();
		// 推荐人
		String refEmp = employee.getRemp_ref_emp();
		// 员工部门
		String empDept = employee.getRemp_dept();
		// 员工岗位
		String empPost = employee.getRemp_post();
		// 公司法人
		String empMcop = employee.getRemp_mcop();
		// 员工生日
		Date empBirthTime = employee.getRemp_birth();
		long empBirth = (empBirthTime == null ? 0 : empBirthTime.getTime());

		// 类转成json对象
		JSONObject empJson = (JSONObject) JSONObject.toJSON(employee);
		empJson.put(EmployeeConstants.WORK_YEARS, "");
		if (workDate != 0) {
			// 计算工龄 根据首次工作时间与离职时间做比较
			empJson.put(EmployeeConstants.WORK_YEARS, DateDiff.yearDateDiff(workDate, leaDateTime));
		}
		// 计算司龄（年）保留一位小数 根据入职时间和离职时间作比较
		empJson.put(EmployeeConstants.COMPANY_YEARS, "");
		// 计算职龄 (月)
		empJson.put(EmployeeConstants.POSITION_MONTHS, "");
		if (inDate != 0) {
			// 计算司龄（年）保留一位小数 根据入职时间和离职时间作比较
			empJson.put(EmployeeConstants.COMPANY_YEARS, DateDiff.yearDateDiff(inDate, leaDateTime));
			// 计算职龄 (月)
			empJson.put(EmployeeConstants.POSITION_MONTHS, DateDiff.monthsDateDiff(inDate, leaDateTime));
		}
		empJson.put(EmployeeConstants.AGE, "");
		if (empBirth != 0) {
			// 计算员工年龄
			empJson.put(EmployeeConstants.AGE, new Double(DateDiff.yearDateDiff(empBirth, null)).intValue());
		}
		try {
			empJson.put(EmployeeConstants.REMP_REF_NAME, "");
			// 查询推荐人
			if (!StringUtil.isNullOrEmpty(refEmp)) {
				EmployeeEntity employeeRef = ormService.load(EmployeeEntity.class, refEmp);
				if (employeeRef != null) {
					empJson.put(EmployeeConstants.REMP_REF_NAME, employeeRef.getRemp_name());
				}
			}
			// 查询部门
			empJson.put(EmployeeConstants.REMP_DEPT_NAME, "");
			empJson.put(EmployeeConstants.RPAK_NAME, "");
			if (!StringUtil.isNullOrEmpty(empDept)) {
				DepttreeEntity depttree = ormService.load(DepttreeEntity.class, empDept);
				if (depttree != null) {
					empJson.put(EmployeeConstants.REMP_DEPT_NAME, depttree.getMdep_name());
					// 为员工列表字段统一设置的字段
					empJson.put("mdepName", depttree.getMdep_name());
					// 得到园区ID
					String parkId = depttree.getMdep_rpak();
					if (!StringUtil.isNullOrEmpty(parkId)) {
						// 根据部门ID 办公园区对象查询园区名称
						ParkEntity park = ormService.load(ParkEntity.class, parkId);
						if (park != null) {
							empJson.put(EmployeeConstants.RPAK_NAME, park.getRpak_name());
						}
					}
				}
			}
			// 查询岗位
			empJson.put(EmployeeConstants.REMP_POST_NAME, "");
			if (!StringUtil.isNullOrEmpty(empPost)) {
				JobpositionEntity post = ormService.load(JobpositionEntity.class, empPost);
				if (post != null) {
					empJson.put(EmployeeConstants.REMP_POST_NAME, post.getRpos_name());
				}
			}
			// 查询公司法人
			empJson.put(EmployeeConstants.REMP_MCOP_NAME, "");
			if (!StringUtil.isNullOrEmpty(empMcop)) {
				RelationEntity relation = ormService.load(RelationEntity.class, empMcop);
				if (relation != null) {
					empJson.put(EmployeeConstants.REMP_MCOP_NAME, relation.getRela_short_name());
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return empJson;
	}

	@SuppressWarnings({})
	@Override
	/***
	 * 删除员工对象
	 * 
	 * @param employeeId员工ID
	 * @author fangkun
	 */
	public Result deleteEmployee(String employeeId) {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		// 先删除主表员工表
		try {
			ormService.delete(EmployeeEntity.class, employeeId);
			deleteSet(employeeId);
			//单独删除任岗经历
			OrmParam ormParam=new OrmParam();
			ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, employeeId));
			ormService.delete(RempRempPostSetaEntity.class, ormParam);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 根据pid批量删除员工属性集表
	private void deleteSet(String employeeId) {
		// 定义需要删除的表集合
		Class[] tableArr = new Class[] {RempRempStudySetaEntity.class,
				RempRempFamSetaEntity.class, RempRempSkillSetaEntity.class, RempRempWorkSetaEntity.class };
		OrmParam ormParam = new OrmParam();
		ormParam.setWhereExp(ormParam.and(ormParam.getEqualXML(EdmSysColumn.PID, employeeId)));
		try {
			// 循环删除属性集表
			for (int i = 0; i < tableArr.length; i++) {
				// 查询每张表
				ormService.delete(tableArr[i], ormParam);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
	}

	/***
	 * 保存员工对象
	 * 
	 * @param employee员工对象
	 */
	@Override
	public Result saveEmployee(EmployeeEntity employee) {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		String empId = employee.getId();
		CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
		try {
			if (StringUtil.isNullOrEmpty(empId)) {
				// 新增主表
				employee.setCretime(new Date());
				employee.setCreuser(sessionEntity.getEmployeeId());
				empId = ormService.insert(employee).toString();
				employee.setId(empId);
			} else {
				// 修改主表
				employee.setModtime(new Date());
				employee.setModuser(sessionEntity.getEmployeeId());
				ormService.update(employee);
				// 根据PID 批量删除属性集表
				deleteSet(empId);
			}
			List<RempRempPostSetaEntity> listPostSet = employee.getRemp_post_set();
			if (listPostSet != null && listPostSet.size() > 0) {
				List<RempRempPostSetaEntity> listPostSetNew = new ArrayList<RempRempPostSetaEntity>();
				for (RempRempPostSetaEntity postSet : listPostSet) {
					if (StringUtil.isNullOrEmpty(postSet.getRemp_pemp_his())) {
						// 如果没有传汇报上级 则根据岗位ID查询汇报上级员工
						postSet.setRemp_pemp_his(getSupEmp(postSet.getRemp_post_his()));
						listPostSetNew.add(postSet);
					}
				}
				//先删除任岗经历i属性集
				OrmParam ormParam=new OrmParam();
				ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, employee.getId()));
				ormService.delete(RempRempPostSetaEntity.class, ormParam);
				// 批量任岗经历新增属性集
				EdmUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employee, listPostSetNew,
						SQLCurdEnum.INSERT);
				ormService.insert(listPostSetNew);
			}

			if (employee.getRemp_skill_set() != null) {
				// 批量工作技能
				EdmUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employee, employee.getRemp_skill_set(),
						SQLCurdEnum.INSERT);
				ormService.insert(employee.getRemp_skill_set());
			}

			if (employee.getRemp_study_set() != null) {
				// 批量教育经历新增属性集
				EdmUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employee, employee.getRemp_study_set(),
						SQLCurdEnum.INSERT);
				ormService.insert(employee.getRemp_study_set());
			}

			if (employee.getRemp_fam_set() != null) {
				// 批量家庭成员新增属性集
				EdmUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employee, employee.getRemp_fam_set(),
						SQLCurdEnum.INSERT);
				ormService.insert(employee.getRemp_fam_set());
			}

			if (employee.getRemp_work_set() != null) {
				// 批量工作经历新增属性集
				EdmUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employee, employee.getRemp_work_set(),
						SQLCurdEnum.INSERT);
				ormService.insert(employee.getRemp_work_set());
			}

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		result.setData(empId);
		return result;
	}

	// 根据岗位ID 查询当时的汇报上级员工
	private String getSupEmp(String postId) {
		String supEmp = "";
		try {
			// 根据岗位ID在岗位变更集中查找岗位信息
			Map<String, Object> post = queryPost(postId);
			if (post != null) {
				String rpos_ppost = String.valueOf(post.get(JobpositionProperty.RPOS_PPOST));
				if (!StringUtil.isNullOrEmpty(rpos_ppost)) {
					Map<String, Object> postSup = queryPost(rpos_ppost);
					if (postSup != null) {
						supEmp = postSup.get(JobpositionProperty.RPOS_EMP).toString();
					}
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return supEmp;
	}

	/***
	 * 加载部门待岗员工
	 */
	@Override
	public JSONObject loadEmpEmpty(String deptId, int subDept, int rows, int startPage) {
		// TODO Auto-generated method stub
		// 1.分页查询员工 按照员工 岗级 工号排序
		Pagination<EmployeeEntity> empPage = queryEmployeeByDeptId(deptId, subDept, rows, startPage);
		JSONObject pageObj = (JSONObject) JSONObject.toJSON(empPage);
		JSONArray retArr = new JSONArray();
		if (pageObj != null) {
			// 取出分页信息中的结果
			List<EmployeeEntity> listEmp = empPage.getList();
			if (listEmp != null && listEmp.size() > 0) {
				for (int num = 0; num < listEmp.size(); num++) {
					JSONObject empObj = new JSONObject();
					EmployeeEntity empEntity = listEmp.get(num);

					// 转化员工字段信息
					JSONObject empJson = empField(empEntity);
					empJson.put(EmployeeConstants.DEPT_ID, empEntity.getRemp_dept());
					empJson.put(EmployeeConstants.RPOS_EMP, empEntity.getId());
					empObj.putAll(empJson);
					// 判断员工是否有调岗
					empObj.putAll(empContainOrder(empEntity));
					// 判断员工是否有档案变更待审单
					empObj.putAll(containRecordChange(empEntity.getRemp_no(), "0"));
					// 下划线转成驼峰
					JsonUtils.underLine2Camel(empObj);
					retArr.add(empObj);
				}
				pageObj.put("list", retArr);
			}
		}
		return pageObj;
	}

	// 查询岗位有人的情况
	@Override
	public JSONObject queryEmpPost(String deptId, int subDept, int rows, int startPage) {
		// 查询有岗有人的情况
		return queryPostEmptyOrNot(deptId, subDept, 1, rows, startPage);
	}

	@Override
	public JSONObject loadEmptyPost(String deptId, int subDept, int rows, int startPage) {
		return queryPostEmptyOrNot(deptId, subDept, 0, rows, startPage);
	}

	/**
	 * 分页查询岗位信息(空岗 或者 非空岗)
	 * 
	 * @param deptId
	 *            部门ID
	 * @param subDept
	 *            是否查询下级部门
	 * @param isEmptyPost
	 *            查询空岗(0)还是非空岗(1)
	 * @param rows
	 * @param startPage
	 * @return
	 */
	private JSONObject queryPostEmptyOrNot(String deptId, int subDept, int isEmptyPost, int rows, int startPage) {
		// TODO Auto-generated method stub
		List<String> deptIds = new ArrayList<String>();
		if (subDept == 1) {
			// 查询部门以及子部门
			deptIds = getSubDept(deptId);
		} else {
			deptIds.add(deptId);
		}
		// 根据部门id分页查询岗位
		Pagination<JobpositionEntity> postPage = queryEmptyPostByPage(deptIds, isEmptyPost, rows, startPage);

		return postPageConvert(postPage, isEmptyPost);
	}

	/**
	 * 
	 * @param postPage
	 *            查询岗位分页数据
	 * @param isEmptyPost
	 *            是否需要查询员工 1需要
	 * @return
	 */
	private JSONObject postPageConvert(Pagination<JobpositionEntity> postPage, int isEmptyPost) {
		JSONObject pageJson = (JSONObject) JSONObject.toJSON(postPage);
		try {
			if (postPage != null) {
				// 取出岗位数据
				List<JobpositionEntity> dataset = postPage.getList();
				if (dataset != null && dataset.size() > 0) {
					JSONArray newArr = new JSONArray();
					for (int count = 0; count < dataset.size(); count++) {
						// 逐条 转化岗位字段
						JobpositionEntity post = dataset.get(count);
						JSONObject postJson = new JSONObject();
						postJson.putAll(convertPostField(post));
						postJson.putAll(postContainOrder(post.getRpos_code(), post.getId()));
						if (isEmptyPost == 1) {
							// 查询员工
							EmployeeEntity empEntity = ormService.load(EmployeeEntity.class, post.getRpos_emp());
							if (empEntity != null) {
								// 转化员工字段信息
								JSONObject empJson = empField(empEntity);
								empJson.remove(EdmSysColumn.ID);
								empJson.remove("mdepName");
								postJson.putAll(empJson);
								// 判断员工是否有调岗
								postJson.putAll(empContainOrder(empEntity));
								// 判断员工是否有档案变更待审单据
								postJson.putAll(containRecordChange(empEntity.getRemp_no(), "0"));
							}
						}
						JsonUtils.underLine2Camel(postJson);
						newArr.add(postJson);
					}
					pageJson.put("list", newArr);
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return pageJson;
	}

	/***
	 * 根据部门分页查询岗位对象(无人)
	 * 
	 * @param deptIds
	 *            部门ID 如果是一个就equal查询 多个就是In操作
	 * @param isEmptyPost
	 *            0 空岗 1岗位有人
	 * @param rows
	 *            每页记录数
	 * @param startPage
	 *            起始页数
	 */
	private Pagination<JobpositionEntity> queryEmptyPostByPage(List<String> deptIds, int isEmptyPost, int rows,
			int startPage) {
		OrmParam ormParam = getEmptyPostCon(deptIds, isEmptyPost);
		ormParam.setPageNo(startPage);
		ormParam.setPageSize(rows);
		Pagination<JobpositionEntity> page = null;
		try {
			page = ormService.selectPagedBeanList(JobpositionEntity.class, ormParam);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return page;
	}

	// 组装岗位查询条件
	@SuppressWarnings("static-access")
	private OrmParam getEmptyPostCon(List<String> deptIds, int isEmptyPost) {
		OrmParam ormParam = new OrmParam();
		ormParam.addColumn(EdmSysColumn.ID + "," + JobpositionProperty.RPOS_CODE + "," + JobpositionProperty.RPOS_NAME
				+ "," + JobpositionProperty.RPOS_DEPT + "," + JobpositionProperty.RPOS_DUTY_TYPE + ","
				+ JobpositionProperty.RPOS_EMP + "," + JobpositionProperty.RPOS_DUTY + ","
				+ JobpositionProperty.RPOS_GRADE + "," + JobpositionProperty.RPOS_END + ","
				+ JobpositionProperty.RPOS_BEG + "," + JobpositionProperty.RPOS_PPOST + ","
				+ JobpositionProperty.RPOS_QUAL);
		// 根据当天日期过滤掉注销岗位
		String nowDate = DateUtil.formatDate(new Date());
		String dateBetweenWhere = ormParam.and(ormParam.getLessThanAndEqualXML(JobpositionProperty.RPOS_BEG, nowDate),
				ormParam.getGreaterThanXML(JobpositionProperty.RPOS_END, nowDate));

		String deptWhere = "";
		// 按照部门查询
		if (deptIds.size() > 1) {
			// in查询
			deptWhere = ormParam.getInXML(JobpositionProperty.RPOS_DEPT, deptIds.toArray());
		} else {
			deptWhere = ormParam.getEqualXML(JobpositionProperty.RPOS_DEPT, deptIds.get(0));
		}
		String isEmptyPostWhere = "";
		if (isEmptyPost == 0) {
			// 查询空岗
			isEmptyPostWhere = ormParam.or(ormParam.getIsNull(JobpositionProperty.RPOS_EMP),
					ormParam.getEqualXML(JobpositionProperty.RPOS_EMP, ""));
		} else {
			// 不查询空岗
			isEmptyPostWhere = ormParam.and(ormParam.getIsNotNull(JobpositionProperty.RPOS_EMP),
					ormParam.getUnequalXML(JobpositionProperty.RPOS_EMP, ""));
		}

		// 组合查询参数
		ormParam.setWhereExp(ormParam.and(isEmptyPostWhere, dateBetweenWhere, deptWhere));
		// 按照岗级升序排序
		ormParam.setOrderExp(SQLSortEnum.ASC, JobpositionProperty.RPOS_GRADE);
		return ormParam;
	}

	/***
	 * 查询部门空岗
	 */
	@Override
	public List<Map<String, Object>> deptEmptyPost(String deptId, int containSub) {
		OrmParam ormParam = new OrmParam();
		List<String> listDepts = new ArrayList<String>();
		if (containSub == 1) {
			listDepts = getSubDept(deptId);
		} else {
			listDepts.add(deptId);
		}
		ormParam = getEmptyPostCon(listDepts, 0);

		List<Map<String, Object>> listEmptyPost = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
		// 查询
		try {
			listEmptyPost = ormService.selectMapList(JobpositionEntity.class, ormParam);
			if (listEmptyPost != null && listEmptyPost.size() > 0) {
				for (Map<String, Object> map : listEmptyPost) {
					map.putAll(postContainOrder(String.valueOf(map.get(JobpositionProperty.RPOS_CODE)),
							String.valueOf(map.get(EdmSysColumn.ID))));
					retList.add(map);
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return retList;
	}

	// 员工岗位字段转化
	private JSONObject postField(String empPostId) {
		JobpositionEntity postEntity = null;
		JSONObject postJson = null;
		try {
			postEntity = ormService.load(JobpositionEntity.class, empPostId);
			// 获取岗位转换后数据
			postJson = convertPostField(postEntity);
			if (postJson != null) {
				postJson.remove(EdmSysColumn.ID);// 移除岗位ID
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return postJson;
	}

	@Override
	// 根据员工查询部门岗位需要的字段
	public JSONObject queryByDeptEmp(String empId) {
		JSONObject deptEmp = new JSONObject();
		try {
			EmployeeEntity emp = ormService.load(EmployeeEntity.class, empId);
			if (emp != null) {
				deptEmp = new JSONObject();
				deptEmp.putAll(empField(emp));
				if (!StringUtil.isNullOrEmpty(emp.getRemp_post())) {
					deptEmp.putAll(postField(emp.getRemp_post()));
				}
				JsonUtils.underLine2Camel(deptEmp);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}

		return deptEmp;
	}

	// 根据员工 查询处其他 岗位列表需要的字段
	private JSONObject empField(EmployeeEntity empEntity) {
		// 取出员工ID
		String empId = empEntity.getId();
		// 转换员工中的字段
		JSONObject empObj = transEmployeeField(empEntity);
		// 返回员工 工号+姓名 类型
		empObj.put("emp",
				empObj.getString(EmployeeConstants.REMP_NAME) + "/" + empObj.getString(EmployeeConstants.REMP_NO));
		// 查询任岗经历
		List<RempRempPostSetaEntity> postSet = queryPostSet(empId);
		// 取出任岗经历第一条
		empObj.put("postMonths", "");
		if (postSet != null && postSet.size() > 0) {
			RempRempPostSetaEntity postSetEntity = postSet.get(0);
			// 获取任岗经历中的任岗时间
			Map<String, String> map = calPostDate(postSetEntity);
			empObj.put("postMonths", map.get("postMonths"));
		}
		// 查询教育经历
		List<Map<String, Object>> studySet = queryStudySet(empId);
		// 取出教育与经历第一条
		empObj.put(RempRempStudySetaProperty.REMP_DEGREE, "");
		empObj.put(RempRempStudySetaProperty.REMP_RSCH, "");
		empObj.put(RempRempStudySetaProperty.REMP_RSCH + "_name", "");
		empObj.put(RempRempStudySetaProperty.REMP_MAJOR, "");
		if (studySet != null && studySet.size() > 0) {
			Map<String, Object> studyEntity = studySet.get(0);
			empObj.putAll(studyEntity);
		}
		return empObj;
	}

	private SchoolEntity getSchoolInfo(String schoolId) {
		SchoolEntity entity = new SchoolEntity();
		// 获取学校名称
		OrmParam ormParam = new OrmParam();
		ormParam.addColumn(SchoolProperty.RSCH_NAME + "," + SchoolProperty.RSCH_CODE);
		ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.ID, schoolId));
		try {
			List<SchoolEntity> listSchool = ormService.selectBeanList(SchoolEntity.class, ormParam);
			if (listSchool != null && listSchool.size() > 0) {
				entity = listSchool.get(0);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return entity;
	}

	// 计算任岗经历中在岗时间
	private Map<String, String> calPostDate(RempRempPostSetaEntity entity) {
		Map<String, String> map = new HashMap<String, String>();

		// 任岗经历生效时间
		Date begDateTime = entity.getRemp_post_beg();
		long begDate = (begDateTime == null ? 0 : begDateTime.getTime());

		// 任岗经历失效时间
		Date endDateTime = entity.getRemp_post_end();
		// 如果任岗经历的时间是9999无固定时间 则取当天时间
		if (!StringUtil.isNullOrEmpty(endDateTime)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(endDateTime);
			if (cal.get(Calendar.YEAR) == 9999) {
				endDateTime = new Date();
			}
		}
		//任岗经理任岗天数
		double b=DateDiff.daysDateDiff(begDate, endDateTime);
		map.put(EmployeeConstants.POST_DAYS,String.valueOf(new java.math.BigDecimal(b)
				.setScale(0, java.math.BigDecimal.ROUND_HALF_UP)));
		map.put(EmployeeConstants.POST_MONTHS,
				String.valueOf(new Double(DateDiff.monthsDateDiff(begDate, endDateTime))));
		return map;
	}

	/** 岗位是否存在待审单 */
	private Map<String, String> postContainOrder(String postNo, String postId) {
		// TODO Auto-generated method stub
		String[] status = new String[] { DeptPostDeleteConstant.ODPS_TYPE_UPDATE,
				DeptPostDeleteConstant.ODPS_TYPE_DELETE };
		// 判断是否存在修改或注销的待审单
		Map<String, String> map = new HashMap<String, String>();
		map.put("hasPostChange", "0");
		map.put("hasEmpPostChange", "0");
		if (postDelete.getPostStatus(postNo, status) != null) {
			map.put("hasPostChange", "1");
		}
		// 判断在’员工岗位调整单‘中岗位没有被其他人占用
		if (getPostEmpStatus(postId)) {
			map.put("hasEmpPostChange", "1");
		}
		// 判断岗位在‘入职单’中是否被员工占用
		map.putAll(containRecordChange(postId, "1"));
		// 判断岗位在‘部门责任人任免’中是否被员工占用
		map.putAll(containDeptChargeOrder((postId)));
		return map;
	}

	private Boolean getPostEmpStatus(String postId) {
		// TODO Auto-generated method stub
		Boolean b = false;
		// 查询员工岗位调整单 表
		OrmParam ormParam = new OrmParam();
		ormParam.addColumn(NodeConstant.PID);
		// 岗位ID查询条件
		ormParam.setWhereExp(ormParam.getEqualXML(OepcOepcChangSetaProperty.OEPC_POST, postId));
		try {
			// 查询员工岗位调整的属性集
			List<OepcOepcChangSetaEntity> retSet = ormService.selectBeanList(OepcOepcChangSetaEntity.class, ormParam);
			if (retSet != null && retSet.size() > 0) {
				for (OepcOepcChangSetaEntity postSetObj : retSet) {
					ormParam = new OrmParam();
					// 根据pid查询员工岗位变更主表
					String idWhere = ormParam.getEqualXML(NodeConstant.ID, postSetObj.getPid());
					// 单据状态为待审
					String[] orderStutus = new String[] { OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3,
							OrderConstants.ORDE_STATUS_4 };
					String statusWhere = ormParam.getInXML(OrderProperty.ORDE_STATUS, orderStutus);
					// 组装查询条件
					ormParam.setWhereExp(ormParam.and(idWhere, statusWhere));
					List<EmppostchangeapplyEntity> retArr = ormService.selectBeanList(EmppostchangeapplyEntity.class,
							ormParam);
					if (retArr != null && retArr.size() > 0) {
						b = true;
						return b;
					}
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return b;
	}

	/*** 判断员工存在‘排岗’和‘调岗’以及‘离岗’待审单据 **/
	@SuppressWarnings("static-access")
	private Map<String, String> empContainOrder(EmployeeEntity empEntity) {
		// 转化成json对象可以拼接字段
		Map<String, String> map = new HashMap<String, String>();
		map.put("hasEmpPostChange", "0");
		// 根据员工ID 查询员工岗位调整单据调整清单
		OrmParam ormParam = new OrmParam();
		try {
			// 组装查询条件
			ormParam.setWhereExp(ormParam.getEqualXML(OepcOepcChangSetaProperty.OEPC_EMP, empEntity.getId()));
			List<OepcOepcChangSetaEntity> list = ormService.selectBeanList(OepcOepcChangSetaEntity.class, ormParam);
			if (list != null && list.size() > 0) {
				// 一个员工同时只能存在一份岗位调整单据
				OepcOepcChangSetaEntity entity = list.get(0);
				// 根据PID查询单据表
				ormParam = new OrmParam();
				String pidWhere = ormParam.getEqualXML(EdmSysColumn.ID, entity.getPid());
				// 单据状态为待审
				String[] status = new String[] { OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3,
						OrderConstants.ORDE_STATUS_4 };
				String statusWhere = ormParam.getInXML(OrderProperty.ORDE_STATUS, status);
				ormParam.setWhereExp(ormParam.and(pidWhere, statusWhere));
				List<EmppostchangeapplyEntity> listEmppost = ormService.selectBeanList(EmppostchangeapplyEntity.class,
						ormParam);
				if (listEmppost != null && listEmppost.size() > 0) {
					map.put("hasEmpPostChange", "1");
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/** 员工是否存在档案变更的待审单据或者入职单据 ***/
	private Map<String, String> containRecordChange(String empNoOrPostId, String orderType) {
		Map<String, String> map = new HashMap<String, String>();
		String retKey = StringUtil.isEqual(orderType, "0") ? "hasRecordChange" : "hasEmpEntryOrder";
		map.put(retKey, "0");
		OrmParam ormParam = new OrmParam();
		String empIdWhere = "";
		if (StringUtil.isEqual(orderType, "0")) {
			// 查询档案变更中待审的单据 员工工号条件
			empIdWhere = ormParam.getEqualXML(EmployeeentryapplyProperty.OEEO_CODE, empNoOrPostId);
		} else {
			// 查询空岗是否有入职待审单查询
			empIdWhere = ormParam.getEqualXML(EmployeeentryapplyProperty.OEEO_POST, empNoOrPostId);
		}
		// 单据类型为0 档案变更
		String recordTypeWhere = ormParam.getEqualXML(EmployeeentryapplyProperty.OEEO_TYPE, orderType);
		// 单据状态为待审
		String[] status = new String[] { OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3,
				OrderConstants.ORDE_STATUS_4 };
		String statusWhere = ormParam.getInXML(OrderProperty.ORDE_STATUS, status);
		// 组装查询条件
		ormParam.setWhereExp(ormParam.and(empIdWhere, recordTypeWhere, statusWhere));
		// 执行查询
		List<EmployeeentryapplyEntity> list = null;
		try {
			list = ormService.selectBeanList(EmployeeentryapplyEntity.class, ormParam);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}

		if (list != null && list.size() > 0) {
			map.put(retKey, "1");
		}
		return map;
	}

	/** 判断岗位是否存在责任人任免单据 ***/
	private Map<String, String> containDeptChargeOrder(String postId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hasDeptChargeOrder", "0");
		try {
			OrmParam ormParam = new OrmParam();
			ormParam.addColumn(EdmSysColumn.PID);
			String pid = "";
			// 根据空岗位ID 部门责任人任免属性集
			ormParam.setWhereExp(ormParam.getEqualXML(OdcsOdcsChrgSetaProperty.ODCS_POST, postId));
			List<OdcsOdcsChrgSetaEntity> listChargeSet = ormService.selectBeanList(OdcsOdcsChrgSetaEntity.class,
					ormParam);
			if (listChargeSet != null && listChargeSet.size() > 0) {
				pid = listChargeSet.get(0).getPid();
				// 根据pid去主表查询是否有待审单据
				ormParam = new OrmParam();
				ormParam.addColumn(EdmSysColumn.ID);
				// 单据状态为待审
				String[] status = new String[] { OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3,
						OrderConstants.ORDE_STATUS_4 };
				String statusWhere = ormParam.getInXML(OrderProperty.ORDE_STATUS, status);
				// id=pid查询条件
				String idWhere = ormParam.getEqualXML(EdmSysColumn.ID, pid);
				// 组装查询条件
				ormParam.setWhereExp(ormParam.and(statusWhere, idWhere));
				// 查询
				List<DeptchargerapplyorderEntity> list = ormService.selectBeanList(DeptchargerapplyorderEntity.class,
						ormParam);

				if (list != null && list.size() > 0) {
					map.put("hasDeptChargeOrder", "1");
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/***
	 * 转化岗位中对象链接字段
	 * 
	 * @param arr
	 * @return
	 */
	private JSONObject convertPostField(JobpositionEntity postJson) {
		// TODO Auto-generated method stub
		// 将岗位对象转换json
		JSONObject postObj = (JSONObject) JSONObject.toJSON(postJson);
		// 所属部门
		String deptId = postJson.getRpos_dept();
		// 汇报岗位
		String supPost = postJson.getRpos_ppost();
		// 岗位ID
		String postId = postJson.getId();
		try {
			// 查询部门
			if (!StringUtil.isNullOrEmpty(deptId)) {
				DepttreeEntity deptEntity = ormService.load(DepttreeEntity.class, deptId);
				if (deptEntity != null) {
					// 判断是否是部门主责人
					postObj.putAll(deptMajorOrAssit(deptEntity.getId(), postId, DateUtil.formatDate(new Date()), null));
					postObj.put("deptId", deptEntity.getId());
					postObj.put("mdepName", deptEntity.getMdep_name());
				}
			}
			// 查询汇报岗位
			postObj.put("ppostName", "");
			postObj.put("rposPpostEmp", "");
			if (!StringUtil.isNullOrEmpty(supPost)) {
				JobpositionEntity ppostEntity = ormService.load(JobpositionEntity.class, supPost);
				if (ppostEntity != null) {
					postObj.put("ppostName", ppostEntity.getRpos_name());
					// 查询汇报岗位的任职人
					if (!StringUtil.isNullOrEmpty(ppostEntity.getRpos_emp())) {
						EmployeeEntity supEmpEntity = ormService.load(EmployeeEntity.class, ppostEntity.getRpos_emp());
						if (supEmpEntity != null) {
							postObj.put("rposPpostEmp", supEmpEntity.getRemp_name());
						}
					}

				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return postObj;

	}

	/***
	 * 根据部门ID 查询部门员工
	 * 
	 * @param deptId
	 * @return
	 */
	public Pagination<EmployeeEntity> queryEmployeeByDeptId(String deptId, int subFlag, int rows, int startPage) {
		// TODO Auto-generated method stub
		Pagination<EmployeeEntity> employeePage = null;
		List<String> listDeptId = new ArrayList<String>();
		if (subFlag == 0) {
			listDeptId.add(deptId);
		} else {
			// 查询下级部门员工
			listDeptId = getSubDept(deptId);
		}
		// 查询员工
		employeePage = queryEmpByPage(listDeptId, rows, startPage);
		return employeePage;
	}

	private List<String> getSubDept(String deptId) {
		// 1.先查询出当前部门 和下级部门
		List<String> deptList = new ArrayList<String>();
		deptService.getDeptLowerIdList(deptList, deptId, new Date(), Integer.MAX_VALUE);
		deptList.add(deptId);
		return deptList;
	}

	/***
	 * 查询部门变更集
	 * 
	 * @param deptId
	 *            部门ID
	 * @param begDate
	 *            生效日期
	 * @return 部门属性集单条记录
	 */
	@SuppressWarnings("static-access")
	private MdepMdepChagSetaEntity queryDeptSetHis(String deptId, String begDate, String endDate) {

		OrmParam ormParam = new OrmParam();
		// 属性集外键PID等于部门ID
		String pidWhere = ormParam.getEqualXML(EdmSysColumn.PID, deptId);

		// 生效时间或者失效时间在部门变更集的生效时间范围内
		// 生效时间
		String dateBegBetweenWhere = ormParam.and(
				ormParam.getLessThanAndEqualXML(MdepMdepChagSetaProperty.MDEP_BEG_HIS, begDate),
				ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_END_HIS, begDate));
		// 失效时间
		String dateEndBetweenWhere = ormParam.and(
				ormParam.getLessThanAndEqualXML(MdepMdepChagSetaProperty.MDEP_BEG_HIS, endDate),
				ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_END_HIS, endDate));
		// 拼接sqlwhere条件
		ormParam.setWhereExp(ormParam.and(pidWhere, ormParam.or(dateBegBetweenWhere, dateEndBetweenWhere)));

		MdepMdepChagSetaEntity changeEntity = null;
		try {
			// 组装查询员工类对象
			List<MdepMdepChagSetaEntity> list = ormService.selectBeanList(MdepMdepChagSetaEntity.class, ormParam);

			// 根据此条件查询最多只有一条记录符合
			if (list != null && list.size() > 0) {
				changeEntity = list.get(0);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return changeEntity;
	}

	/***
	 * 查询部门负责人集合
	 * 
	 * @param deptId
	 *            部门ID
	 * @param begDate
	 *            生效日期
	 * @return
	 */
	public List<MdepMdepChgrSetaEntity> queryChgrSet(String deptId, String begDate, String endDate) {
		OrmParam ormParam = new OrmParam();
		// 组装查询条件
		// 1.属性集的外键PID为部门ID
		String pidWhere = ormParam.getEqualXML(EdmSysColumn.PID, deptId);

		// 2.开始时间在指定时间段内
		String dateBegBetween = ormParam.and(
				ormParam.getLessThanAndEqualXML(MdepMdepChgrSetaProperty.MDEP_CHGR_BEG, begDate),
				ormParam.getGreaterThanXML(MdepMdepChgrSetaProperty.MDEP_CHGR_END, begDate));
		if (!StringUtil.isNullOrEmpty(endDate)) {
			// 3.结束时间在指定时间段内
			String dateEndBetween = ormParam.and(
					ormParam.getLessThanAndEqualXML(MdepMdepChgrSetaProperty.MDEP_CHGR_BEG, endDate),
					ormParam.getGreaterThanXML(MdepMdepChgrSetaProperty.MDEP_CHGR_END, endDate));
			dateBegBetween = ormParam.or(dateBegBetween, dateEndBetween);
		}
		ormParam.setWhereExp(ormParam.and(pidWhere, dateBegBetween));

		List<MdepMdepChgrSetaEntity> list = new ArrayList<MdepMdepChgrSetaEntity>();
		// 3.查询
		try {
			list = ormService.selectBeanList(MdepMdepChgrSetaEntity.class, ormParam);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/***
	 * 根据员工ID查询员工任岗经历(根据生效日期降序 任岗方式升序 排序)
	 * 
	 * @param employeeId
	 * @return 员工任岗经历 添加在岗天数postDays 和在岗月数postMonths两个字段
	 */
	@SuppressWarnings("static-access")
	public List<RempRempPostSetaEntity> queryPostSet(String employeeId) {
		OrmParam ormParam = new OrmParam();
		ormParam.addColumn(EdmSysColumn.ID + "," + RempRempPostSetaProperty.REMP_DTYP_HIS + ","
				+ RempRempPostSetaProperty.REMP_PEMP_HIS + "," + RempRempPostSetaProperty.REMP_PGRAD_HIS + ","
				+ RempRempPostSetaProperty.REMP_POST_BEG + "," + RempRempPostSetaProperty.REMP_POST_END + ","
				+ RempRempPostSetaProperty.REMP_POST_HIS);
		List<RempRempPostSetaEntity> listPostSet = new ArrayList<RempRempPostSetaEntity>();
		try {
			// 根据员工ID查询(即根据PID查询属性集)
			ormParam.setWhereExp(ormParam.and(ormParam.getEqualXML(EdmSysColumn.PID, employeeId)));
			// 按照任岗经历的生效日期降序排序
			ormParam.setOrderExp(SQLSortEnum.DESC, RempRempPostSetaProperty.REMP_POST_BEG);
			listPostSet = ormService.selectBeanList(RempRempPostSetaEntity.class, ormParam);

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return listPostSet;
	}

	/***
	 * 根据员工ID查询员工教育经历
	 * 
	 * @param employeeId
	 * @return 员工教育经历
	 */
	public List<Map<String, Object>> queryStudySet(String employeeId) {
		OrmParam ormParam = new OrmParam();
		// 教育经历属性集外键PID等于员工ID
		ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, employeeId));
		// 根据生效日期降序排序
		ormParam.setOrderExp(SQLSortEnum.ASC, RempRempStudySetaProperty.REMP_DEGREE);

		// ormParam.setOrderExp(SQLSortEnum.DESC,RempRempStudySetaProperty.REMP_DEGREE,
		// RempRempStudySetaProperty.REMP_STU_BEG);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = ormService.selectMapList(RempRempStudySetaEntity.class, ormParam);
			for (Map<String, Object> map : list) {
				map.put(RempRempStudySetaProperty.REMP_RSCH + "_name", "");
				if (!StringUtil.isNullOrEmpty(map.get(RempRempStudySetaProperty.REMP_RSCH))) {
					SchoolEntity entity = getSchoolInfo(String.valueOf(map.get(RempRempStudySetaProperty.REMP_RSCH)));
					map.put(RempRempStudySetaProperty.REMP_RSCH + "_name", entity.getRsch_name());
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/***
	 * 根据员工ID查询员工家庭成员
	 * 
	 * @param employeeId
	 * @return 员工家庭成员
	 */
	public List<RempRempFamSetaEntity> queryFamSet(String employeeId) {
		OrmParam ormParam = new OrmParam();
		// 教育经历属性集外键PID等于员工ID
		ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, employeeId));
		List<RempRempFamSetaEntity> list = new ArrayList<RempRempFamSetaEntity>();
		try {
			list = ormService.selectBeanList(RempRempFamSetaEntity.class, ormParam);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/***
	 * 根据员工ID查询员工技能
	 * 
	 * @param employeeId
	 * @return 员工技能
	 */
	public List<RempRempSkillSetaEntity> querySkillSet(String employeeId) {
		OrmParam ormParam = new OrmParam();
		// 教育经历属性集外键PID等于员工ID
		ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, employeeId));
		List<RempRempSkillSetaEntity> list = new ArrayList<RempRempSkillSetaEntity>();
		try {
			list = ormService.selectBeanList(RempRempSkillSetaEntity.class, ormParam);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/***
	 * 根据员工ID查询员工技能
	 * 
	 * @param employeeId
	 * @return 员工技能
	 */
	public List<RempRempWorkSetaEntity> queryWorkSet(String employeeId) {
		OrmParam ormParam = new OrmParam();
		// 教育经历属性集外键PID等于员工ID
		ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, employeeId));
		List<RempRempWorkSetaEntity> list = new ArrayList<RempRempWorkSetaEntity>();
		try {
			list = ormService.selectBeanList(RempRempWorkSetaEntity.class, ormParam);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/***
	 * 根据部门分页查询员工对象
	 */
	private Pagination<EmployeeEntity> queryEmpByPage(List<String> depts, int rows, int startPage) {
		OrmParam ormParam = new OrmParam();
		// 部门查询条件
		String deptWhere = "";
		// 根据条件组装查询条件
		if (depts.size() == 1) {
			deptWhere = ormParam.getEqualXML(EmployeeProperty.REMP_DEPT, depts.get(0));
		} else {
			deptWhere = ormParam.getInXML(EmployeeProperty.REMP_DEPT, depts.toArray());
		}
		// 排除离职人员
		String removeLeavWhere = ormParam.getUnequalXML(EmployeeProperty.REMP_STATUS, "3");
		// 待岗人员查询条件
		String emptyPostWhere = ormParam.or(ormParam.getIsNull(EmployeeProperty.REMP_POST),
				ormParam.getEqualXML(EmployeeProperty.REMP_POST, ""));
		// 待岗人员
		ormParam.setWhereExp(ormParam.and(emptyPostWhere, deptWhere, removeLeavWhere));

		// 根据员工岗级升序
		ormParam.setOrderExp(SQLSortEnum.ASC, EmployeeProperty.REMP_PGRADE, EmployeeProperty.REMP_NO);

		// 添加分页参数
		ormParam.setPageNo(startPage);
		ormParam.setPageSize(rows);

		// 分页查询
		Pagination<EmployeeEntity> page = null;
		try {
			page = ormService.selectPagedBeanList(EmployeeEntity.class, ormParam);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return page;
	}

	@Override
	public int checkCardNoInEnte(String cardNo) {
		// TODO Auto-generated method stub
		int ret = 0;
		CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
		String enteId = sessionEntity.getEnterpriseId();
		OrmParam ormParam = new OrmParam();
		try {
			// 身份证号
			String cardNoWhere = ormParam.getEqualXML(EmployeeProperty.REMP_CARD_ID, cardNo);
			// 企业
			String enteIdWhere = ormParam.getEqualXML("edmd_ente", enteId);
			// 非离职状态
			String leavStatusWhere = ormParam.getUnequalXML(EmployeeProperty.REMP_STATUS, "3");
			// 组装查询条件
			// 根据身份证号和公司查询是否存在同样的身份证号
			ormParam.setWhereExp(ormParam.and(cardNoWhere, enteIdWhere, leavStatusWhere));
			List<EmployeeEntity> list = ormService.selectBeanList(EmployeeEntity.class, ormParam);
			if (list != null && list.size() > 0) {
				ret = 1;
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public int checkEmail(String entryOrderId,String peopleId, String email) {
		// email是否在入职单中存在
		// 返回的校验码 0代表正常 1表示有入职单存在email 2表示档案变更存在 3表示员工类存在
		OrmParam ormParam = new OrmParam();
		ormParam.addColumn(EdmSysColumn.ID + "," + EmployeeentryapplyProperty.OEEO_TYPE);
		try {
			String sqlWhere="";
			// 入职单中oeeo_email=入参
			sqlWhere = ormParam.getEqualXML(EmployeeentryapplyProperty.OEEO_MAIL, email);
			if(!StringUtil.isNullOrEmpty(peopleId)){
				// 排除本人
				sqlWhere =ormParam.and(sqlWhere, ormParam.getUnequalXML(EmployeeentryapplyProperty.OEEO_EPEO, peopleId));
			}
			if(!StringUtil.isNullOrEmpty(entryOrderId)){
				// 排除相同入职单
				sqlWhere =ormParam.and(sqlWhere, ormParam.getUnequalXML(EdmSysColumn.ID, entryOrderId));
			}
			ormParam.setWhereExp(sqlWhere);
			List<EmployeeentryapplyEntity> listEntry = ormService.selectBeanList(EmployeeentryapplyEntity.class,
					ormParam);
			if (listEntry != null && listEntry.size() > 0) {
				// 取出第一条
				EmployeeentryapplyEntity entity = listEntry.get(0);
				if (StringUtil.isEqual(EmployeeEntryApplyConstant.OEEO_TYPE_ENTRY, entity.getOeeo_type())) {
					// 代表入职单中存在相同的email
					return 1;
				} else {
					// 代表档案变更中存在相同的email
					return 2;
				}
			}
			/** 判断员工类中是否存在 */
			String sqlWhereEmp="";
			ormParam = new OrmParam();
			ormParam.addColumn(EdmSysColumn.ID);
			// email条件
			sqlWhereEmp = ormParam.getEqualXML(EmployeeProperty.REMP_MAIL, email);
			// 排除离职
			sqlWhereEmp = ormParam.and(sqlWhereEmp,ormParam.getUnequalXML(EmployeeProperty.REMP_STATUS, "3"));
			if(!StringUtil.isNullOrEmpty(peopleId)){
				// 排除本人
				sqlWhereEmp = ormParam.and(sqlWhereEmp,ormParam.getUnequalXML(EmployeeProperty.REMP_EPEO_OBJ, peopleId));
			}
			ormParam.setWhereExp(sqlWhereEmp);
			List<EmployeeEntity> listEmployee = ormService.selectBeanList(EmployeeEntity.class, ormParam);
			if (listEmployee != null && listEmployee.size() > 0) {
				return 3;
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
