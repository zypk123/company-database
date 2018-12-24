/**
 *name :违法车辆分析查询action
 *author:wangyunqi
 *date:2016/03/14
 * 
 */

package cy.its.vehTrack.rest.action;

import com.alibaba.fastjson.JSONObject;
import cy.its.vehTrack.rest.dto.ViolationCar;
import cy.its.vehTrack.rest.dto.ViolationDetailCar;

public interface IViolationCarAction {

	public JSONObject fingViolationCarInfo(ViolationCar dto) throws Exception;

	public JSONObject queryViolationDetails(ViolationDetailCar dto) throws Exception;
}
