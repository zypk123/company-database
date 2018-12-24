package cy.its.device.rest.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.device.domain.model.SysFunction;
import cy.its.device.domain.service.IModelFunctionService;
import cy.its.device.rest.action.ISysFunctionManageAction;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.Results;
import cy.its.device.rest.dto.SystemFunctionDto;

@RestController
@RequestMapping("/device/sysFunctionManage")
public class SysFunctionManageAction implements ISysFunctionManageAction {
	
	@Autowired
	IModelFunctionService modelFunctionService;
	
	/**
	 * 添加系统功能
	 * @param form 系统功能信息
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/addSysFunction")
	public int addSysFunction(@ModelAttribute("form") SystemFunctionDto form) throws Exception {
		// TODO Auto-generated method stub
		SysFunction function = new SysFunction();
		ObjectMapUtils.parseObject(function, form);
		modelFunctionService.createFunction(function);
		return 1;
	}
	
	/**
	 * 查询系统功能列表
	 * @return	对象集合
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/querySysFunction")
	public DataGridResult<SystemFunctionDto> querySysFunction() throws Exception {
		// TODO Auto-generated method stub
		List<SysFunction> list = modelFunctionService.findSysFunction();
		List<SystemFunctionDto> lst = new ArrayList<SystemFunctionDto>();
		for (int i = 0; i < list.size(); i++) {
			SystemFunctionDto systemFunctionDto = new SystemFunctionDto();
			ObjectMapUtils.parseObject(systemFunctionDto, list.get(i));
			lst.add(systemFunctionDto);
		}
		Results<SystemFunctionDto> res = new Results<SystemFunctionDto>();
		res.setRows(lst);
		DataGridResult<SystemFunctionDto> dgr = new DataGridResult<SystemFunctionDto>();
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	}
	
	/**
	 * 查询所有系统功能
	 */
	@Override
	@RequestMapping(value = "/queryAllSysFunction")
	public List<SystemFunctionDto> queryAllSysFunction() throws Exception {
		// TODO Auto-generated method stub
		List<SysFunction> list = modelFunctionService.findSysFunction();
		List<SystemFunctionDto> lst = new ArrayList<SystemFunctionDto>();
		for (int i = 0; i < list.size(); i++) {
			SystemFunctionDto systemFunctionDto = new SystemFunctionDto();
			//系统功能ID
			systemFunctionDto.setDeviceSysFunctionId(list.get(i).getDeviceSysFunctionId());
			//系统功能名称
			systemFunctionDto.setFunctionName(list.get(i).getFunctionName());
			lst.add(systemFunctionDto);
		}
		return lst;
	}
	
	
	/**
	 * 删除系统功能
	 * @param deviceSysFunctionId 系统功能ID
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/removeSysFunction")
	public int removeSysFunction(@RequestParam("deviceSysFunctionId") String deviceSysFunctionId) throws Exception {
		// TODO Auto-generated method stub
		String sysFunctionId = deviceSysFunctionId;
		modelFunctionService.deleteFunction(sysFunctionId);
		return 1;
	}
	
	/**
	 * 批量删除系统功能
	 * @param deviceSysFunctionIdStr 多个系统功能ID字符串
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/removeSomeSysFunction")
	public int removeSomeSysFunction(@RequestParam("deviceSysFunctionIdStr") String deviceSysFunctionIdStr) throws Exception {
		// TODO Auto-generated method stub
		String deviceSysFunctionId[] = deviceSysFunctionIdStr.split(",");
		for (int i = 0; i < deviceSysFunctionId.length; i++) {
			String sysFunctionId = deviceSysFunctionId[i];
			modelFunctionService.deleteFunction(sysFunctionId);
		}
		return 1;
	}
	
	/**
	 * 编辑系统功能
	 * @param form 系统功能信息
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/editSysFunction")
	public int editSysFunction(@ModelAttribute("form") SystemFunctionDto form) throws Exception {
		// TODO Auto-generated method stub
		SysFunction function = new SysFunction();
		ObjectMapUtils.parseObject(function, form);
		modelFunctionService.updateFunction(function);
		return 1;
	}

}
