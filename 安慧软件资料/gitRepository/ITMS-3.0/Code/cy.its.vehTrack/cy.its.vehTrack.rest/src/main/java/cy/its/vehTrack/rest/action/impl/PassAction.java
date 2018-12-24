package cy.its.vehTrack.rest.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.vehTrack.domain.criteria.PassCriteria;
import cy.its.vehTrack.domain.model.PassInfo;
import cy.its.vehTrack.domain.service.IPassService;
import cy.its.vehTrack.rest.action.IPassAction;
import cy.its.vehTrack.rest.dto.PassDto;

/**
 * 
  * @ClassName: PassAction
  * @Description: 过车信息rest服务
  * @author zuop zuop@cychina.cn
  * @date 2015年10月27日 下午2:59:46
  *
 */
@RestController
@RequestMapping("/vehTrack/pass")
public class PassAction implements IPassAction{
	
	@Autowired
	IPassService passService;
	
	@Autowired
	ISiteService siteService;

	/**
	 * 
	  * findPassInfo 查询过车信息
	  * @Title: findPassInfo
	  * @Description: 根据前端查询条件查询过车西你想
	  * @param @param passDto 过车查询条件封装DTO
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return Map    返回类型
	  * @throws
	 */
	@RequestMapping("/findPassInfo")
	public Map findPassInfo(PassDto passDto) throws Exception {
		if("nullPlate".equals(passDto.getJustSearchNullPlate())){
			passDto.setJustSearchNullPlate("true");
		}else if ("unlocal".equals(passDto.getJustNonlocalVeh())){
			passDto.setJustNonlocalVeh("true");
		}
		PassCriteria criteria = passDto.convertToCriteria();
		criteria.setSort("desc");
		List<PassInfo> passInfoList = passService.findPassInfo(criteria);
		List<PassDto> passDtoList = new ArrayList<PassDto>();
		SiteCriteria siteCriteria = new SiteCriteria();
		siteCriteria.setNoPage();
		passInfoList.stream().forEach(item -> {
			Site site = siteService.siteOfCode(item.getSiteCode());
			PassDto passDtoItem = new PassDto(item);
			if(site != null){
				passDtoItem.setSiteName(site.getSiteName());
			}
			passDtoList.add(passDtoItem);
		});
		return returnResult(passDtoList, criteria.getTotal(),passDto.isActiveFlag());
	}
	
	/**
	 * @throws ParseException 
	 *  查询轨迹
	  * findPath(这里用一句话描述这个方法的作用)
	  * @Title: findPath
	  * @Description: TODO
	  * @param @param plateNbr 车牌号
	  * @param @param passTimeStart 开始时间
 	  * @param @param passTimeEnd 结束时间
	  * @param @param searchType 查询类型
	  * @param @return    设定文件
	  * @return List<PassDto>    返回类型
	  * @throws
	 */
	@RequestMapping("/findPath")
	public List<PassDto> findPath(String plateNbr, String passTimeStart, 
			String passTimeEnd, String searchType,String currentOrgPrivilegeCode, int pageSize, int pageNumber,String plateType) throws Exception{
		PassDto passDto = new PassDto(plateNbr, passTimeStart, passTimeEnd, searchType, pageSize, pageNumber,plateType);
		PassCriteria criteria = passDto.convertToCriteria();
		criteria.setSort("asc");
		criteria.setOrgPrvCode(currentOrgPrivilegeCode);
		criteria.setTotal(Integer.MAX_VALUE);
		List<PassInfo> passInfoList = passService.findPassInfo(criteria);
		List<PassDto> passDtoList = new ArrayList<PassDto>();
		SiteCriteria siteCriteria = new SiteCriteria();
		siteCriteria.setNoPage();
		passInfoList.stream().forEach(item -> {
			PassDto passDtoItem = new PassDto(item);
			siteCriteria.siteCode = item.getSiteCode();
			List<Site> result = siteService.findSites(siteCriteria);
			if(result != null && !result.isEmpty()){
				passDtoItem.setSiteName(result.get(0).getSiteName());
				passDtoItem.setSiteLon(result.get(0).getSiteLongitude());
				passDtoItem.setSiteLat(result.get(0).getSiteLatitude());
			}
			passDtoList.add(passDtoItem);
			
		});
		return passDtoList;
	}
	
	/**
	 * 
	  * returnResult 封装返回参数
	  * @Title: returnResult
	  * @Description: 封装返回参数
	  * @param @param dataList 数据
	  * @param @param total 总数
	  * @param @return    设定文件
	  * @return Map<String,Object>    返回类型
	  * @throws
	 */
	private Map<String, Object> returnResult(List<PassDto> dataList, long total,boolean flag) {
		// 封装返回集
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("error", "");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", dataList);
		returnMap.put("result", resultMap);
		if (flag){
			Map<String,PassDto> map = new HashMap<String,PassDto>();
		  for(PassDto bean:dataList){
			  if(!map.containsKey(bean.getPlateNbr())){
				  map.put(bean.getPlateNbr(), bean);
			  }
		  }
		  if(map.size()>3){
			  returnMap.put("error", "请输入准确的车牌号");
		  }
		}
		return returnMap;
	}
}
