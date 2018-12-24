package cy.its.device.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.bigdata.Config;
import cy.its.device.domain.criteria.DataPathCountCriteria;
import cy.its.device.domain.criteria.DataPathCountCriteriaDB;
import cy.its.device.domain.criteria.DeviceDataPathCriteria;
import cy.its.device.domain.model.DataPathBasic;
import cy.its.device.domain.model.DataPathDetail;
import cy.its.device.domain.model.DataPathDynamicDetail;
import cy.its.device.domain.model.DataPathNode;
import cy.its.device.domain.model.DeviceDataPath;
import cy.its.device.domain.repository.IDeviceDataPathRepository;
import cy.its.device.mybatis.client.DeviceDataPathMapper;
import cy.its.device.mybatis.model.DynamicDataPathDetail;

@Service
public class DeviceDataPathRepository implements IDeviceDataPathRepository {
	
	@Autowired
	DeviceDataPathMapper deviceDataPathMapper;
	
	@Override
	public List<DeviceDataPath> findDataPath(DeviceDataPathCriteria criteria) {
		// TODO Auto-generated method stub
		PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
		Page<DeviceDataPath> page = (Page<DeviceDataPath>) deviceDataPathMapper.findDeviceDataPath(ParamUtil.parseParams(criteria));
		criteria.setTotal((int) page.getTotal());
		return page.getResult();
	}

	@Override
	public int countDataPath(DeviceDataPathCriteria criteria) {
		// TODO Auto-generated method stub
		return deviceDataPathMapper.countDataPath(ParamUtil.parseParams(criteria));
	}

	@Override
	public List<DataPathBasic> countDataPathBasics(DataPathCountCriteria criteria) {
		// TODO Auto-generated method stub
		return deviceDataPathMapper.selectDataPathBasics(ParamUtil.parseParams(criteria));
	}

	@Override
	public DataPathDetail dataPathDetailOfDevice(String deviceSysNbr, Date passTimeFrom, Date passTimeTo) {
		return deviceDataPathMapper.dataPathDetailOfDevice(createParam(deviceSysNbr, passTimeFrom, passTimeTo));
	}

	@Override
	public DataPathDynamicDetail dynamicDataPathDetailOfDevice(String deviceSysNbr, Date passTimeFrom,
			Date passTimeTo) {				
		DynamicDataPathDetail detail = deviceDataPathMapper.selectDynamicDataPath(
				createParam(deviceSysNbr, passTimeFrom, passTimeTo));
		if(detail != null){			
			DataPathDynamicDetail rst = createDynamicDetail(detail);
			fillDataPathNodes("UploadBegin", "上传开始", detail.getUpd0Delay(), rst.getDataPathNodes());
			fillDataPathNodes("UploadEnd", "上传结束", detail.getAvgDelay(), rst.getDataPathNodes());	
			return rst;
		}
		
		return null;
	}


	@Override
	public List<DataPathBasic> countDataPathToItmsBasics(DataPathCountCriteria criteria) {
		return deviceDataPathMapper.selectDataPathToItmsBasics(ParamUtil.parseParams(criteria));
	}

	@Override
	public DataPathDynamicDetail dynamicDataPathToItmsDetailOfDevice(String deviceSysNbr, Date passTimeFrom,
			Date passTimeTo) {
		DynamicDataPathDetail detail = deviceDataPathMapper.selectDynamicDataPathToItms(
				createParam(deviceSysNbr, passTimeFrom, passTimeTo));
		if(detail != null){			
			DataPathDynamicDetail rst = createDynamicDetail(detail);
			fillDataPathNodes("ITMS", "管控平台", detail.getAvgDelay(), rst.getDataPathNodes());
			return rst;
		}
		
		return null;
	}
	
	@Override
	public List<DataPathBasic> countAllDataPathBasics(DataPathCountCriteria criteria) {
		try {
			if(!Boolean.parseBoolean(Config.getProperties("use_big_data"))){
				DataPathCountCriteriaDB criteriaDB = new DataPathCountCriteriaDB(criteria);
				String params = criteriaDB.toString();
				
			}else{
				PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
				if(!StringUtil.isNullOrEmpty(criteria.getOrderName())){
					PageHelper.orderBy(criteria.getOrderName() + " " + criteria.getOrderType());
				}
				Page<DataPathBasic> page = (Page<DataPathBasic>) deviceDataPathMapper.selectAllDataPathBasics(ParamUtil.parseParams(criteria));
				criteria.setTotal(page.getTotal());
				return page.getResult();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	private Map<String, Object> createParam(String deviceSysNbr, Date passTimeFrom, Date passTimeTo) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("deviceSysNbr", deviceSysNbr);
		m.put("passTimeFrom", passTimeFrom);
		m.put("passTimeTo", passTimeTo);
		return m;
	}

	private DataPathDynamicDetail createDynamicDetail(DynamicDataPathDetail detail) {
		DataPathDynamicDetail rst = new DataPathDynamicDetail();
		rst.setDeviceSysNbr(detail.getDeviceSysNbr());
		rst.setDeviceName(detail.getDeviceName());
		rst.setAvgDelay(detail.getAvgDelay() != null?detail.getAvgDelay().intValue():null);		
		List<DataPathNode> lst = new ArrayList<DataPathNode>();		
		if(detail.getSvrDelay() != null) {
			if(detail.getAfsvrDelay() != null){
			    lst.add(new DataPathNode("PreSurveyServer", "前置监控服务器", detail.getSvrDelay(), detail.getSvrDelay()));				    
			}else{
				lst.add(new DataPathNode("SurveyServer", "监控服务器", detail.getSvrDelay(), detail.getSvrDelay()));
			}
		}

		fillDataPathNodes("AftSurveyServer", "后置监控服务器", detail.getAfsvrDelay(), lst);
		fillDataPathNodes("ICE2MQ", "ICE2MQ", detail.getImDelay(), lst);
		fillDataPathNodes("PreServer", "前置机", detail.getPreDelay(), lst);
		fillDataPathNodes("AftServer", "后置机", detail.getAfDelay(), lst);
		rst.setDataPathNodes(lst);
		return rst;
	}

	private void fillDataPathNodes(String nodeKey, String nodeName, Integer delay, List<DataPathNode> lst) {
		if (delay != null) {			
			DataPathNode lastNode = null;
			if (lst.size() > 0) {
				lastNode = lst.get(lst.size() - 1);
			}			
			lst.add(new DataPathNode(nodeKey, nodeName,
					lastNode != null ? delay - lastNode.getTotalDelay() : delay, delay.intValue()));
		}
	}


}
