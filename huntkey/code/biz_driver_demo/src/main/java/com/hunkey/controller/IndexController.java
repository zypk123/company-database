package com.hunkey.controller;

import com.hunkey.service.DriverService;
import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/index")
public class IndexController {

	@Autowired
	private DriverService driverService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Result get(@RequestParam String params)  {
		System.out.println("-----"+params);
        return  driverService.test("get");
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public Result post(@RequestBody String params)  {
		System.out.println("-----"+params);
		return  driverService.test("post");
	}

	@RequestMapping(value = "/put", method = RequestMethod.PUT)
	public Result put(@RequestBody String params)  {
		System.out.println("-----"+params);
		return  driverService.test("put");
	}

	@RequestMapping(value = "/patch", method = RequestMethod.PATCH)
	public Result patch(@RequestBody String params)  {
		System.out.println("-----"+params);
		return  driverService.test("patch");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Result delete(@RequestBody String params)  {
		System.out.println("-----"+params);
		return  driverService.test("delete");
	}

}