package cy.its.road.rest.action;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import cy.its.road.rest.dto.RoadDto;
import cy.its.road.rest.dto.RoadTreeDto;

public interface IRoadCodeAction {
	/**
	 * 获取道路树数据
	 * @return 道路树对象
	 * @throws Exception
	 */
	public List<RoadTreeDto> getRoadCodeTree(String currentOrgPrivilegeCode) throws Exception;
	//创建道路信息 
	public  String createRoad(RoadDto roadDto) throws Exception;
	//查询
	public Map<String, Object> searchRoad(RoadDto roadDto) throws Exception;
	//更新
	public int goUpdateRoad(RoadDto road) throws Exception;
	//单个删除
	public String goDeleteRoad(String roadId);
	//批量删除
	public String goRemoveRoad(String roadIdStr);
	
	//根据道路Code查询该道路是否单向通行来添加断面
	public int querySectionByRoadId(String roadId) throws Exception;
	//根据roadId获取道路信息
	public RoadDto queryRoadInfoById(String roadId) throws Exception;
}
