package cy.its.violation.rest.action.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.syscfg.domain.model.config.SysConfig;
import cy.its.syscfg.domain.repository.sysConfig.ISysConfigRepository;
import cy.its.syscfg.domain.service.ISysConfigService;
import cy.its.violation.rest.action.IillegalBusinessConfigAction;
import cy.its.violation.rest.dto.IllegalBusinessConfigDto;
@RestController
@RequestMapping("/illBusinessConfig")
public class IllegalBusinessConfigAction implements IillegalBusinessConfigAction{
	@Autowired
	ISysConfigService sysConfigService;
	
	/*
	  * <p>Title: searchWhiteList</p>
	  * <p>Description: </p>
	  * @param illegalBusinessConfigDto
	  * @return
	  * @see cy.its.violation.rest.action.IillegalBusinessConfigAction#searchWhiteList(cy.its.violation.rest.dto.IllegalBusinessConfigDto)
	  */
	
	@RequestMapping("/searchIllConfig")
	@Override
	public Map searchIllConfig(IllegalBusinessConfigDto illegalBusinessConfigDto) {
		
		 List<SysConfig> list=sysConfigService.findConfig("");
		 ArrayList<IllegalBusinessConfigDto> lstView=new ArrayList<IllegalBusinessConfigDto>();
		 for(SysConfig sysConfig:list){
			 IllegalBusinessConfigDto illBusinessConfig=new IllegalBusinessConfigDto();
			 ObjectMapUtils.parseObject(illBusinessConfig, sysConfig);
			 lstView.add(illBusinessConfig);
		 }

	 		Map map = new HashMap();
			map.put("error", "");
			Map maprow = new HashMap();
			maprow.put("rows", lstView);
			maprow.put("total", lstView.size());
			map.put("result", maprow);
			
			return map;
	}

	/*
	  * <p>Title: goUpdateIllConfig</p>
	  * <p>Description: </p>
	  * @param illegalBusinessConfigDto
	  * @return
	  * @see cy.its.violation.rest.action.IillegalBusinessConfigAction#goUpdateIllConfig(cy.its.violation.rest.dto.IllegalBusinessConfigDto)
	  */
	
	@RequestMapping("/update")
	@Override
	public String goUpdateIllConfig(@ModelAttribute(value = "configId")IllegalBusinessConfigDto illegalBusinessConfigDto) {
		SysConfig sysConfig=new SysConfig();
		
		// 客户端Dto转换领域 Dto
		ObjectMapUtils.parseObject(sysConfig, illegalBusinessConfigDto);

		int resutCode=sysConfigService.updateByPrimaryKey(sysConfig);//调用更新方法
		if(resutCode==1){
			return "success";	
		}
		else
		{
			return "false";
		}

		

	}

	
	

	
}
