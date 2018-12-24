package cy.its.sysCfg.rest.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.com.util.StringUtil;
import cy.its.sysCfg.rest.action.IAreaAction;
import cy.its.syscfg.domain.model.traffic.SysArea;
import cy.its.syscfg.domain.service.IAreaService;

@RestController
@RequestMapping(value = "/sysCfg/area")
public class AreaAction implements IAreaAction {

	@Autowired 
	IAreaService serivce;
	
	@Override
	public String deleteByPrimaryKey(String areaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/insertArea", method = RequestMethod.POST)
	@ApiOperation(value = "insertArea", notes = "新增区域", httpMethod = "POST")
	public String insert(SysArea record) {
		// TODO Auto-generated method stub
		record.setAreaId(StringUtil.generateUUID());
		record.setOrgPrivilegeCode(record.getCurrentOrgPrivilegeCode());
		int  i = this.serivce.insert(record);
		if (i>0){
			return "sucess";
		}
		return null;
	}

	@Override
	public String insertSelective(SysArea record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysArea findByPrimaryKey(String areaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateByPrimaryKeySelective(SysArea record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateByPrimaryKey(SysArea record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/getAreaList", method = RequestMethod.POST)
	@ApiOperation(value = "getAreaList", notes = "新增区域", httpMethod = "POST")
	public Map<String, Object> findAreaList(SysArea record) {
		record.setOrgPrivilegeCode(record.getCurrentOrgPrivilegeCode());
		List<SysArea> list = this.serivce.selectAreaList(record);
		Map<String,Object> map = new HashMap<String,Object>();
		if(list ==null ){
			map.put("err", "服务器正忙");
		}
		map.put("result", list);
		return map ;
	}

}
