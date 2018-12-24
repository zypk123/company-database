package cy.its.syscfg.domain.service;

import java.util.List;

import cy.its.syscfg.domain.model.config.SysFestival;
import cy.its.syscfg.domain.model.sysCode.Code;

public interface ISysFestivalService {
	/**
	 * 查询所有的节假日类型
	 * 
	 * @return 节系统假日类型列表
	 */
	public List<Code> findFestivalsType();
	
	/**
	 * 查询所有的节假日信息列表
	 * 
	 * @return 系统节假日信息列表
	 */
	public List<SysFestival> findAllFestivals(String year, String festivalType);
	
	
	/**
	 * 创建节假日
	 * 
	 * @param sysFestival
	 *            系统节假日信息
	 */
	public void createFestival(SysFestival sysFestival);

	/**
	 * 更新节假日
	 * 
	 * @param sysFestival
	 *            系统节假日信息
	 */
	public void updateFestival(SysFestival sysFestival);
	
	/**
	 * 删除指定的节假日
	 * 
	 * @param festivalId
	 *            系统节假日唯一标识ID
	 */
	public void removeFestival(String festivalId);
	
}
