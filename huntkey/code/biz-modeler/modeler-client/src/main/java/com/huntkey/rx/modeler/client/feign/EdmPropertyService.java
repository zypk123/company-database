package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmPropertyHystrix;
import com.huntkey.rx.modeler.common.model.EdmProperty;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by licj on 2017/4/16.
 */
@FeignClient(value = "modeler-provider")
public interface EdmPropertyService {

	@RequestMapping(value = "/properties/{id}", method = RequestMethod.GET)
	Result getProperty(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/properties/tree/{id}/{flag}", method = RequestMethod.GET)
	Result getPropertyTree(@PathVariable("id") String id, @PathVariable(value = "flag") int flag);

	@RequestMapping(value = "/properties/edmpCode", method = RequestMethod.GET)
	Result checkEdmpCode(@RequestParam(value = "edmpCode") String edmpCode,
                         @RequestParam(value = "edmpEdmcId") String edmpEdmcId,
						 @RequestParam(required = false,value = "edmpParentId") String edmpParentId);

	@RequestMapping(value = "/properties/edmpName", method = RequestMethod.GET)
	Result checkEdmpName(@RequestParam(value = "edmpName") String edmpName,
                         @RequestParam(value = "edmpEdmcId") String edmpEdmcId,
						 @RequestParam(required = false,value = "edmpParentId") String edmpParentId);

	@RequestMapping(value = "/properties/getEdmpCode", method = RequestMethod.GET)
	Result getEdmpCode(@RequestParam(value = "classId") String classId);

	@RequestMapping(value = "/properties", method = RequestMethod.POST)
	Result add(@RequestBody EdmProperty edmProperty);

	@RequestMapping(value = "/properties/consts", method = RequestMethod.POST)
	Result addConsts(@RequestBody EdmProperty edmProperty);

	@RequestMapping(value = "/properties", method = RequestMethod.PUT)
	Result update(@RequestBody EdmProperty edmProperty);

	@RequestMapping(value = "/properties/{id}", method = RequestMethod.DELETE)
	Result delete(@PathVariable("id") String id);

	@RequestMapping(value = "/properties/batch/{ids}", method = RequestMethod.DELETE)
	Result deleteIds(@PathVariable("ids") String[] ids);

	@RequestMapping(value = "/properties/move", method = RequestMethod.PUT)
	Result move(@RequestBody String[] ids);

	@RequestMapping(value = "/properties/methods", method = RequestMethod.PUT)
	Result updateMethod(@RequestParam(value = "id") String id, @RequestParam(value = "mid") String mid);

	@RequestMapping(value = "/properties/{id}/triggerCond/{triggerCondId}", method = RequestMethod.PUT)
	Result updateTriggerCond(@PathVariable("id") String uid,
                             @PathVariable("triggerCondId") String triggerCondId);

	@RequestMapping(value = "/properties/{id}/properties", method = RequestMethod.GET)
	Result getProperties(@PathVariable("id") String id);

	@RequestMapping(value = "/properties/{id}/connects", method = RequestMethod.GET)
	Result getConnectsByEdcnEdmpId(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/properties/{id}/convolute", method = RequestMethod.GET)
	Result getConvolutesByEdcoEdmpId(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/properties/{id}/links", method = RequestMethod.GET)
	Result getLinksByEdmlEdmpId(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/properties/{id}/units", method = RequestMethod.GET)
	Result getUnitsByEdunEdmpId(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/properties/{id}/conds", method = RequestMethod.GET)
	Result getCondsByPropertyId(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/properties/{id}/units/sameclassunitlist", method = RequestMethod.GET)
	Result getSameClassUnitsListByEdunEdmpId(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/properties/{id}/connect/properties", method = RequestMethod.GET)
	Result getEdmConnectById(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/properties/getValueLimitById/{id}", method = RequestMethod.GET)
	Result getValueLimitById(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/properties/update", method = RequestMethod.PUT)
	Result updateProperty(@RequestBody EdmProperty edmProperty);

	@RequestMapping(value = "/properties/getTwoProperties/{id}", method = RequestMethod.GET)
	Result getTwoProperties(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/properties/getEnumTwoProperties/{id}", method = RequestMethod.GET)
	Result getEnumTwoProperties(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/properties/getPropertyAndClass", method = RequestMethod.GET)
	Result getPropertyAndClass(@RequestParam(value = "propertyId") String propertyId);

	@RequestMapping(value = "/properties/values", method = RequestMethod.GET)
	Result getPropertyTypeAndValue(@RequestParam(value="classId") String classId,@RequestParam(value="edmpCode") String edmpCode);

	@RequestMapping(value = "/properties/getConProperties", method = RequestMethod.GET)
	Result getConvolutionPropertiesByVersionAndEnName(@RequestParam(value = "edmdVer") String edmdVer,
													  @RequestParam(value = "edmcNameEn") String edmcNameEn);

	@RequestMapping(value = "/properties/limitAndFormula", method = RequestMethod.PUT)
	Result clearLimitAndFormula(@RequestBody Map<String, String> map);

	@RequestMapping(value = "/properties/groups", method = RequestMethod.GET)
	Result selectGroups(@RequestParam(value = "edmcId") String edmcId,
						@RequestParam(value = "edmpId") String edmpId,
						@RequestParam(value = "isSource") String isSource);

	@RequestMapping(value = "/properties/propTree/{edmcId}", method = RequestMethod.GET)
	Result getPropTree(@PathVariable(value = "edmcId")String edmcId);

	@RequestMapping(value = "/properties/enumInfoCode", method = RequestMethod.GET)
    Result getEnumInfoCode(@RequestParam(value = "className") String className,
						   @RequestParam(value = "propCode") String propCode);
}
