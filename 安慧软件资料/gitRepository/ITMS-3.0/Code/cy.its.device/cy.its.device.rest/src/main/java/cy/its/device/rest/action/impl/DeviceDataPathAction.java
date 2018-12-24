package cy.its.device.rest.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.device.domain.criteria.DataPathCountCriteria;
import cy.its.device.domain.model.DataPathBasic;
import cy.its.device.domain.model.DataPathDynamicDetail;
import cy.its.device.domain.model.DataPathNode;
import cy.its.device.domain.service.IDeviceDataPathService;
import cy.its.device.rest.action.IDeviceDataPathAction;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.DeviceDataPathDto;
import cy.its.device.rest.dto.Results;

@RestController
@RequestMapping("/device/deviceDataPath")
public class DeviceDataPathAction implements IDeviceDataPathAction {
	@Autowired
	IDeviceDataPathService deviceDataPathService;
	
	
	@RequestMapping(value = "/queryDeviceDataPath")
	public DataGridResult<DeviceDataPathDto> queryDeviceDataPath(@ModelAttribute("form") DeviceDataPathDto form) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DataPathCountCriteria criteria = new DataPathCountCriteria();
		ObjectMapUtils.parseObject(criteria, form);
		criteria.isConnectTrackSys = form.getIsConnectTrackSys();
		criteria.passTimeFrom = sdf.parse(form.getPassTimeFrom());
		criteria.passTimeTo = sdf.parse(form.getPassTimeTo());
		criteria.setPageNum(1);
		criteria.setPageSize(Integer.MAX_VALUE);
		criteria.setOrderName(form.getOrderName());
		criteria.setOrderType(form.getOrderType());
		List<DataPathBasic> list = deviceDataPathService.countAllDataPathBasics(criteria);
		List<DeviceDataPathDto> lst = new ArrayList<DeviceDataPathDto>();
		for (int i = 0; i < list.size(); i++) {
			DeviceDataPathDto ddp = new DeviceDataPathDto();
			ObjectMapUtils.parseObject(ddp,list.get(i));
			ddp.setConnectTrackSys(list.get(i).getIsConnectTrackSys());
			if(list.get(i).getAvgDelay() != null){
				ddp.setAvgDelay(list.get(i).getAvgDelay());
			}
			lst.add(ddp);
		}
		long total = criteria.getTotal();
		Results<DeviceDataPathDto> result = new Results<DeviceDataPathDto>();
		result.setTotal(total);
		result.setRows(lst);
		DataGridResult<DeviceDataPathDto> dgr = new DataGridResult<DeviceDataPathDto>();
		dgr.setErro("");
		dgr.setResult(result);
		return dgr;
	};
	
	@RequestMapping(value = "/dataPathDetailOfDevice")
	public DeviceDataPathDto dataPathDetailOfDevice(@ModelAttribute("form") DeviceDataPathDto form) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String deviceSysNbr = form.getDeviceSysNbr();
		Date passTimeBegin = sdf.parse(form.getPassTimeFrom());
		Date passTimeEnd = sdf.parse(form.getPassTimeTo());
		DataPathDynamicDetail dataPathDetail = new DataPathDynamicDetail();
		if("是".equals(form.getConnectTrackSys())){
			dataPathDetail = deviceDataPathService.dynamicDataPathDetailOfDevice(deviceSysNbr, passTimeBegin, passTimeEnd);
		}else if("否".equals(form.getConnectTrackSys())){
			dataPathDetail = deviceDataPathService.dynamicDataPathToItmsDetailOfDevice(deviceSysNbr, passTimeBegin, passTimeEnd);
		}
		DeviceDataPathDto ddpDto = new DeviceDataPathDto();
		ObjectMapUtils.parseObject(ddpDto, dataPathDetail);
		List<DataPathNode> dataPathNodesList = new ArrayList<DataPathNode>();
		if(dataPathDetail != null){
			dataPathNodesList = dataPathDetail.getDataPathNodes();
			ddpDto.setAvgDelay(dataPathDetail.getAvgDelay());
		}
		if(dataPathNodesList.size() != 0 ){
			for (int i = 0; i < dataPathNodesList.size(); i++) {
				if("PreSurveyServer".equals(dataPathNodesList.get(i).getNodeKey())){//设备到前置监控服务器 
					ddpDto.setDev2svrDelay(dataPathNodesList.get(i).getDelayToLastNode());	
				}
				if("AftSurveyServer".equals(dataPathNodesList.get(i).getNodeKey())){//前置到后置监控服务器 
					ddpDto.setSvr2afsvrDelay(dataPathNodesList.get(i).getDelayToLastNode());	
				}
				if("SurveyServer".equals(dataPathNodesList.get(i).getNodeKey())){//设备到监控服务器（只有一个服务器，不分前置与后置）
					ddpDto.setDev2server(dataPathNodesList.get(i).getDelayToLastNode());	
				}
				if("ICE2MQ".equals(dataPathNodesList.get(i).getNodeKey())){//监控服务器到ICE2MQ
					ddpDto.setAfsvr2imDelay(dataPathNodesList.get(i).getDelayToLastNode());	
				}
				if("UploadBegin".equals(dataPathNodesList.get(i).getNodeKey())){//ICE2MQ到上传开始
					ddpDto.setIm2updsDelay(dataPathNodesList.get(i).getDelayToLastNode());	
				}
				if("UploadEnd".equals(dataPathNodesList.get(i).getNodeKey())){//上传开始到上传结束
					ddpDto.setUpds2updeDelay(dataPathNodesList.get(i).getDelayToLastNode());	
				}
				if("ITMS".equals(dataPathNodesList.get(i).getNodeKey())){//ICE2MQ到管控平台
					ddpDto.setIm2Itms(dataPathNodesList.get(i).getDelayToLastNode());	
				}
			}
		}
		return ddpDto;
	}
}
