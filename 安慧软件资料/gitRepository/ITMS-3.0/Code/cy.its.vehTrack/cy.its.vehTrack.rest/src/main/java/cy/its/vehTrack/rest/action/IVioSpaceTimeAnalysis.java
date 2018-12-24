package cy.its.vehTrack.rest.action;

import java.util.Map;

import cy.its.vehTrack.rest.dto.VioSpaceTimeInputBean;

public interface IVioSpaceTimeAnalysis {
	
	/**
	 * 违法分布分析
	 * @param inputBean
	 * @throws Exception
	 */
	public Map<String, Object> getVioDistribution(VioSpaceTimeInputBean inputBean) throws Exception;

	
	/**
	 * 违法趋势分析
	 * @param inputBean
	 * @throws Exception
	 */
	public  Map<String, Object> getVioTrendStat(VioSpaceTimeInputBean inputBean) throws Exception;

}
