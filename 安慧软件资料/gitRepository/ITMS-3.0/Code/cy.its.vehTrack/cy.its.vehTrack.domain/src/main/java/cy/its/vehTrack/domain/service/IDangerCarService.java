/**
 *name :危险驾驶车辆分析
 *author:wangyunqi
 *date:2016/03/14
 * 
 */
package cy.its.vehTrack.domain.service;

import java.util.Map;

public interface IDangerCarService {
	/**
	 * 危险驾驶车辆TOPN
	 * 
	 * @param dangerDto
	 * @return
	 * @throws Exception
	 */
	public String findDangerCarTopN(String query) throws Exception;

	/**
	 * 危险驾驶车辆整体分析
	 * 
	 * @param dangerDto
	 * @return
	 * @throws Exception
	 */
	public String findDangerCarWholeAnylsis(String query) throws Exception;

	/**
	 * 危险驾驶车辆单项分析
	 * 
	 * @param dangerDto
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findDangerCarSimpleAnylsis(String query) throws Exception;

	/**
	 * 危险驾驶车辆联项分析
	 * 
	 * @param dangerDto
	 * @return
	 * @throws Exception
	 */
	public String findDangerCarMulitAnylsis(String query) throws Exception;

}
