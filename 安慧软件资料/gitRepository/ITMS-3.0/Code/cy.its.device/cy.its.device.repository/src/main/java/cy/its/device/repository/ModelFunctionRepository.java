package cy.its.device.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.ModelFunction;
import cy.its.device.domain.model.ModelFunctionKey;
import cy.its.device.domain.model.SysFunction;
import cy.its.device.domain.model.SysModel;
import cy.its.device.domain.repository.IModelFunctionRepository;
import cy.its.device.mybatis.client.ModelFunctionMapper;
import cy.its.device.mybatis.client.SysFunctionMapper;
import cy.its.device.mybatis.client.SysModelMapper;

@Service
public class ModelFunctionRepository implements IModelFunctionRepository {
	
	@Autowired
	ModelFunctionMapper modelFunctionMapper;
	
	@Autowired
	SysModelMapper sysModelMapper;
	
	@Autowired
	SysFunctionMapper sysFunctionMapper;
	
	public void createFunction(SysFunction function) {
		function.setDeviceSysFunctionId(StringUtil.generateUUID());
		sysFunctionMapper.insertSelective(function);
	}
	
	public List<SysFunction> functionsOfModel(String modelId) {		
		return sysFunctionMapper.selectFunctions(modelId);
	}
	
	public void createModel(SysModel sysModel) {
		sysModel.setDeviceSysModelId(StringUtil.generateUUID());
		sysModelMapper.insertSelective(sysModel);
	}
	
	public void createModelFunction(ModelFunction modelFunction) {
		modelFunctionMapper.insertSelective(modelFunction);
	}

	public void deleteModel(String sysModelId) {
		sysModelMapper.deleteByPrimaryKey(sysModelId);
	}

	public void updateModel(SysModel sysModel) {
		sysModelMapper.updateByPrimaryKeySelective(sysModel);
	}

	public List<SysFunction> findSysFunction() {
		return sysFunctionMapper.selectFunctions(null);
	}

	public void deleteFunction(String sysFunctionId) {
		sysFunctionMapper.deleteByPrimaryKey(sysFunctionId);
	}

	public void updateFunction(SysFunction function) {
		sysFunctionMapper.updateByPrimaryKeySelective(function);
	}

	public void deleteModelFunction(String sysFunctionId, String sysModelId) {
		ModelFunctionKey key = new ModelFunctionKey();
		key.setDeviceSysModelId(sysModelId);
		key.setDeviceSysFunctionId(sysFunctionId);
		modelFunctionMapper.deleteByPrimaryKey(key);
	}

	public List<SysModel> findSysModel() {
		return sysModelMapper.selectModels(null);
	}
	
	public List<SysModel> findSysModel(String modelName) {
		return sysModelMapper.selectModels(modelName);
	}
}
