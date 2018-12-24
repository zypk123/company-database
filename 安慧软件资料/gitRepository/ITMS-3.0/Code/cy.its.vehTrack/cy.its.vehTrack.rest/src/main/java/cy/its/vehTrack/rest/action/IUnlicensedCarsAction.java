/**
 *name :无牌车车辆分析
 *author:wangyunqi
 *date:2016/03/14
 * 
 */
package cy.its.vehTrack.rest.action;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.rest.dto.UnlicensedCarDto;

public interface IUnlicensedCarsAction {
	
	JSONObject findUnlicensedCarsInfo(UnlicensedCarDto carDto) throws Exception;
}
