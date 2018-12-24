package cy.its.device.domain.repository;

import java.util.List;

import cy.its.device.domain.model.ModelFunction;
import cy.its.device.domain.model.SysFunction;
import cy.its.device.domain.model.SysModel;

public interface IModelFunctionRepository {
	void createFunction(SysFunction function);
	List<SysFunction> functionsOfModel(String modelId);
	void createModel(SysModel sysModel);
	void createModelFunction(ModelFunction modelFunction);
	List<SysModel> findSysModel();
	List<SysModel> findSysModel(String modelName);
	void deleteModel(String sysModelId);
	void updateModel(SysModel sysModel);
	List<SysFunction> findSysFunction();
	void deleteFunction(String sysFunctionId);
	void updateFunction(SysFunction function);
	void deleteModelFunction(String sysFunctionId, String sysModelId);
}
