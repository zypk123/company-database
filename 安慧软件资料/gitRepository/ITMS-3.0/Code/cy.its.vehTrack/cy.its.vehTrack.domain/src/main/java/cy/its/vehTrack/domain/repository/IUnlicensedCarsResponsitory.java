/**
 *name :无牌车车辆分析
 *author:wangyunqi
 *date:2016/03/14
 * 
 */

package cy.its.vehTrack.domain.repository;

import java.util.Map;

public interface IUnlicensedCarsResponsitory {
	
	public Map<String ,Object> findUnlicensedCarsInfo(String query) throws Exception;
		

}
