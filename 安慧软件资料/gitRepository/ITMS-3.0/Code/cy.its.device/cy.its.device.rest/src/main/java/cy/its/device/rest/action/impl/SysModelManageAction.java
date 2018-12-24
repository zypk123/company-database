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
import cy.its.device.domain.model.SysModel;
import cy.its.device.domain.service.IModelFunctionService;
import cy.its.device.rest.action.ISysModelManageAction;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.Results;
import cy.its.device.rest.dto.SystemModelDto;
import cy.its.device.rest.dto.TreeDto;

@RestController
@RequestMapping("/device/sysModelManage")
public class SysModelManageAction implements ISysModelManageAction {
	
	@Autowired
	IModelFunctionService modelFunctionService;
	
	/**
	 * 添加系统型号
	 * @param form 系统型号信息
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/addSysModel")
	public int addSysModel(@ModelAttribute("form") SystemModelDto form) throws Exception {
		// TODO Auto-generated method stub
		SysModel sysModel = new SysModel();
		ObjectMapUtils.parseObject(sysModel, form);
		modelFunctionService.createModel(sysModel);
		return 1;
	}
	
	/**
	 *编辑系统型号
	 * @param form 系统型号信息
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/updateSysModel")
	public int updateSysModel(@ModelAttribute("form") SystemModelDto form) throws Exception {
		// TODO Auto-generated method stub
		SysModel sysModels = new SysModel();
		ObjectMapUtils.parseObject(sysModels, form);
		modelFunctionService.updateModel(sysModels);
		return 1;
	}
	
	/**
	 * 查询所有系统型号
	 * @return	对象集合
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/queryAllSysModel")
	public List<TreeDto> queryAllSysModel() throws Exception {
		// TODO Auto-generated method stub
		List<SysModel> list = modelFunctionService.findAllSysModel();
		List<TreeDto> lst = new ArrayList<TreeDto>();
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				TreeDto modelTree = new TreeDto();
				modelTree.setId(list.get(i).getDeviceSysModelId());			//系统型号ID
				modelTree.setText(list.get(i).getDeviceSysModelName());		//系统型号名称
				lst.add(modelTree);
			}
		}
		return lst;
	}
	
	/**
	 * 删除系统型号
	 * @param sysModelId 系统型号ID
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/removeSysModel")
	public int removeSysModel(@RequestParam("deviceSysModelId") String deviceSysModelId) throws Exception {
		// TODO Auto-generated method stub
		String sysModelId = deviceSysModelId;
		String modelId = sysModelId;
		List<SysFunction> list = modelFunctionService.functionsOfModel(modelId);//系统型号已配置好的功能
		for (int i = 0; i < list.size(); i++) {
			String sysFunctionId = list.get(i).getDeviceSysFunctionId();
			modelFunctionService.disattachFuncToModel(sysFunctionId, sysModelId);//删除原先设置好的系统功能
		}
		modelFunctionService.deleteModel(sysModelId);
		return 1;
	}
	
	/**
	 * 添加系统型号功能
	 * @param form 型号及其功能信息
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/addModelFunction")
	public int addModelFunction(@ModelAttribute("form") SystemModelDto form) throws Exception {
		String sysModelId = form.getDeviceSysModelId();//系统型号ID
		String remark = form.getRemark();
		String deviceSysFunctionId[] = form.getDeviceSysFunctionId().split(",");//型号功能ID数组
		for (int i = 0; i < deviceSysFunctionId.length; i++) {
			String sysFunctionId = deviceSysFunctionId[i];
			modelFunctionService.attachFuncToModel(sysFunctionId, sysModelId, remark);
		}
		return 1;
	};
	
	/**
	 * 查询系统型号功能列表
	 * @param form 系统型号功能信息
	 * @return	对象集合
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/queryModelFunction")
	public DataGridResult<SystemModelDto> queryModelFunction(@RequestParam("deviceSysModelId") String deviceSysModelId) throws Exception {
		// TODO Auto-generated method stub
		String modelId = deviceSysModelId;
		List<SysFunction> list = modelFunctionService.functionsOfModel(modelId);
		List<SystemModelDto> lst = new ArrayList<SystemModelDto>();
		for (int i = 0; i < list.size(); i++) {
			SystemModelDto systemFunctionDto = new SystemModelDto();
			ObjectMapUtils.parseObject(systemFunctionDto, list.get(i));
			systemFunctionDto.setDeviceSysModelId(modelId);
			lst.add(systemFunctionDto);
		}
		Results<SystemModelDto> res = new Results<SystemModelDto>();
		res.setRows(lst);
		DataGridResult<SystemModelDto> dgr = new DataGridResult<SystemModelDto>();
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	}
	

	/**
	 * 
	  * querySysFunction(查询系统型号已配置好的功能)
	  * @Title: querySysFunction
	  * @Description: 查询系统型号已配置好的功能
	  * @param @param deviceSysModelId
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return List<SystemModelDto>    返回类型
	  * @throws
	 */
	@Override
	@RequestMapping(value="/querySysFunction")
	public List<SystemModelDto> querySysFunction(@RequestParam("deviceSysModelId") String deviceSysModelId) throws Exception {
		// TODO Auto-generated method stub
		String modelId = deviceSysModelId;
		List<SysFunction> list = modelFunctionService.functionsOfModel(modelId);
		List<SystemModelDto> lst = new ArrayList<SystemModelDto>();
		for (int i = 0; i < list.size(); i++) {
			SystemModelDto systemModelDto = new SystemModelDto();
			ObjectMapUtils.parseObject(systemModelDto, list.get(i));
			lst.add(systemModelDto);
		}
		return lst;
	}
	
	/**
	 * 删除列表中型号功能
	 * @return 整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/removeModelFunction")
	public int removeModelFunction(@RequestParam("deviceSysModelId") String deviceSysModelId,@RequestParam("deviceSysFunctionId") String deviceSysFunctionId) throws Exception {
		// TODO Auto-generated method stub
		String sysModelId = deviceSysModelId;
		String sysFunctionId = deviceSysFunctionId;
		modelFunctionService.disattachFuncToModel(sysFunctionId, sysModelId);
		return 1;
	}
	
	
	/**
	 * 批量删除系统型号功能
	 * @param sysModelIdStr 多个系统型号ID字符串，deviceSysFunctionIdStr 多个系统功能ID字符串
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/removeSomeModelFunction")
	public int removeSomeModelFunction(@RequestParam("deviceSysModelIdStr") String deviceSysModelIdStr,@RequestParam("deviceSysFunctionIdStr") String deviceSysFunctionIdStr) throws Exception {
		// TODO Auto-generated method stub
		String deviceSysModelId[] = deviceSysModelIdStr.split(",");
		String deviceSysFunctionId[] = deviceSysFunctionIdStr.split(",");
		for (int i = 0; i < deviceSysModelId.length; i++) {
			String sysModelId = deviceSysModelId[i];
			String sysFunctionId = deviceSysFunctionId[i];
			modelFunctionService.disattachFuncToModel(sysFunctionId, sysModelId);
		}
		return 1;
	}

	/**
	 * 
	  * editModelFunction(编辑系统型号功能信息)
	  * @Title: editModelFunction
	  * @Description: 编辑系统型号功能
	  * @param @param form
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	@RequestMapping(value = "/editModelFunction")
	public int editModelFunction(@ModelAttribute("form") SystemModelDto form) throws Exception {
		// TODO Auto-generated method stub
		String sysModelId = form.getDeviceSysModelId();
		String modelId = sysModelId;
		List<SysFunction> list = modelFunctionService.functionsOfModel(modelId);//系统型号已配置好的功能
		for (int i = 0; i < list.size(); i++) {
			String sysFunctionId = list.get(i).getDeviceSysFunctionId();
			modelFunctionService.disattachFuncToModel(sysFunctionId, sysModelId);//删除原先设置好的系统功能
		}
		String remark = form.getRemark();
		String deviceSysFunctionId[] = form.getDeviceSysFunctionId().split(",");
		for (int i = 0; i < deviceSysFunctionId.length; i++) {
			String sysFunctionId = deviceSysFunctionId[i];
			modelFunctionService.attachFuncToModel(sysFunctionId, sysModelId, remark);//添加编辑后的系统功能
		}
		return 1;
	}
}
