package cy.its.device.domain.service;

import java.util.List;

import cy.its.device.domain.model.SysFunction;
import cy.its.device.domain.model.SysModel;

public interface IModelFunctionService {
	
	List<SysModel> findAllSysModel();
	List<SysModel> findSysModel(String sysModelName);
	void createModel(SysModel sysModel);
	void deleteModel(String sysModelId);
	void updateModel(SysModel sysModels);
	
	List<SysFunction> findSysFunction();
	void createFunction(SysFunction function);
	void deleteFunction(String sysFunctionId);
	void updateFunction(SysFunction function);
	
	
	void attachFuncToModel(String sysFunctionId, String sysModelId, String remark);
	void disattachFuncToModel(String sysFunctionId, String sysModelId);
	List<SysFunction> functionsOfModel(String modelId);
}
