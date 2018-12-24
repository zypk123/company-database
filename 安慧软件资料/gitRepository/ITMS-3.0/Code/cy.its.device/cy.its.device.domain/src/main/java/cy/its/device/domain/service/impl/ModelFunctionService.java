package cy.its.device.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.model.ModelFunction;
import cy.its.device.domain.model.SysFunction;
import cy.its.device.domain.model.SysModel;
import cy.its.device.domain.repository.IModelFunctionRepository;
import cy.its.device.domain.service.IModelFunctionService;

@Service
public class ModelFunctionService implements IModelFunctionService {

	@Autowired
	IModelFunctionRepository modelFunctionRepository;

	public void createFunction(SysFunction function) {
		modelFunctionRepository.createFunction(function);
	}

	public List<SysFunction> functionsOfModel(String modelId) {
		return modelFunctionRepository.functionsOfModel(modelId);
	}

	public void createModel(SysModel sysModel) {
		modelFunctionRepository.createModel(sysModel);
	}

	public void deleteModel(String sysModelId) {
		modelFunctionRepository.deleteModel(sysModelId);
		
	}

	public void updateModel(SysModel sysModel) {
		modelFunctionRepository.updateModel(sysModel);
	}

	public List<SysFunction> findSysFunction() {
		return modelFunctionRepository.findSysFunction();
	}

	public void deleteFunction(String sysFunctionId) {
		modelFunctionRepository.deleteFunction(sysFunctionId);
	}

	public void updateFunction(SysFunction function) {
		modelFunctionRepository.updateFunction(function);
	}

	public void attachFuncToModel(String functionId, String modelId, String remark) {
		ModelFunction modelFunction = new ModelFunction();
		modelFunction.setDeviceSysFunctionId(functionId);
		modelFunction.setDeviceSysModelId(modelId);
		modelFunction.setRemark(remark);
		modelFunctionRepository.createModelFunction(modelFunction);
	}

	public void disattachFuncToModel(String sysFunctionId, String sysModelId) {
		modelFunctionRepository.deleteModelFunction(sysFunctionId, sysModelId);
	}

	public List<SysModel> findSysModel() {
		return modelFunctionRepository.findSysModel();
	}
	
	public List<SysModel> findAllSysModel() {
		return modelFunctionRepository.findSysModel();
	}

	public List<SysModel> findSysModel(String sysModelName) {
		return modelFunctionRepository.findSysModel(sysModelName);
	}
}
