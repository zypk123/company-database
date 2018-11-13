package com.huntkey.rx.modeler.provider.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodAndArgVO;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodArgVO;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodTypeVO;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodVO;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.*;
import com.huntkey.rx.modeler.provider.feign.FormulaClient;
import com.huntkey.rx.modeler.provider.feign.ScheduleClient;
import com.huntkey.rx.modeler.provider.service.EdmMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static com.huntkey.rx.modeler.common.util.VOToModel.VOToEdmMethod;

/**
 * 
 * @ClassName: EdmMethodServiceImpl
 * @Description: EdmMethodService实现类
 * @author: zhangyu
 * @date: 2017年4月12日上午10:28:53
 *
 */
@Service
@Transactional(readOnly = true)
public class EdmMethodServiceImpl implements EdmMethodService {


	@Autowired
	EdmMethodMapper edmMethodMapper; // 注入dao

	@Autowired
	EdmClassMapper edmClassMapper;

	@Autowired
	EdmMethodTypeMapper edmMethodTypeMapper;

	@Autowired
	EdmModelerMapper edmModelerMapper;

	@Autowired
	EdmCodeMapper edmCodeMapper;

	@Autowired
	EdmMethodArgMapper edmMethodArgMapper;

	@Autowired
	private DefaultGenerateStorageClient defaultGenerateStorageClient;

	@Autowired
	private FormulaClient formulaClient;

	@Autowired
	private ScheduleClient scheduleClient;

	@Value("${modeler.version}")
	private String version;

	/**
	 * 对方法进行扩展
	 * @return
	 */
	private EdmMethodVO getExdendEdmMethod(EdmMethod edmMethod){
		EdmMethodVO edmMethodVO = ModelToVO.edmMethodToVO(edmMethod);
		edmMethodVO.setEdmmTypeName(edmMethodTypeMapper.selectEdmtNameById(edmMethod.getEdmmType()));// 设置方法分类名
		edmMethodVO.setEdmProgramTypeName(
				edmMethodMapper.selectProgramTypeNameByProgramTypeCode(edmMethod.getEdmmProgramType()));// 设置程序类型名
		edmMethodVO.setEdmEdmdStatusName(
				edmMethodMapper.selectEdmtStatusNameByEdmtStatusCode(edmMethod.getEdmmStatus()));// 设置方法状态名
        //设置方法所属类
		if(!StringUtil.isNullOrEmpty(edmMethod.getEdmmEdmcId())){
			String edmcName = edmClassMapper.getEdmcNameById(edmMethod.getEdmmEdmcId());
			String edmcEnName = edmClassMapper.getEdmcEnNameById(edmMethod.getEdmmEdmcId());
			edmMethodVO.setEdmcName(edmcName);
			edmMethodVO.setEdmcNameEn(edmcEnName);
		}
		String edcoCond = edmMethod.getEdmmTriggerCond();
		if (!StringUtil.isNullOrEmpty(edcoCond)) {
			Result result = formulaClient.selectByPrimaryKey(edcoCond);
			if (result != null) {
				Map<String, String> data = (Map<String, String>) result.getData();
				if (data != null) {
					edmMethodVO.setEdmTriggerCondName(data.get("prplConditionName"));
					edmMethodVO.setEdmTriggerCondNameDesc(data.get("prplConditionDesc"));
				}
			}
		}
		return edmMethodVO;
	}

	@Override
	@Transactional(readOnly = false)
	public int insert(EdmMethod edmMethod) {
		edmMethod.setId(UuidCreater.uuid());// 主键通过uuid生成
		Date now = new Date();
		edmMethod.setAddtime(now);
		edmMethod.setModtime(now);
		edmMethod.setIsDel((byte) 0); // 新增默认为未删除0
		return edmMethodMapper.insertSelective(edmMethod);
	}

	@Override
	public Pagination<EdmMethodVO> selectByExample(String edmmType, String edmmProgramType, String edmmName,
			String edmmClasses,String edmmStatus, int pageNum, int pageSize) {//add 所属类
		List<EdmMethodVO> VOList = null;
		//拆分所属类
		String[] edmmCls = null;//
		if(!StringUtil.isNullOrEmpty(edmmClasses)){
			edmmCls = edmmClasses.split(",");
		}

		Page page = PageHelper.startPage(pageNum, pageSize); // 创建分页对象
		List<EdmMethod> edmMethods = edmMethodMapper.selectEdmMethodByCondition(edmmType, edmmProgramType,
				edmmName, edmmCls, edmmStatus);
		VOList = methodToVO(edmMethods);// 将查询出来的EdmMethod转化成EdmMethodVO
		Pagination<EdmMethodVO> pageInfo = new Pagination<EdmMethodVO>(VOList, pageNum, pageSize, page.getTotal());
		return pageInfo;
	}

	/**
	 * 将查询出来的EdmMethod转化成EdmMethodVO
	 * @param edmMethods
	 * @return
	 */
	private List<EdmMethodVO> methodToVO(List<EdmMethod> edmMethods) {
		List<EdmMethodVO> VOList = null;
		// 将主表list值赋值给VOList
		if (null != edmMethods && edmMethods.size()>0) {
			VOList = new ArrayList<>();
			for (EdmMethod edmMethod : edmMethods) {
				EdmMethodVO edmMethodVO = getExdendEdmMethodPage(edmMethod);
				VOList.add(edmMethodVO);
			}
		}
		return VOList;
	}

	/**
	 * 查询方法扩展信息(分页用，去除不必要的数据查询)
	 * @param edmMethod
	 * @return
	 */
	private EdmMethodVO getExdendEdmMethodPage(EdmMethod edmMethod) {

		EdmMethodVO edmMethodVO = ModelToVO.edmMethodToVO(edmMethod);
		edmMethodVO.setEdmmTypeName(edmMethodTypeMapper.selectEdmtNameById(edmMethod.getEdmmType()));// 设置方法分类名
		edmMethodVO.setEdmProgramTypeName(
				edmMethodMapper.selectProgramTypeNameByProgramTypeCode(edmMethod.getEdmmProgramType()));// 设置程序类型名
//		edmMethodVO.setEdmEdmdStatusName(
//				edmMethodMapper.selectEdmtStatusNameByEdmtStatusCode(edmMethod.getEdmmStatus()));// 设置方法状态名
		EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmMethod.getEdmmEdmcId());
		if (edmClass != null){
			edmMethodVO.setEdmcName(edmClass.getEdmcName()+"/"+edmClass.getEdmcNameEn());
		}
		return  edmMethodVO;
	}

	@Override
	public EdmMethodAndArgVO selectByPrimaryKey(String id) {
		EdmMethodAndArgVO edmMethodAndArgVO = null;
		EdmMethod edmMethod = edmMethodMapper.selectByPrimaryKey(id);
		if(edmMethod != null){
			edmMethodAndArgVO = new EdmMethodAndArgVO();
			edmMethodAndArgVO.setEdmMethod_show(getExdendEdmMethod(edmMethod));
			//设置方法输入参数（显示）
			List<EdmMethodArgVO> ins = setMethodArg(id, (byte)1);
			if(ins.size()>0) {
				edmMethodAndArgVO.setEdmMethodInsertList_show(ins);
			}
			//设置方法返回值（显示）
			List<EdmMethodArgVO> ret = setMethodArg(id, (byte)2);
			if(ret.size()>0) {
				edmMethodAndArgVO.setEdmMethodreturn_show(ret.get(0));
			}

		}
		return edmMethodAndArgVO;
	}

	public List<EdmMethodArgVO> setMethodArg(String id, byte edmaType) {
		EdmMethodArgExample edmMethodArgExample = new EdmMethodArgExample();
		EdmMethodArgExample.Criteria criteria = edmMethodArgExample.createCriteria();
		criteria.andEdmaTypeEqualTo(edmaType).andEdmaEdmmIdEqualTo(id).andIsDelNotEqualTo((byte)1);
		List<EdmMethodArg> edmMethodArgList = edmMethodArgMapper.selectByExample(edmMethodArgExample);
		List<EdmMethodArgVO> edmMethodArgList_ret = new ArrayList<>();
		if(edmMethodArgList.size()>0) {
			for(EdmMethodArg edmMethodArg:edmMethodArgList) {
				EdmMethodArgVO edmMethodArgVO = ModelToVO.edmMethodArgToVO(edmMethodArg);
				//设置数据类型名称
				EdmCodeExample example = new EdmCodeExample();
				EdmCodeExample.Criteria exampleCriteria = example.createCriteria();
				exampleCriteria.andCodeTypeEqualTo("edm_para_type").andCodeValueEqualTo(edmMethodArgVO.getEdmaDataType());
				List<EdmCode> list = edmCodeMapper.selectByExample(example);
				if(list.size()>0) {
					edmMethodArgVO.setEdmaDataTypeName(list.get(0).getCodeName());
				}
				edmMethodArgList_ret.add(edmMethodArgVO);
			}
		}
		return edmMethodArgList_ret;
	}

	@Override
	@Transactional(readOnly = false)
	public int updateByPrimaryKey(EdmMethod edmMethod) {

		edmMethod.setModtime(new Date()); // 默认修改时间为当前时间
		return edmMethodMapper.updateByPrimaryKeySelective(edmMethod);
	}

	@Override
	@Transactional(readOnly = false)
	public int deleteByPrimaryKey(String id) {
		return edmMethodMapper.updateIsDelByPrimaryKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void move(String[] ids) {
		int seq = 1;
		if(null != ids && ids.length>0) {
			for(String id:ids) {
				if(!StringUtil.isNullOrEmpty(id)) {
					edmMethodMapper.updateSeqById(id,seq);
					seq++;
				}
			}
		}
	}


	/**
	 * 查询方法分类结构
	 * @return
	 */
	@Override
	public List<EdmMethodTypeVO> queryClassMethodTypeTree() {
		List<EdmMethodTypeVO> resultList = new ArrayList<EdmMethodTypeVO>();
		EdmMethodExample example = new EdmMethodExample();
		EdmMethodExample.Criteria criteria = example.createCriteria();
		criteria.andIsDelNotEqualTo((byte)1).andEdmmStatusEqualTo("1");
		example.setOrderByClause("edmm_seq asc");
		// 获取所有的方法
		List<EdmMethod> allMethodList = edmMethodMapper.selectByExample(example);
		EdmMethodTypeExample exampleType = new EdmMethodTypeExample();
		EdmMethodTypeExample.Criteria criteriaType = exampleType.createCriteria();
		criteriaType.andIsDelNotEqualTo((byte)1);
		exampleType.setOrderByClause("edmt_seq asc");
		// 获取所有方法分类
		List<EdmMethodType> allMethodTypeList = edmMethodTypeMapper.selectByExample(exampleType);
		if (null != allMethodTypeList && allMethodTypeList.size()>0) {
			// 过滤掉方法分类下面没有方法的方法分类
			allMethodTypeList = filterMethodTypeList(allMethodTypeList);
			// 分类转分类VO
			List<EdmMethodTypeVO> methodTypeVOList = new ArrayList<EdmMethodTypeVO>();
			for (EdmMethodType edmMethodType : allMethodTypeList) {
				EdmMethodTypeVO temp = new EdmMethodTypeVO();
				temp.setId(edmMethodType.getId());
				temp.setEdmtParentId(edmMethodType.getEdmtParentId());
				temp.setEdmtCode(edmMethodType.getEdmtCode());
				temp.setLabel(edmMethodType.getEdmtName());
				temp.setEdmtDesc(edmMethodType.getEdmtDesc());
				temp.setEdmtSeq(edmMethodType.getEdmtSeq());
				methodTypeVOList.add(temp);
			}
			if (!allMethodList.isEmpty()) {
				// 方法转VO，组织方法与方法分类的上下级关系
				for (EdmMethodTypeVO edmMethodTypeVO : methodTypeVOList) {
					List<EdmMethodTypeVO> children = new ArrayList<EdmMethodTypeVO>();
					for (EdmMethod edmMethod : allMethodList) {
						if (!StringUtils.isEmpty(edmMethod.getEdmmType())) {
							if (edmMethod.getEdmmType().equals(edmMethodTypeVO.getId())) {
								EdmMethodTypeVO temp = new EdmMethodTypeVO();
								temp.setId(edmMethod.getId());
								temp.setEdmtParentId(edmMethodTypeVO.getId());
								temp.setLabel(edmMethod.getEdmmName());
								temp.setEdmtDesc(edmMethod.getEdmmDesc());
								temp.setEdmtSeq(edmMethod.getEdmmSeq());
								children.add(temp);
							}
						}
					}
					edmMethodTypeVO.setChildren(children);
				}
			}
			// 组织方法分类的上下级关系
			for (EdmMethodTypeVO edmMethodTypeVO : methodTypeVOList) {
				List<EdmMethodTypeVO> children = new ArrayList<EdmMethodTypeVO>();
				boolean b = false;
				for (EdmMethodTypeVO emtv : methodTypeVOList) {
					if (!StringUtils.isEmpty(emtv.getEdmtParentId()) && !StringUtils.isEmpty(edmMethodTypeVO.getId())) {
						if (emtv.getEdmtParentId().equals(edmMethodTypeVO.getId())) {
							children.add(emtv);
							b = true;
						}
					}
				}
				if (b) {
					List<EdmMethodTypeVO> totalChildren = new ArrayList<EdmMethodTypeVO>();
					if(edmMethodTypeVO.getChildren()!=null){
						totalChildren.addAll(edmMethodTypeVO.getChildren());
					}
					if(children!=null){
						for(EdmMethodTypeVO edmMethodTypeV:children){
							System.out.println("children="+edmMethodTypeV.toString());
						}
						totalChildren.addAll(children);
					}
					for(EdmMethodTypeVO emtv:totalChildren){
						System.out.println("totalChildren="+emtv.toString());
					}
					edmMethodTypeVO.setChildren(totalChildren);
				}
			}
			// 过滤掉非根节点
			for (EdmMethodTypeVO edmMethodTypeVO : methodTypeVOList) {
				if ("".equals(edmMethodTypeVO.getEdmtParentId()) || edmMethodTypeVO.getEdmtParentId() == null) {
					System.out.println("根节点---"+edmMethodTypeVO.toString());
					resultList.add(edmMethodTypeVO);
				}
			}
		}
		return resultList;
	}

	public List<EdmMethodType> filterMethodTypeList(List<EdmMethodType> allMethodTypeList) {
		List<EdmMethodType> resultList = new ArrayList<EdmMethodType>();
		resultList.addAll(allMethodTypeList);
		if (!allMethodTypeList.isEmpty()) {
			for (EdmMethodType edmMethodType : allMethodTypeList) {
				String allSubId = "";
				allSubId = getAllSubMethodTypeIdByParentId(edmMethodType.getId(), allSubId);
				if (",".equals(allSubId.substring(0, 1))) {
					allSubId = allSubId.substring(1, allSubId.length());
				}
				if (",".equals(allSubId.substring(allSubId.length() - 1, allSubId.length()))) {
					allSubId = allSubId.substring(0, allSubId.length() - 1);
				}
				List<EdmMethod> edmMethodList = null;
				String[] item;
				if (!StringUtils.isEmpty(allSubId)) {
					item = allSubId.split(",");
					edmMethodList = edmMethodMapper.selectEdmMethodByIdStr(item);
					if (StringUtils.isEmpty(edmMethodList) || edmMethodList == null || edmMethodList.size() == 0) {
						resultList.remove(edmMethodType);
					}
				}
			}
		}
		return resultList;
	}

	public String getAllSubMethodTypeIdByParentId(String id, String allSubId) {
		EdmMethodType edmMethodType = null;
		edmMethodType = edmMethodTypeMapper.selectByPrimaryKey(id);
		if (!StringUtils.isEmpty(edmMethodType) && edmMethodType != null) {
			allSubId = allSubId + "," + edmMethodType.getId();
		}
		// 递归
		List<String> subId = edmMethodTypeMapper.selectEdmMethodTypeIdByParentId(id);
		if (",".equals(allSubId.substring(0, 1))) {
			allSubId = allSubId.substring(1, allSubId.length());
		}
		if (",".equals(allSubId.substring(allSubId.length() - 1, allSubId.length()))) {
			allSubId = allSubId.substring(0, allSubId.length() - 1);
		}
		if (!subId.isEmpty()) {
			StringBuffer buf = new StringBuffer();
			buf.append(allSubId);
			for (String sId : subId) {
				String temp = "";
				temp = getAllSubMethodTypeIdByParentId(sId, allSubId);
				if (!StringUtils.isEmpty(temp) && temp != null) {
					buf.append(",");
					buf.append(temp);
				}
			}
			allSubId = buf.toString();
		}
		return allSubId;
	}

	@Override
	public List<EdmMethodVO> queryMethodsAndClasses(String type, String name, String version, String className) {
		List<EdmMethod> edmMethods = null;//edmMethodMapper.selectMethodsByName(name);
		EdmMethodExample edmMethodExample = new EdmMethodExample();
		EdmMethodExample.Criteria criteria = edmMethodExample.createCriteria().andIsDelNotEqualTo((byte)1);
		if(!StringUtil.isNullOrEmpty(name)){
			name = "%"+name+"%";
			criteria.andEdmmNameLike(name);
		}
		edmMethods = edmMethodMapper.selectByExample(edmMethodExample);
		List<EdmMethodVO> edmMethodVOS = null;
		if (null != edmMethods && edmMethods.size() > 0) {
			edmMethodVOS = new ArrayList<EdmMethodVO>();
			for (EdmMethod edmMethod : edmMethods) {
				EdmMethodVO edmMethodVO = ModelToVO.edmMethodToVO(edmMethod);
				if(!StringUtil.isNullOrEmpty(edmMethod.getEdmmEdmcId())){
					EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmMethod.getEdmmEdmcId());
					if (null != edmClass) {
						edmMethodVO.setEdmcName(edmClass.getEdmcName());
						//edmMethodVO.setEdmcId(edmClass.getId());
						EdmModeler edmModeler = edmModelerMapper.selectByPrimaryKey(edmClass.getEdmcEdmdId());
						if (null != edmModeler) {
							edmMethodVO.setEdmdVersion(edmModeler.getEdmdVer());
							edmMethodVO.setEdmdId(edmModeler.getId());
							edmMethodVO.setEdmdStatus(edmCodeMapper.selectEdmdStatusNameBycodeValue(edmModeler.getEdmdStatus()));
							// edmMethodVO.setEdmdStatus(edmCodeMapper.);
						}
					}
				}
				edmMethodVO.setEdmtName(edmMethodTypeMapper.selectTypeNameById(edmMethod.getEdmmType()));
				edmMethodVOS.add(edmMethodVO);
			}
		}
		List<EdmMethodVO> edmMethodVOS1 = null;
		if (null != edmMethodVOS && edmMethodVOS.size() > 0) {
			edmMethodVOS1 = new ArrayList<EdmMethodVO>();
			for (EdmMethodVO edmMethodVO : edmMethodVOS) {
				if (!StringUtil.isNullOrEmpty(className)) {
					if (!StringUtil.isNullOrEmpty(edmMethodVO.getEdmcName())) {
						if (edmMethodVO.getEdmcName().contains(className)) {
							if (!StringUtil.isNullOrEmpty(version)) {
								if (version.equals(edmMethodVO.getEdmdVersion())) {
									if (!StringUtil.isNullOrEmpty(type)) {
										if (type.equals(edmMethodVO.getEdmtName())) {
											edmMethodVOS1.add(edmMethodVO);
										}
									} else {
										edmMethodVOS1.add(edmMethodVO);
									}
								}
							} else {
								if (!StringUtil.isNullOrEmpty(type)) {
									if (type.equals(edmMethodVO.getEdmtName())) {
										edmMethodVOS1.add(edmMethodVO);
									}
								} else {
									edmMethodVOS1.add(edmMethodVO);
								}
							}
						}
					}
				} else {
					if (!StringUtil.isNullOrEmpty(version)) {
						if (version.equals(edmMethodVO.getEdmdVersion())) {
							if (!StringUtil.isNullOrEmpty(type)) {
								if (type.equals(edmMethodVO.getEdmtName())) {
									edmMethodVOS1.add(edmMethodVO);
								}
							} else {
								edmMethodVOS1.add(edmMethodVO);
							}
						}
					} else {
						if (!StringUtil.isNullOrEmpty(type)) {
							if (type.equals(edmMethodVO.getEdmtName())) {
								edmMethodVOS1.add(edmMethodVO);
							}
						} else {
							edmMethodVOS1.add(edmMethodVO);
						}
					}

				}
			}
		}

		return edmMethodVOS1;
	}

	/*
	 * 根据类id查方法id数组
	 */
	@Override
	public String[] selectMethodIdsByClassId(String edmmEdmcId) {
		String[] resultArray = null;
		List<String> methodIdList = edmMethodMapper.selectMethodIdListByClassId(edmmEdmcId);
		if (null != methodIdList && methodIdList.size()>0) {
			resultArray =(String[]) methodIdList.toArray();
		}
		return resultArray;
	}

	@Override
	@Transactional(readOnly = true)
	public String checkUnique(String edmcId,String edmmName) {
		String errorStr = null;
		if (StringUtil.isNullOrEmpty(edmmName)) {
			errorStr = "方法名称不能为空";
			return errorStr;
		}
		if(StringUtil.isNullOrEmpty(edmcId)){
			errorStr = "该类不存在";
			return errorStr;
		}
		EdmMethodExample checkExample = new EdmMethodExample();
		checkExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmmEdmcIdEqualTo(edmcId).andEdmmNameEqualTo(edmmName);
		List<EdmMethod> edmMethodList = edmMethodMapper.selectByExample(checkExample);
		if (edmMethodList != null && edmMethodList.size() > 0) {
			errorStr = "该类方法名已存在";
			return errorStr;
		}
		return errorStr;
	}

	@Override
	@Transactional(readOnly = true)
	public String checkEdmmName(String edmcName, String edmmName) {
		String errorStr = null;
		if (StringUtil.isNullOrEmpty(edmmName)) {
			errorStr = "方法名称不能为空";
			return errorStr;
		}
		EdmMethodExample checkExample = new EdmMethodExample();
		String edmcId = getEdmcIdByEdmcName(edmcName, version);
		if(StringUtil.isNullOrEmpty(edmcId)){
			errorStr = "该类名不存在";
			return errorStr;
		}
		checkExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmmEdmcIdEqualTo(edmcId).andEdmmNameEqualTo(edmmName);
		List<EdmMethod> edmMethodList = edmMethodMapper.selectByExample(checkExample);
		if (edmMethodList != null && edmMethodList.size() > 0) {
			errorStr = "该类方法名已存在";
			return errorStr;
		}
		return errorStr;
	}

	@Override
	public String geMethodNameById(String id) {
		return edmMethodMapper.geMethodNameById(id);
	}

	@Override
	public void deleteMethods(String[] ids) {
		if(null != ids && ids.length>0){
			for (String id : ids){
				edmMethodMapper.updateIsDelByPrimaryKey(id);
				//删除方法arg数据
				deleteMethodArgs(id);
			}
		}
	}

	@Override
	public StringBuffer getMethodBody(String edmmName) {
		EdmMethodExample example = new EdmMethodExample();
		EdmMethodExample.Criteria criteria = example.createCriteria();
		criteria.andEdmmNameEqualTo(edmmName).andIsDelNotEqualTo((byte)1);
		List<EdmMethod> list = edmMethodMapper.selectByExample(example);
		if(list.size()==0) {
			return null;
		}else {
			String groupName = Constant.FILE_GROUP_NAME;
			String edmmPath = list.get(0).getEdmmProgramStorage().replace(groupName+"/","");
			byte[] content = null;
			String str = "";
			String encode = "GBK";
			StringBuffer sb = new StringBuffer();
			try{
				DownloadByteArray callback = new DownloadByteArray();
				content = defaultGenerateStorageClient.downloadFile(groupName,edmmPath, callback);
				InputStream in = new ByteArrayInputStream(content);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, encode));
				while ((str = reader.readLine()) != null) {
					sb.append(str);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return sb;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void insertEdmMethodAndArgVO(EdmMethodAndArgVO edmMethodAndArgVO) {
		//插入方法
		if(edmMethodAndArgVO.getEdmMethod_in() != null) {
			Date now = new Date();
			EdmMethodVO edmMethodVO = edmMethodAndArgVO.getEdmMethod_in();
			//处理类名和方法类型名
			/*if (StringUtil.isNullOrEmpty(edmMethodVO.getEdmmEdmcId())) {
				String edmcId = getEdmcIdByEdmcName(edmMethodVO.getEdmcName(), version);
				if (!StringUtil.isNullOrEmpty(edmcId)) { edmMethodVO.setEdmmEdmcId(edmcId); }
			}*/
			if (StringUtil.isNullOrEmpty(edmMethodVO.getEdmmType())) {
				String edmmType = edmMethodTypeMapper.selectEdmtIdByEdmtName(edmMethodVO.getEdmmTypeName());
				if (!StringUtil.isNullOrEmpty(edmmType)) { edmMethodVO.setEdmmType(edmmType); }
			}
			EdmMethod edmMethod =  VOToEdmMethod(edmMethodVO);
			edmMethod.setId(UuidCreater.uuid());// 主键通过uuid生成
			if(!StringUtil.isNullOrEmpty(edmMethod.getEdmmEdmcId())){
				Integer seq = edmMethodMapper.getMaxSeqByCid(edmMethod.getEdmmEdmcId());
				if (null != seq) {
					seq = seq + 1;
				} else {
					seq = 1;
				}
				edmMethod.setEdmmSeq(seq);
			}
			edmMethod.setAddtime(now);
			edmMethod.setModtime(now);
			edmMethod.setIsDel((byte) 0); // 新增默认为未删除0
			edmMethod.setAdduser("admin");
			edmMethod.setModuser("admin");

			/*if("3".equals(edmMethod.getEdmmMethodType())){
				Result  result = scheduleClient.registerByEDM(edmMethod.getEdmmName(),edmMethod.getEdmmPlanId(),edmMethod.getId());
				if(result.getRetCode() !=1){
					//throw new RuntimeException();
				}
				String jobId = (String) result.getData();
				edmMethod.setEdmmJobId(jobId);
			}*/
			//插入方法
			edmMethodMapper.insertSelective(edmMethod);
			//插入方法arg
			insertMethodArgs(edmMethodAndArgVO, edmMethod.getId());

		}
	}

	@Override
	@Transactional(readOnly = false)
	public void updateMethodAndArg(EdmMethodAndArgVO edmMethodAndArgVO) {

		EdmMethodVO edmMethodVO = edmMethodAndArgVO.getEdmMethod_in();

		EdmMethod edmMethod = edmMethodMapper.selectByPrimaryKey(edmMethodVO.getId());
				//删除原先arg
		deleteMethodArgs(edmMethodVO.getId());
		System.out.println("================:    "+edmMethodAndArgVO.getEdmMethod_in().toString() );
		//处理类名和方法类型名
		/*if (StringUtil.isNullOrEmpty(edmMethodVO.getEdmmEdmcId())) {
			String edmcId = getEdmcIdByEdmcName(edmMethodVO.getEdmcName(), version);
			if (!StringUtil.isNullOrEmpty(edmcId)) { edmMethodVO.setEdmmEdmcId(edmcId); }
		}*/
		if (StringUtil.isNullOrEmpty(edmMethodVO.getEdmmType())) {
			String edmmType = edmMethodTypeMapper.selectEdmtIdByEdmtName(edmMethodVO.getEdmmTypeName());
			if (!StringUtil.isNullOrEmpty(edmmType)) { edmMethodVO.setEdmmType(edmmType); }
		}
		//更新method
		System.out.println("================:    "+edmMethodAndArgVO.getEdmMethod_in().toString() );
		/*if(!"3".equals(edmMethod.getEdmmMethodType()) && "3".equals(edmMethodVO.getEdmmMethodType() )){
			Result  result = scheduleClient.registerByEDM(edmMethodVO.getEdmmName(),edmMethodVO.getEdmmPlanId(),edmMethodVO.getId());
			if(result.getRetCode() !=1){
				//throw new RuntimeException();
			}
			String jobId = (String) result.getData();
			edmMethodVO.setEdmmJobId(jobId);
		}*/
		updateByPrimaryKey(VOToEdmMethod(edmMethodVO));
		//插入方法arg
		insertMethodArgs(edmMethodAndArgVO, edmMethodVO.getId());
	}

	public void insertMethodArgs(EdmMethodAndArgVO edmMethodAndArgVO, String methodId) {
		//插入方法输入参数
		List<EdmMethodArg> edmMethodInsertList = edmMethodAndArgVO.getEdmMethodInsertList_in();
		if(edmMethodInsertList.size()>0) {
			for(EdmMethodArg edmMethodArg:edmMethodInsertList) {
				edmMethodArg.setEdmaType((byte)1);
				edmMethodArg.setId(UuidCreater.uuid());
				edmMethodArg.setEdmaEdmmId(methodId);
				edmMethodArg.setIsDel((byte)0);
				edmMethodArg.setAddtime(new Date());
				edmMethodArg.setAdduser("admin");
				edmMethodArgMapper.insertSelective(edmMethodArg);
			}
		}
		//插入方法返回值
		if(edmMethodAndArgVO.getEdmMethodreturn_in() != null) {
			EdmMethodArg edmMethodArg = edmMethodAndArgVO.getEdmMethodreturn_in();
			edmMethodArg.setEdmaType((byte)2);
			edmMethodArg.setId(UuidCreater.uuid());
			edmMethodArg.setEdmaEdmmId(methodId);
			edmMethodArg.setIsDel((byte)0);
			edmMethodArg.setAddtime(new Date());
			edmMethodArg.setAdduser("admin");
			edmMethodArgMapper.insertSelective(edmMethodArg);
		}
	}

	public void deleteMethodArgs(String id) {
		edmMethodArgMapper.updateIsDelByEdmaEdmmId(id);
	}

	/**
	 * 禁/启用方法
	 * @param id
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateEdmmStatus(String id,String edmmStatus) {
		edmMethodMapper.updateEdmmStatus(id, edmmStatus);
	}

	@Override
	public String getMethodByIdClassId(String id, String edmClassId) {
		String className = "";
		String methodName="";
		EdmMethod edmMethod = edmMethodMapper.selectByPrimaryKey(id);
		if(edmMethod != null){
			EdmClass edmClass  = edmClassMapper.selectByPrimaryKey(edmClassId);
			if(edmClass !=null){
				className = edmClass.getEdmcName();
			}
			methodName = edmMethod.getEdmmName();
			return  className + "," + methodName;
		}
		return null ;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateTriggerCond(String id, String condId) {
		edmMethodMapper.updateTriggerCond(id,condId);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteTriggerCond(String id) {
		edmMethodMapper.deleteTriggerCond(id);
	}

	@Override
	public EdmMethod getSimpleMethodById(String id) {
		return edmMethodMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据类名称和modeler版本号获取类id
	 * @param edmcName
	 * @param version
	 * @return
	 */
	@Override
	public String getEdmcIdByEdmcName(String edmcName, String version) {
		if (StringUtil.isNullOrEmpty(edmcName) || StringUtil.isNullOrEmpty(version)) { return null; }
		String modelerId = edmModelerMapper.selectEdmdIdByVer(version);
		if (StringUtil.isNullOrEmpty(modelerId)) { return null; }
		return edmClassMapper.selectEdmcIdByEdmdIdAndName(modelerId, edmcName);
	}

	/**
	 * 根据类名与方法名获取方法（注：获取的是当前配置的modeler版本中的方法）
	 * @param edmmName
	 * @return
	 */
	@Override
	public EdmMethodAndArgVO getEdmMethodByEdmcNameAndEdmmName(String edmcName,String edmmName) {
		if (StringUtil.isNullOrEmpty(edmcName) || StringUtil.isNullOrEmpty(edmmName)) { return null; }
		//获取当前版本modeler的版本id
		String edmcId = getEdmcIdByEdmcName(edmcName, version);;
		if (StringUtil.isNullOrEmpty(edmcId)) { return null; }
		EdmMethod edmMethod = edmMethodMapper.selectByEdmcIdAndEdmmName(edmcId,edmmName);
		if (edmMethod == null) {return null;}

		EdmMethodAndArgVO edmMethodAndArgVO = new EdmMethodAndArgVO();
		EdmMethodVO edmMethodVO = ModelToVO.edmMethodToVO(edmMethod);
		edmMethodVO.setEdmcName(edmClassMapper.getEdmNameEnById(edmcId));
		edmMethodVO.setEdmmTypeName(edmMethodTypeMapper.selectTypeNameById(edmMethodVO.getEdmmType()));
		edmMethodAndArgVO.setEdmMethod_in(edmMethodVO);

		EdmMethodArgExample example = new EdmMethodArgExample();
		EdmMethodArgExample.Criteria criteria = example.createCriteria();
		criteria.andEdmaEdmmIdEqualTo(edmMethodVO.getId()).andIsDelNotEqualTo((byte) 1).andEdmaTypeEqualTo((byte) 1);
		List<EdmMethodArg> edmMethodArgs = edmMethodArgMapper.selectByExample(example);
		edmMethodAndArgVO.setEdmMethodInsertList_in(edmMethodArgs);
        EdmMethodArgExample e = new EdmMethodArgExample();
        EdmMethodArgExample.Criteria c = e.createCriteria();
        c.andEdmaEdmmIdEqualTo(edmMethodVO.getId()).andIsDelNotEqualTo((byte) 1).andEdmaTypeEqualTo((byte) 2);
		edmMethodArgs = edmMethodArgMapper.selectByExample(e);
		if (edmMethodArgs != null && edmMethodArgs.size() > 0) {
		    edmMethodAndArgVO.setEdmMethodreturn_in(edmMethodArgs.get(0));
        }
		return edmMethodAndArgVO;
	}

}
