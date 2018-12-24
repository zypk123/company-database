package cy.its.vehTrack.domain.service;

import java.util.Map;

public interface IVioSpaceTimeService {
	/**
	 * 违法分布分析
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public  String getVioDistribution(String query) throws Exception;
	
	/**
	 * 违法趋势分析
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public  String getVioTrendStat(String query) throws Exception;

}
