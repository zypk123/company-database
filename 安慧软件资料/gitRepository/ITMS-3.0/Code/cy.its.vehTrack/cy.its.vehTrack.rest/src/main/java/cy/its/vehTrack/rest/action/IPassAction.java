package cy.its.vehTrack.rest.action;

import java.util.Map;

import cy.its.vehTrack.rest.dto.PassDto;

public interface IPassAction {

	
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
	public Map findPassInfo(PassDto passDto) throws Exception;
}

