package cy.its.sysCfg.rest.action;

import java.util.Map;

import cy.its.sysCfg.rest.dto.FestivalDto;

public interface IFestivalAction {
	// 查询
	public Map<String, Object> selectAllFestivalList(FestivalDto festivalDto) throws Exception;

	// 更新
	public String updateFestival(FestivalDto festivalDto) throws Exception;

	// 创建系统代码
	String createFestival(FestivalDto festivalDto) throws Exception;

	// 单个删除
	String removeFestival(String festivalId);
	
	// 批量删除
	String deleteFestival(String festivalIds);

}
