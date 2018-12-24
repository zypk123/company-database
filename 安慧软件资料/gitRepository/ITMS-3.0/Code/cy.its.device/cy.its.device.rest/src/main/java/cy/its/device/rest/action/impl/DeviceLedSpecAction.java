package cy.its.device.rest.action.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.DeviceLedSpecCriteria;
import cy.its.device.domain.criteria.LedSpecCriteria;
import cy.its.device.domain.model.DeviceLedSpec;
import cy.its.device.domain.model.led.LedSpec;
import cy.its.device.domain.service.IDeviceLedSpecService;
import cy.its.device.domain.service.ILedService;
import cy.its.device.rest.action.IDeviceLedSpecAction;
import cy.its.device.rest.dto.DeviceLedSpecDto;
import cy.its.device.rest.dto.TreeDto;
@RestController
@RequestMapping("/DeviceLedSpecAction")
public class DeviceLedSpecAction implements IDeviceLedSpecAction {
	@Autowired
	ILedService ledService;
	
	@RequestMapping(value="/goSave",method=RequestMethod.POST)
	@ApiOperation(value="saveDeviceLedSpec",notes="创建诱导屏配置信息操作",httpMethod="POST")
	@Override
	public String saveDeviceLedSpec(@ModelAttribute(value = "deviceLedSpecDto") DeviceLedSpecDto deviceLedSpecDto) throws Exception {
		//String 转 Integer
		LedSpec deviceLedSpec=new LedSpec();
		if(!StringUtil.isNullOrEmpty(deviceLedSpecDto.height)){
			deviceLedSpec.setHeight(Short.valueOf(deviceLedSpecDto.height));
		}
		if(!StringUtil.isNullOrEmpty(deviceLedSpecDto.width)){
			deviceLedSpec.setWidth(Short.valueOf(deviceLedSpecDto.width));
		}
		if(!StringUtil.isNullOrEmpty(deviceLedSpecDto.pixesHeight)){
			deviceLedSpec.setPixesHeight(Short.valueOf(deviceLedSpecDto.pixesHeight));
		}
		if(!StringUtil.isNullOrEmpty(deviceLedSpecDto.pixesWidth)){
			deviceLedSpec.setPixesWidth(Short.valueOf(deviceLedSpecDto.pixesWidth));
		}
		deviceLedSpec.setLedDeviceType("1");
		deviceLedSpec.setControlContractor(deviceLedSpecDto.getControlContractor());
		ObjectMapUtils.parseObject(deviceLedSpec, deviceLedSpecDto);
		
		ledService.createLedSpec(deviceLedSpec);
		
		return "success";
	}
	@RequestMapping(value="/goUpdate",method=RequestMethod.POST)
	@ApiOperation(value="saveDeviceLedSpec",notes="更新诱导屏配置信息操作",httpMethod="POST")
	@Override
	public void updateDeviceLedSpec(@ModelAttribute(value="deviceLedSpecDto") DeviceLedSpecDto deviceLedSpecDto) throws Exception {
		
		LedSpec deviceLedSpec = ledService.ledSpecOfId(deviceLedSpecDto.specId);
		
		if(!StringUtil.isNullOrEmpty(deviceLedSpecDto.height)){
			deviceLedSpec.setHeight(Short.valueOf(deviceLedSpecDto.height));
		}else{
			deviceLedSpec.setHeight(null);
		}
		
		if(!StringUtil.isNullOrEmpty(deviceLedSpecDto.width)){
			deviceLedSpec.setWidth(Short.valueOf(deviceLedSpecDto.width));
		}else{
			deviceLedSpec.setWidth(null);
		}
		
		if(!StringUtil.isNullOrEmpty(deviceLedSpecDto.pixesHeight)){
			deviceLedSpec.setPixesHeight(Short.valueOf(deviceLedSpecDto.pixesHeight));
		}
		if(!StringUtil.isNullOrEmpty(deviceLedSpecDto.pixesWidth)){
			deviceLedSpec.setPixesWidth(Short.valueOf(deviceLedSpecDto.pixesWidth));
		}
		
		ObjectMapUtils.parseObjectOverWrite(deviceLedSpec, deviceLedSpecDto);
		
		ledService.updateLedSpec(deviceLedSpec);
		
	}
	@RequestMapping(value="/findByDeviceLedSpec",method=RequestMethod.POST)
	@ApiOperation(value="findByDeviceLedSpec",notes="查找诱导屏",httpMethod="POST")
	@Override
	public Map<String, Object> findByDeviceLedSpec(@ModelAttribute(value="deviceLedSpecDto") DeviceLedSpecDto deviceLedSpecDto) {
		// TODO Auto-generated method stub
		//构建查询条件
		LedSpecCriteria ledSpecC = new LedSpecCriteria();
			ledSpecC.specName=deviceLedSpecDto.getSpecName();
			ledSpecC.colorType=deviceLedSpecDto.getColorType();
			ledSpecC.ledFunctionType=deviceLedSpecDto.getLedFunctionType();
			ledSpecC.controlTypeArr=deviceLedSpecDto.getControlTypeStr();
			ledSpecC.setNoPage();
			ledSpecC.setNeedTotal(true);
		ArrayList<DeviceLedSpecDto> viewList=new ArrayList<DeviceLedSpecDto>();
		List<LedSpec> list=ledService.findLedSpecs(ledSpecC);
		for(LedSpec dLS : list){
			//Integer 转 String
			DeviceLedSpecDto deviceLSDto =new DeviceLedSpecDto();		
			deviceLSDto.setHeight(dLS.getHeight() != null?dLS.getHeight().toString():null);		
			deviceLSDto.setWidth(dLS.getWidth() != null?dLS.getWidth().toString():null);			
			deviceLSDto.setPixesHeight(dLS.getPixesHeight() != null?dLS.getPixesHeight().toString():null);
			deviceLSDto.setPixesWidth(dLS.getPixesWidth() != null?dLS.getPixesWidth().toString():null);
			ObjectMapUtils.parseObject(deviceLSDto, dLS);
			viewList.add(deviceLSDto);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("error", "");
		Map<String,Object> maprows=new HashMap<String,Object>();
		maprows.put("total",viewList.size());
		maprows.put("rows", viewList);
		map.put("result", maprows);
		return map;
	}
	
	@RequestMapping(value="/deleteByDeviceLedSpecId",method=RequestMethod.POST)
	@ApiOperation(value="deleteByDeviceLedSpecId",notes="删除诱导屏配置操作",httpMethod="POST")
	@Override
	public int deleteByDeviceLedSpecId(String ids) throws Exception {
		for (String id : ids.split(",")) {
			ledService.removeLedSpec(id);
		}

		return 1;
	}
	/**
	 * 查询诱导屏配置
	 */
	@Override
	@RequestMapping(value = "/queryDeviceLedSpec",method=RequestMethod.POST)
	@ApiOperation(value="queryDeviceLedSpec",notes="查询诱导屏配置",httpMethod="POST")
	public List<TreeDto> queryDeviceLedSpec() throws Exception {
		List<LedSpec> list = ledService.findLedSpecs(new LedSpecCriteria());
		List<TreeDto> lst = new ArrayList<TreeDto>();
		if(list != null){
			for (LedSpec spec : list) {
				TreeDto treeDto = new TreeDto();
				treeDto.setId(spec.getSpecId());
				treeDto.setText(spec.getSpecName());
				lst.add(treeDto);
			}
		}
		return lst;
	}
	
	@RequestMapping(value="/queryLedSpecInfo",method=RequestMethod.POST)
	@ApiOperation(value="queryLedSpecInfo",notes="查询单个诱导屏配置信息",httpMethod="POST")
	@Override
	public DeviceLedSpecDto queryLedSpecInfo(String specId) throws Exception {
		LedSpec ledSpec = ledService.ledSpecOfId(specId);
		DeviceLedSpecDto deviceLedSpecDto = new DeviceLedSpecDto();
		if(ledSpec.getHeight() != null){
			deviceLedSpecDto.height = ledSpec.getHeight().toString();
		}
		if(ledSpec.getWidth() != null){
			deviceLedSpecDto.width = ledSpec.getWidth().toString();
		}
		if(ledSpec.getPixesHeight() != null){
			deviceLedSpecDto.pixesHeight = ledSpec.getPixesHeight().toString();
		}
		if(ledSpec.getPixesWidth() != null){
			deviceLedSpecDto.pixesWidth = ledSpec.getPixesWidth().toString();
		}
		ObjectMapUtils.parseObject(deviceLedSpecDto,ledSpec);
		return deviceLedSpecDto;
	}
}
