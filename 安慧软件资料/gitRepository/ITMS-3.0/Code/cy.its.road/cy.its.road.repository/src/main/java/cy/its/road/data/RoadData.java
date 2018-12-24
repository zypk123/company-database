package cy.its.road.data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.bus.EventBus;
import cy.its.com.constant.ConstValue;
import cy.its.com.util.Data;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;

import cy.its.road.domain.criteria.RoadCriteria;
import cy.its.road.domain.model.road.Road;
import cy.its.road.mybatis.client.RoadMapper;

@Service
public class RoadData extends Data<RoadMapper, Road> {
	@Autowired(required = true)
	public RoadData(@Qualifier(value = "roadMapper") RoadMapper roadMapper,
			@Qualifier(value = "eventBus") EventBus eventBus) {
		super(roadMapper, ConstValue.ROUTE_KEY_CACHECHANGE_ROAD, eventBus);
	}
	
	
	public static void Test(){
		String s ="dsfsdfsdfdsf";
		String m = null;
		System.out.println(s.contains(m));
	}

//	public List<Road> findRoad(RoadCriteria criteria) {
//		PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
//		if(!StringUtil.isNullOrEmpty(criteria.getOrderName())){
//			PageHelper.orderBy(criteria.getOrderName() + " " + criteria.getOrderType());
//		}
//		Page<Road> page = (Page<Road>) roadMapper.selectRoads(ParamUtil.parseParams(criteria));
//		criteria.setTotal(page.getTotal());
//		return page.getResult();
//		return findDatas(
//				(s) -> (StringUtil.isNullOrEmpty(criteria.roadCode) ? true
//						: criteria.roadCode.equals(s.getRoadCode()))
//						&& (StringUtil.isNullOrEmpty(criteria.roadName) ? true
//								:  (!StringUtil.isNullOrEmpty(s.getRoadName()) &&
//									s.getRoadName().contains(criteria.roadName)) ||
//									(!StringUtil.isNullOrEmpty(s.getRoadAliasName()) &&
//									 s.getRoadAliasName().contains(criteria.roadName))
//								)
//						&& (StringUtil.isNullOrEmpty(criteria.roadType) ? true
//								: criteria.roadType.equals(s.getRoadType()))
//						&& (StringUtil.isNullOrEmpty(criteria.districtCode) ? true
//								: (StringUtil.isNullOrEmpty(s.getDistrictCodeList())? false:
//									s.getDistrictCodeList().contains(criteria.districtCode)))
//						&& (StringUtil.isNullOrEmpty(criteria.orgPrivilegeCode) ? true
//								: isInOrgList(s.getOrgPrivilegeCode(),criteria.orgPrivilegeCode)), 
//				new Comparator<Road>() {
//					@Override
//					public int compare(Road arg0, Road arg1) {
//						if (arg0.getUpdateTime() == null || arg1.getUpdateTime() == null) {
//							if (arg0.getUpdateTime() != null) {
//								return -1;
//							} else if (arg1.getUpdateTime() != null) {
//								return 1;
//							} else {
//								return 0;
//							}
//						}
//
//						return arg1.getUpdateTime().compareTo(arg0.getUpdateTime());
//					}
//				},criteria);
//	}
	
	/**
	 * 
	  * isInOrgList 机构过滤判断
	  *
	  * @Title: isInOrgList
	  * @Description: TODO
	  * @param @param orgPrvCodes
	  * @param @param testCode
	  * @param @return    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	private boolean isInOrgList(String orgPrvCodes,String testCode){
		if(orgPrvCodes == null || "".equals(orgPrvCodes)){
			return false;
		}
		String[] prvCodes = orgPrvCodes.split(",");
		for(String prvCode : prvCodes){
			if(prvCode.startsWith(testCode)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected List<Road> readMapper(RoadMapper mapper) {
		return mapper.selectAllRoads();
	}

	@Override
	protected Map<String, Road> groupList(List<Road> lstData) {
		return lstData.stream().collect(
				Collectors.toMap(Road::getRoadId, (s) -> s));
	}
}
