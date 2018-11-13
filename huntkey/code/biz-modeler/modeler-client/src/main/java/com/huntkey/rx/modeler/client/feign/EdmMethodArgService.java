package com.huntkey.rx.modeler.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmMethodArgHystrix;
import com.huntkey.rx.modeler.common.model.EdmMethodArg;

@FeignClient(value = "modeler-provider", fallback = EdmMethodArgHystrix.class)
public interface EdmMethodArgService {

	/**
	 * 方法参数名称的唯一性
	 * @param edmaName
	 * @param edmaEdmmId
	 * @return
	 */
	@RequestMapping(value = "/methodArgs/edmaName", method = RequestMethod.GET)
	Result checkData(@RequestParam(value = "edmaName") String edmaName,
					 @RequestParam(value = "edmaEdmmId") String edmaEdmmId);

	/**
	 * 新增输入参数
	 * @param edmMethodArg
	 * @return
	 */
	@RequestMapping(value = "/methodArgs/inputArg", method = RequestMethod.POST)
	Result insertInputArg(@RequestBody EdmMethodArg edmMethodArg);

	/**
	 * 新增返回值
	 * @param edmMethodArg
	 * @return
	 */
	@RequestMapping(value = "/methodArgs/reArg", method = RequestMethod.POST)
	Result insertReturnArg(@RequestBody EdmMethodArg edmMethodArg);

	/**
	 * 通过id删除返回值
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/methodArgs/retArgs/{id}", method = RequestMethod.DELETE)
	Result deleteRetArg(@PathVariable("id") String id);

	/**
	 * 根据id更新参数
	 * 
	 * @param edmMethodArg
	 * @return
	 */
	@RequestMapping(value = "/methodArgs", method = RequestMethod.PUT)
	Result update(@RequestBody EdmMethodArg edmMethodArg);

	/**
	 * 通过id查找输入参数
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/methodArgs/inputArgs/{id}", method = RequestMethod.GET)
	Result getInputArgById(@PathVariable("id") String id);

	/**
	 * 通过id查找返回值
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/methodArgs/retArgs/{id}", method = RequestMethod.GET)
	Result getReturnArgById(@PathVariable("id") String id);

	/**
	 * 参数的移动
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/methodArgs/move", method = RequestMethod.PUT)
	Result move(@RequestBody String[] ids);

}
